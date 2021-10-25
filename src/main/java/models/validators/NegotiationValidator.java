package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.CustomerView;
import actions.views.NegotiationView;
import constants.MessageConst;

/**
 * 商談インスタンスに設定されている値のバリデーションを行うクラス
 */
public class NegotiationValidator {

    /**
     * 商談インスタンスの各項目についてバリデーションを行う
     * @param rv 商談インスタンス
     * @return エラーのリスト
     */
    public static List<String> validate(NegotiationView rv) {
        List<String> errors = new ArrayList<String>();

        //営業担当のチェック
        String salesRepError = validateSalesRep(rv.getSalesRep());
        if (!salesRepError.equals("")) {
            errors.add(salesRepError);
        }

        //会社名のチェック
        String customerIdError = validateCustomerId(rv.getCustomerId());
        if (!customerIdError.equals("")) {
            errors.add(customerIdError);
        }

        //内容のチェック
        String contentError = validateContent(rv.getContent());
        if (!contentError.equals("")) {
            errors.add(contentError);
        }

        return errors;
    }

    /**
     * 営業担当に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param salesRep 営業担当
     * @return エラーメッセージ
     */
    private static String validateSalesRep(String salesRep) {
        if (salesRep == null || salesRep.equals("")) {
            return MessageConst.E_SALESREP.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 会社名に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param customerId 会社名
     * @return エラーメッセージ
     */
    private static String validateCustomerId(CustomerView customerId) {
        if (customerId == null || Integer.MIN_VALUE == customerId.getId()) {
            return MessageConst.E_COMPANYNAME.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    /**
     * 内容に入力値があるかをチェックし、入力値がなければエラーメッセージを返却
     * @param content 内容
     * @return エラーメッセージ
     */
    private static String validateContent(String content) {
        if (content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}