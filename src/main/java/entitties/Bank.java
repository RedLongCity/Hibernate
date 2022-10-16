package entitties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BANK")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANK_ID")
    private Long bankId;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Address address = new Address();

    @Column(name = "IS_INTERNATIONAL")
    private boolean international;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @ElementCollection
    @CollectionTable(name = "BANK_CONTACT", joinColumns = @JoinColumn(name = "BANK_ID"))
    @MapKeyColumn(name = "POSITION_TYPE")
    @Column(name = "NAME")
    private Map<String, String> contacts = new HashMap<>();

}
