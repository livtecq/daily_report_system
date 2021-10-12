package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商談情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class NegotiationView {

    /**
     * id
     */
    private Integer id;

    /**
     * 商談を登録した従業員
     */
    private EmployeeView employee;

    /**
     * いつの商談かを示す日付
     */
    private LocalDate negotiationDate;

    /**
     * 営業担当
     */
    private String salesRep;

    /**
     * 会社名
     */
    private String companyName;

    /**
     * 商談状況
     */
    private String negotiationStatus;

    /**
     * 商談の内容
     */
    private String content;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;

}