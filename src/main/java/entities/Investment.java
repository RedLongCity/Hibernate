package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INVESTMENT_TYPE")
public abstract class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "INVESTMENT_ID")
    private Long investmentId;
    @JoinColumn(name = "PORTFOLIO_ID", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Portfolio portfolio;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "ISSUER")
    protected String issuer;

    @Column(name = "PURCHASE_DATE")
    protected Date purchaseDate;

    public Investment(String name, String issuer, Date purchaseDate) {
        this.name = name;
        this.issuer = issuer;
        this.purchaseDate = purchaseDate;
    }

}
