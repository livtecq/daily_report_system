package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客データのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_CUS)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_CUS_GET_ALL,
            query = JpaConst.Q_CUS_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_CUS_COUNT,
            query = JpaConst.Q_CUS_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_CUS_GET_ALL_MINE,
            query = JpaConst.Q_CUS_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_CUS_COUNT_ALL_MINE,
            query = JpaConst.Q_CUS_COUNT_ALL_MINE_DEF)
})

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
@Entity
public class Customer {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.CUS_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 顧客を登録した従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.CUS_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * いつの顧客情報かを示す日付
     */
    @Column(name = JpaConst.CUS_COL_CUS_DATE, nullable = false)
    private LocalDate customerDate;

   /**
     * 顧客名
     */
    @Column(name = JpaConst.CUS_COL_CUS_NAME, length = 255, nullable = false)
    private String customerName;

    /**
     * 顧客名カナ
     */
    @Column(name = JpaConst.CUS_COL_CUS_KANA, length = 255, nullable = false)
    private String customerKana;

    /**
     * 会社名
     */
    @Column(name = JpaConst.CUS_COL_COM_NAME, length = 255, nullable = false)
    private String companyName;

    /**
     * 固定電話
     */
    @Column(name = JpaConst.CUS_COL_CUS_PHONE, length = 255)
    private String phoneNumber;

    /**
     * 携帯番号
     */
    @Column(name = JpaConst.CUS_COL_CUS_MOBILE, length = 255, nullable = false)
    private String mobileNumber;

    /**
     * 登録日時
     */
    @Column(name = JpaConst.CUS_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    @Column(name = JpaConst.CUS_COL_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

}