package com.example.bankspringbatch.demo.domain.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BankTransactionOutput {
    @Id
    private String id_Output;
    private String account_Output;
    private Date transactionDate_Output;
    @Transient
    private String strTansactionDate_Output;
    private double transactionAmount_Output;
    private String transactionType_Output;

}
