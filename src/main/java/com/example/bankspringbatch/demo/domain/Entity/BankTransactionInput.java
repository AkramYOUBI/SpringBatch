package com.example.bankspringbatch.demo.domain.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BankTransactionInput {
    @Id
    private String id;
    private String accountId;
    private Date transactionDate;
    @Transient
    private String strTransactionDate;
    private String transactionType;
    private double transactionAmount;

    @PrePersist
    private void onPrePersiste(){
        if(this.getId()==null)
            this.setId("BankTransaction_"+ UUID.randomUUID().toString());
    }
}
