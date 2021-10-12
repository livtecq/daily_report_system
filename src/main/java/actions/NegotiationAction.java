package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.EmployeeView;
import actions.views.NegotiationView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.NegotiationService;

/**
 * 商談に関する処理を行うActionクラス
 *
 */
public class NegotiationAction extends ActionBase {

    private NegotiationService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new NegotiationService();

        //メソッドを実行
        invoke();
        service.close();
    }

    /**
     * 一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException {

        //指定されたページ数の一覧画面に表示する商談データを取得
        int page = getPage();
        List<NegotiationView> negotiations = service.getAllPerPage(page);

        //全商談データの件数を取得
        long negotiationsCount = service.countAll();

        putRequestScope(AttributeConst.NEGOTIATIONS, negotiations); //取得した商談データ
        putRequestScope(AttributeConst.NEG_COUNT, negotiationsCount); //全ての商談データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_NEG_INDEX);
    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

    //CSRF対策 tokenのチェック
    if (checkToken()) {

        //商談の日付が入力されていなければ、今日の日付を設定
        LocalDate day = null;
        if (getRequestParam(AttributeConst.NEG_DATE) == null
                || getRequestParam(AttributeConst.NEG_DATE).equals("")) {
            day = LocalDate.now();
        } else {
            day = LocalDate.parse(getRequestParam(AttributeConst.NEG_DATE));
        }

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //パラメータの値をもとに商談情報のインスタンスを作成する
        NegotiationView rv = new NegotiationView(
                null,
                ev, //ログインしている従業員を、商談作成者として登録する
                day,
                getRequestParam(AttributeConst.NEG_SALES),
                getRequestParam(AttributeConst.NEG_COMPANY),
                getRequestParam(AttributeConst.NEG_STATUS),
                getRequestParam(AttributeConst.NEG_CONTENT),
                null,
                null);

        //商談情報登録
        List<String> errors = service.create(rv);

        List<CustomerView> Customers = service.getAllcustomer();



        if (errors.size() > 0) {
            //登録中にエラーがあった場合

            putRequestScope(AttributeConst.CUSTOMERS, Customers); //取得した顧客データ
            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.NEGOTIATION, rv);//入力された商談情報
            putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

            //新規登録画面を再表示
            forward(ForwardConst.FW_NEG_NEW);

        } else {
            //登録中にエラーがなかった場合

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_NEG, ForwardConst.CMD_INDEX);
        }
        }
    }

    /**
     * 新規登録画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void entryNew() throws ServletException, IOException {

    List<CustomerView> Customers = service.getAllcustomer();

    putRequestScope(AttributeConst.CUSTOMERS, Customers); //取得した顧客データ
    putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

    //商談情報の空インスタンスに、商談の日付＝今日の日付を設定する
    NegotiationView rv = new NegotiationView();
    rv.setNegotiationDate(LocalDate.now());
    putRequestScope(AttributeConst.NEGOTIATION, rv); //日付のみ設定済みの商談インスタンス

    //新規登録画面を表示
    forward(ForwardConst.FW_NEG_NEW);

    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

    //idを条件に商談データを取得する
    NegotiationView rv = service.findOne(toNumber(getRequestParam(AttributeConst.NEG_ID)));

    if (rv == null) {
        //該当の商談データが存在しない場合はエラー画面を表示
        forward(ForwardConst.FW_ERR_UNKNOWN);

    } else {

        putRequestScope(AttributeConst.NEGOTIATION, rv); //取得した商談データ

        //詳細画面を表示
        forward(ForwardConst.FW_NEG_SHOW);
    }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

    List<CustomerView> Customers = service.getAllcustomer();

    putRequestScope(AttributeConst.CUSTOMERS, Customers); //取得した顧客データ

    //idを条件に商談データを取得する
    NegotiationView rv = service.findOne(toNumber(getRequestParam(AttributeConst.NEG_ID)));

    //セッションからログイン中の従業員情報を取得
    EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

    if (rv == null || ev.getId() != rv.getEmployee().getId()) {
        //該当の商談データが存在しない、または
        //ログインしている従業員が商談の作成者でない場合はエラー画面を表示
        forward(ForwardConst.FW_ERR_UNKNOWN);

    } else {


        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.NEGOTIATION, rv); //取得した商談データ

        //編集画面を表示
        forward(ForwardConst.FW_NEG_EDIT);
    }
    }

    /**
     * 更新を行う
     * @throws ServletException
     * @throws IOException
     */
    public void update() throws ServletException, IOException {

        //CSRF対策 tokenのチェック
        if (checkToken()) {

            //idを条件に商談データを取得する
            NegotiationView rv = service.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

            //入力された日報内容を設定する
            rv.setNegotiationDate(toLocalDate(getRequestParam(AttributeConst.NEG_DATE)));
            rv.setSalesRep(getRequestParam(AttributeConst.NEG_SALES));
            rv.setCompanyName(getRequestParam(AttributeConst.NEG_COMPANY));
            rv.setNegotiationStatus(getRequestParam(AttributeConst.NEG_STATUS));
            rv.setContent(getRequestParam(AttributeConst.NEG_CONTENT));

            //商談データを更新する
            List<String> errors = service.update(rv);
            List<CustomerView> Customers = service.getAllcustomer();


            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.CUSTOMERS, Customers); //取得した顧客データ
                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.NEGOTIATION, rv); //入力された日報情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_NEG_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_NEG, ForwardConst.CMD_INDEX);

            }
        }
    }
}
