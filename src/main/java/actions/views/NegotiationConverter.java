package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Negotiation;

/**
 * 商談データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class NegotiationConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rv NegotiationViewのインスタンス
     * @return Negotiationのインスタンス
     */
    public static Negotiation toModel(NegotiationView rv) {
        return new Negotiation(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                rv.getNegotiationDate(),
                rv.getSalesRep(),
                CustomerConverter.toModel(rv.getCustomerId()),
                rv.getNegotiationStatus(),
                rv.getContent(),
                rv.getCreatedAt(),
                rv.getUpdatedAt());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param r Negotiationのインスタンス
     * @return NegotiationViewのインスタンス
     */
    public static NegotiationView toView(Negotiation r) {

        if (r == null) {
            return null;
        }

        return new NegotiationView(
                r.getId(),
                EmployeeConverter.toView(r.getEmployee()),
                r.getNegotiationDate(),
                r.getSalesRep(),
                CustomerConverter.toView(r.getCustomerId()),
                r.getNegotiationStatus(),
                r.getContent(),
                r.getCreatedAt(),
                r.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<NegotiationView> toViewList(List<Negotiation> list) {
        List<NegotiationView> evs = new ArrayList<>();

        for (Negotiation r : list) {
            evs.add(toView(r));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param r DTOモデル(コピー先)
     * @param rv Viewモデル(コピー元)
     */
    public static void copyViewToModel(Negotiation r, NegotiationView rv) {
        r.setId(rv.getId());
        r.setEmployee(EmployeeConverter.toModel(rv.getEmployee()));
        r.setNegotiationDate(rv.getNegotiationDate());
        r.setSalesRep(rv.getSalesRep());
        r.setCustomerId(CustomerConverter.toModel(rv.getCustomerId()));
        r.setNegotiationStatus(rv.getNegotiationStatus());
        r.setContent(rv.getContent());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());

    }

    /**
     * DTOモデルの全フィールドの内容をViewモデルのフィールドにコピーする
     * @param r DTOモデル(コピー元)
     * @param rv Viewモデル(コピー先)
     */
    public static void copyModelToView(Negotiation r, NegotiationView rv) {
        rv.setId(r.getId());
        rv.setEmployee(EmployeeConverter.toView(r.getEmployee()));
        rv.setNegotiationDate(r.getNegotiationDate());
        rv.setSalesRep(r.getSalesRep());
        r.setCustomerId(CustomerConverter.toModel(rv.getCustomerId()));
        rv.setNegotiationStatus(r.getNegotiationStatus());
        rv.setContent(r.getContent());
        rv.setCreatedAt(r.getCreatedAt());
        rv.setUpdatedAt(r.getUpdatedAt());
    }

}