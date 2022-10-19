package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Stock extends Investment {

    @Column(name = "SHARE_PRICE")
    private BigDecimal sharePrice;

    @Column(name = "QUANTITY")
    private BigDecimal quantity;


    public Stock(String name,
                 String issuer,
                 Date purchaseDate,
                 BigDecimal sharePrice,
                 BigDecimal quantity) {
        super(name, issuer, purchaseDate);
        this.sharePrice = sharePrice;
        this.quantity = quantity;
    }

}
