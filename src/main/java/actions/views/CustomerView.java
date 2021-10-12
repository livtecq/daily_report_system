package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 顧客情報について画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数にもつ引数ありコンストラクタを自動生成する(Lombok)
public class CustomerView {

    /**
     * id
     */
    private Integer id;

    /**
     * 顧客を登録した従業員
     */
    private EmployeeView employee;

    /**
     * いつの顧客かを示す日付
     */
    private LocalDate customerDate;

    /**
     * 顧客名
     */
    private String customerName;

    /**
     * 顧客名カナ
     */
    private String customerKana;

    /**
     * 会社名
     */
    private String companyName;

    /**
     * 固定電話
     */
    private String phoneNumber;

    /**
     * 携帯番号
     */
    private String mobileNumber;

    /**
     * 登録日時
     */
    private LocalDateTime createdAt;

    /**
     * 更新日時
     */
    private LocalDateTime updatedAt;
}