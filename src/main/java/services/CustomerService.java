package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CustomerConverter;
import actions.views.CustomerView;
import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.JpaConst;
import models.Customer;
import models.validators.CustomerValidator;

/**
 * 顧客テーブルの操作に関わる処理を行うクラス
 */
public class CustomerService extends ServiceBase {

    /**
     * 指定した従業員が作成した顧客データを、指定されたページ数の一覧画面に表示する分取得しCustomerViewのリストで返却する
     * @param employee 従業員
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<CustomerView> getMinePerPage(EmployeeView employee, int page) {

        List<Customer> customers = em.createNamedQuery(JpaConst.Q_CUS_GET_ALL_MINE, Customer.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return CustomerConverter.toViewList(customers);
    }

    /**
     * 指定した従業員が作成した顧客データの件数を取得し、返却する
     * @param employee
     * @return 顧客データの件数
     */
    public long countAllMine(EmployeeView employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
                .getSingleResult();

        return count;
    }

    /**
     * 指定されたページ数の一覧画面に表示する顧客データを取得し、CustomerViewのリストで返却する
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<CustomerView> getAllPerPage(int page) {

        List<Customer> customers = em.createNamedQuery(JpaConst.Q_CUS_GET_ALL, Customer.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return CustomerConverter.toViewList(customers);
    }

    /**
     * 顧客テーブルのデータの件数を取得し、返却する
     * @return データの件数
     */
    public long countAll() {
        long customers_count = (long) em.createNamedQuery(JpaConst.Q_CUS_COUNT, Long.class)
                .getSingleResult();
        return customers_count;
    }

    /**
     * idを条件に取得したデータをCustomerViewのインスタンスで返却する
     * @param id
     * @return 取得データのインスタンス
     */
    public CustomerView findOne(int id) {
        return CustomerConverter.toView(findOneInternal(id));
    }

    /**
     * 画面から入力された顧客の登録内容を元にデータを1件作成し、顧客テーブルに登録する
     * @param rv 顧客の登録内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> create(CustomerView rv) {
        List<String> errors = CustomerValidator.validate(rv);
        if (errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            rv.setCreatedAt(ldt);
            rv.setUpdatedAt(ldt);
            createInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * 画面から入力された顧客の登録内容を元に、顧客データを更新する
     * @param rv 顧客の更新内容
     * @return バリデーションで発生したエラーのリスト
     */
    public List<String> update(CustomerView rv) {

        //バリデーションを行う
        List<String> errors = CustomerValidator.validate(rv);

        if (errors.size() == 0) {

            //更新日時を現在時刻に設定
            LocalDateTime ldt = LocalDateTime.now();
            rv.setUpdatedAt(ldt);

            updateInternal(rv);
        }

        //バリデーションで発生したエラーを返却（エラーがなければ0件の空リスト）
        return errors;
    }

    /**
     * idを条件にデータを1件取得する
     * @param id
     * @return 取得データのインスタンス
     */
    private Customer findOneInternal(int id) {
        return em.find(Customer.class, id);
    }

    /**
     * 顧客データを1件登録する
     * @param rv 顧客データ
     */
    private void createInternal(CustomerView rv) {

        em.getTransaction().begin();
        em.persist(CustomerConverter.toModel(rv));
        em.getTransaction().commit();

    }

    /**
     * 顧客データを更新する
     * @param rv 顧客データ
     */
    private void updateInternal(CustomerView rv) {

        em.getTransaction().begin();
        Customer r = findOneInternal(rv.getId());
        CustomerConverter.copyViewToModel(r, rv);
        em.getTransaction().commit();

    }

}