package com.example.bankspringbatch.demo.domain.Config;

import com.example.bankspringbatch.demo.domain.Entity.BankTransactionInput;
import com.example.bankspringbatch.demo.domain.Entity.BankTransactionOutput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BankTransactionItemProcessor implements ItemProcessor<BankTransactionInput, BankTransactionOutput> {
    private SimpleDateFormat dateFormat= new SimpleDateFormat("dd/mm/yyyy-hh:mm");

    @Override
    public BankTransactionOutput process(BankTransactionInput bankTransactionInput) throws Exception {
        BankTransactionOutput bankTransactionOutput = new BankTransactionOutput();
        bankTransactionOutput.setTransactionDate_Output(dateFormat.parse(bankTransactionInput.getStrTransactionDate()));
        return null;
    }
}
