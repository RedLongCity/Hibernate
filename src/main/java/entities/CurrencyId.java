package entities;

import lombok.Getter;

import java.io.Serializable;











@Getter
@SuppressWarnings("serial")
public class CurrencyId implements Serializable {

    private String name;

    private String countryName;

    public CurrencyId() {

    }

    public CurrencyId(String name, String countryName) {
        this.name = name;
        this.countryName = countryName;
    }
}
