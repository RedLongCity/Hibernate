package entitties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BUDGET")
public class Budget {

    @Id
    @GeneratedValue
    @Column(name = "BUDGET_ID")
    private Long budgetId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GOAL_AMOUNT")
    private BigDecimal goalAmount;

    @Column(name = "PERIOD")
    private String period;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BUDGET_TRANSACTION", joinColumns = @JoinColumn(name = "BUDGET_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    private List<Transaction> transactions = new ArrayList<>();

}
