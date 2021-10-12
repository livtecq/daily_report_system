package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CustomerView;
import constants.MessageConst;

/**
 * 顧客インスタンスに設定されている値のバリデーションを行うクラス
 */
public class CustomerValidator {

    /**
     * 顧客インスタンスの各項目についてバリデーションを行う
     * @param rv 顧客インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(CustomerView rv) {
        List<String> errors = new ArrayList<String>();

        //顧客名のチェック
        String customerNameError = validateCustomer(rv.getCustomerName());
        if (!customerNameError.equals("")) {
            errors.add(customerNameError);
        }

        //会社名のチェック
        String companyNameError = validateCompany(rv.getCompanyName());
        if (!companyNameError.equals("")) {
            errors.add(companyNameError);
        }

        //携帯番号のチェック
        String mobileNumberError = validateMobile(rv.getMobileNumber());
        if (!mobileNumberError.equals("")) {
            errors.add(mobileNumberError);
        }


        return errors;
    }

    /**
     * 顧客名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param customer タイトル
     * @return エラーメッセージ
     */
    private static String validateCustomer(String customer) {
        if (customer == null || customer.equals("")) {
            return MessageConst.E_CUSTOMERNAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 会社名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param company 内容
     * @return エラーメッセージ
     */
    private static String validateCompany(String company) {
        if (company == null || company.equals("")) {
            return MessageConst.E_COMPANYNAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 携帯番号に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param mobile 内容
     * @return エラーメッセージ
     */
    private static String validateMobile(String mobile) {
        if (mobile == null || mobile.equals("")) {
            return MessageConst.E_MOBILEPHONE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}