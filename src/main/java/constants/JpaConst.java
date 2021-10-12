package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数は public static final 修飾子がついているとみなされる
 */
public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    //顧客テーブル
    String TABLE_CUS = "customers"; //テーブル名
    //顧客テーブルカラム
    String CUS_COL_ID = "id"; //id
    String CUS_COL_EMP = "employee_id"; //顧客を作成した従業員のid
    String CUS_COL_CUS_DATE = "customer_date"; //いつの顧客情報かを示す日付
    String CUS_COL_CUS_NAME = "customer_name"; //顧客名
    String CUS_COL_CUS_KANA = "customer_kana"; //顧客カナ
    String CUS_COL_COM_NAME = "company_name"; //会社名
    String CUS_COL_CUS_PHONE = "phone_number"; //固定電話
    String CUS_COL_CUS_MOBILE = "mobile_number"; //携帯番号
    String CUS_COL_CREATED_AT = "created_at"; //登録日時
    String CUS_COL_UPDATED_AT = "updated_at"; //更新日時

    //商談テーブル
    String TABLE_NEG = "negotiations"; //テーブル名
    //商談テーブルカラム
    String NEG_COL_ID = "id"; //id
    String NEG_COL_EMP = "employee_id"; //商談を作成した従業員のid
    String NEG_COL_NEG_DATE = "negotiation_date"; //いつの商談かを示す日付
    String NEG_COL_SALES_REP = "sales_rep"; //営業担当
    String NEG_COL_COM_NAME = "company_name"; //会社名
    String NEG_COL_NEG_STA = "negotiation_status"; //商談状況
    String NEG_COL_CONTENT = "content"; //商談の内容
    String NEG_COL_CREATED_AT = "created_at"; //登録日時
    String NEG_COL_UPDATED_AT = "updated_at"; //更新日時

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report"; //日報
    String ENTITY_CUS = "customer"; //顧客
    String ENTITY_NEG = "negotiation"; //商談

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員

    //NamedQueryの nameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全ての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;
    //全ての顧客をidの降順に取得する
    String Q_CUS_GET_ALL = ENTITY_CUS + ".getAll";
    String Q_CUS_GET_ALL_DEF = "SELECT r FROM Customer AS r ORDER BY r.id DESC";
    //全ての顧客の件数を取得する
    String Q_CUS_COUNT = ENTITY_CUS + ".count";
    String Q_CUS_COUNT_DEF = "SELECT COUNT(r) FROM Customer AS r";
    //指定した従業員が作成した顧客を全件idの降順で取得する
    String Q_CUS_GET_ALL_MINE = ENTITY_CUS + ".getAllMine";
    String Q_CUS_GET_ALL_MINE_DEF = "SELECT r FROM Customer AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した顧客の件数を取得する
    String Q_CUS_COUNT_ALL_MINE = ENTITY_CUS + ".countAllMine";
    String Q_CUS_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Customer AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;
    //全ての商談をidの降順に取得する
    String Q_NEG_GET_ALL = ENTITY_NEG + ".getAll";
    String Q_NEG_GET_ALL_DEF = "SELECT r FROM Negotiation AS r ORDER BY r.id DESC";
    //全ての商談の件数を取得する
    String Q_NEG_COUNT = ENTITY_NEG + ".count";
    String Q_NEG_COUNT_DEF = "SELECT COUNT(r) FROM Negotiation AS r";
    //指定した従業員が作成した商談を全件idの降順で取得する
    String Q_NEG_GET_ALL_MINE = ENTITY_NEG + ".getAllMine";
    String Q_NEG_GET_ALL_MINE_DEF = "SELECT r FROM Negotiation AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した商談の件数を取得する
    String Q_NEG_COUNT_ALL_MINE = ENTITY_NEG + ".countAllMine";
    String Q_NEG_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Negotiation AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;
    //商談を作成する従業員が会社名を全件取得する
    String Q_NEG_GET_LIST = ENTITY_CUS + ".getList";
    String Q_NEG_GET_LIST_MINE_DEF = "SELECT company_name FROM customer";



}
