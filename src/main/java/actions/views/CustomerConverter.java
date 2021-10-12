package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Customer;

/**
 * 顧客データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class CustomerConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv CustomerViewのインスタンス
     * @return Customerのインスタンス
     */
    public static Customer toModel(CustomerView rv) {
        return new Customer(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                rv.getCustomerDate(),
                rv.getCustomerName(),
                rv.getCustomerKana(),
                rv.getCompanyName(),
                rv.getPhoneNumber(),
                rv.getMobileNumber(),
                rv.getCreatedAt(),
                rv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Customerのインスタンス
     * @return CustomerViewのインスタンス
     */
    public static CustomerView toView(Customer r) {

        if (r == null) {
            return null;
        }

        return new CustomerView(
                r.getId(),
                EmployeeConverter.toView(r.getEmployee()),
                r.getCustomerDate(),
                r.getCustomerName(),
                r.getCustomerKana(),
                r.getCompanyName(),
                r.getPhoneNumber(),
                r.getMobileNumber(),
                r.getCreatedAt(),
                r.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<CustomerView> toViewList(List<Customer> list) {
        List<CustomerView> evs = new ArrayList<>();

        for (Customer r : list) {
            evs.add(toView(r));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Customer r, CustomerView rv) {
        r.setId(rv.getId());
        r.setEmployee(EmployeeConverter.toModel(rv.getEmployee()));
        r.setCustomerDate(rv.getCustomerDate());
        r.setCustomerName(rv.getCustomerName());
        r.setCustomerKana(rv.getCustomerKana());
        r.setCompanyName(rv.getCompanyName());
        r.setPhoneNumber(rv.getPhoneNumber());
        r.setMobileNumber(rv.getMobileNumber());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());

    }

    /**
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param r DTOモデル(コピー元)
     * @param rv Viewモデル(コピー先)
     */
    public static void copyModelToView(Customer r, CustomerView rv) {
        rv.setId(r.getId());
        rv.setEmployee(EmployeeConverter.toView(r.getEmployee()));
        rv.setCustomerDate(r.getCustomerDate());
        rv.setCustomerName(r.getCustomerName());
        rv.setCustomerKana(r.getCustomerKana());
        rv.setCompanyName(r.getCompanyName());
        rv.setPhoneNumber(r.getPhoneNumber());
        rv.setMobileNumber(r.getMobileNumber());
        rv.setCreatedAt(r.getCreatedAt());
        rv.setUpdatedAt(r.getUpdatedAt());
    }

}