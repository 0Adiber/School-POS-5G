package at.kaindorf.bankdb.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity
@DiscriminatorValue("GIRO")
public class GiroAccount extends Account{

    private Double overdraft;
    @Column(name = "debit_interest")
    private Float debitInterest;
    @Column(name = "credit_interest")
    private Float creditInterest;

}
