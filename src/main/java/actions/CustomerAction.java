package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CustomerView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.CustomerService;

/**
 * 顧客に関する処理を行うActionクラス
 *
 */
public class CustomerAction extends ActionBase {

    private CustomerService service;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        service = new CustomerService();

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

        //指定されたページ数の一覧画面に表示する顧客データを取得
        int page = getPage();
        List<CustomerView> Customers = service.getAllPerPage(page);

        //全顧客データの件数を取得
        long CustomersCount = service.countAll();

        putRequestScope(AttributeConst.CUSTOMERS, Customers); //取得した顧客データ
        putRequestScope(AttributeConst.CUS_COUNT, CustomersCount); //全ての顧客データの件数
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数

        //セッションにフラッシュメッセージが設定されている場合はリクエストスコープに移し替え、セッションからは削除する
        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        //一覧画面を表示
        forward(ForwardConst.FW_CUS_INDEX);
    }

    /**
     * 新規登録画面を表示する
    * @throws ServletException
    * @throws IOException
    */
    public void entryNew() throws ServletException, IOException {

    putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン

    //顧客情報の空インスタンスに、日報の日付＝今日の日付を設定する
    CustomerView rv = new CustomerView();
    rv.setCustomerDate(LocalDate.now());
    putRequestScope(AttributeConst.CUSTOMER, rv); //日付のみ設定済みの顧客インスタンス

    //新規登録画面を表示
    forward(ForwardConst.FW_CUS_NEW);

    }

    /**
     * 新規登録を行う
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException {

    //CSRF対策 tokenのチェック
    if (checkToken()) {

        //顧客の日付が入力されていなければ、今日の日付を設定
        LocalDate day = null;
        if (getRequestParam(AttributeConst.CUS_DATE) == null
                || getRequestParam(AttributeConst.CUS_DATE).equals("")) {
            day = LocalDate.now();
        } else {
            day = LocalDate.parse(getRequestParam(AttributeConst.CUS_DATE));
        }

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //パラメータの値をもとに顧客情報のインスタンスを作成する
        CustomerView rv = new CustomerView(
                null,
                ev, //ログインしている従業員を、顧客作成者として登録する
                day,
                getRequestParam(AttributeConst.CUS_NAME),
                getRequestParam(AttributeConst.CUS_KANA),
                getRequestParam(AttributeConst.CUS_COM),
                getRequestParam(AttributeConst.CUS_PHONE),
                getRequestParam(AttributeConst.CUS_MOBILE),
                null,
                null);

        //日報情報登録
        List<String> errors = service.create(rv);

        if (errors.size() > 0) {
            //登録中にエラーがあった場合

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.CUSTOMER, rv);//入力された顧客情報
            putRequestScope(AttributeConst.ERR, errors);//エラーのリスト

            //新規登録画面を再表示
            forward(ForwardConst.FW_CUS_NEW);

        } else {
            //登録中にエラーがなかった場合

            //セッションに登録完了のフラッシュメッセージを設定
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

            //一覧画面にリダイレクト
            redirect(ForwardConst.ACT_CUS, ForwardConst.CMD_INDEX);
        }
        }
    }

    /**
     * 詳細画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void show() throws ServletException, IOException {

        //idを条件に顧客データを取得する
        CustomerView rv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

        if (rv == null) {
            //該当の顧客データが存在しない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.CUSTOMER, rv); //取得した顧客データ

            //詳細画面を表示
            forward(ForwardConst.FW_CUS_SHOW);
        }
    }

    /**
     * 編集画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void edit() throws ServletException, IOException {

        //idを条件に日報データを取得する
        CustomerView rv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

        //セッションからログイン中の従業員情報を取得
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if (rv == null || ev.getId() != rv.getEmployee().getId()) {
            //該当の日報データが存在しない、または
            //ログインしている従業員が日報の作成者でない場合はエラー画面を表示
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
            putRequestScope(AttributeConst.CUSTOMER, rv); //取得した日報データ

            //編集画面を表示
            forward(ForwardConst.FW_CUS_EDIT);
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

            //idを条件に顧客データを取得する
            CustomerView rv = service.findOne(toNumber(getRequestParam(AttributeConst.CUS_ID)));

            //入力された日報内容を設定する
            rv.setCustomerDate(toLocalDate(getRequestParam(AttributeConst.CUS_DATE)));
            rv.setCustomerName(getRequestParam(AttributeConst.CUS_NAME));
            rv.setCustomerKana(getRequestParam(AttributeConst.CUS_KANA));
            rv.setCompanyName(getRequestParam(AttributeConst.CUS_COM));
            rv.setPhoneNumber(getRequestParam(AttributeConst.CUS_PHONE));
            rv.setMobileNumber(getRequestParam(AttributeConst.CUS_MOBILE));

            //日報データを更新する
            List<String> errors = service.update(rv);

            if (errors.size() > 0) {
                //更新中にエラーが発生した場合

                putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
                putRequestScope(AttributeConst.CUSTOMER, rv); //入力された顧客情報
                putRequestScope(AttributeConst.ERR, errors); //エラーのリスト

                //編集画面を再表示
                forward(ForwardConst.FW_CUS_EDIT);
            } else {
                //更新中にエラーがなかった場合

                //セッションに更新完了のフラッシュメッセージを設定
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                //一覧画面にリダイレクト
                redirect(ForwardConst.ACT_CUS, ForwardConst.CMD_INDEX);

            }
        }
    }

}