package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Bond extends Investment {

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "INTEREST_RATE")
    private BigDecimal interestRate;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "MATURITY_DATE")
    private Date maturityDate;


    public Bond(String name,
                String issuer,
                Date purchaseDate,
                BigDecimal value,
                BigDecimal interestRate,
                Date maturityDate) {
        super(name, issuer, purchaseDate);
        this.value = value;
        this.interestRate = interestRate;
        this.maturityDate = maturityDate;
    }
}
