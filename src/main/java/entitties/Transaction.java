package entitties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "INITIAL_BALANCE")
    private BigDecimal initialBalance;

    @Column(name = "CLOSING_BALANCE")
    private BigDecimal closingBalance;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType='" + transactionType + '\'' +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", initialBalance=" + initialBalance +
                ", closingBalance=" + closingBalance +
                ", notes='" + notes + '\'' +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }

}
