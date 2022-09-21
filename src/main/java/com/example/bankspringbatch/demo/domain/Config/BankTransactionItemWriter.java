package com.example.bankspringbatch.demo.domain.Config;

import com.example.bankspringbatch.demo.domain.Entity.BankTransactionInput;
import com.example.bankspringbatch.demo.domain.Repository.BankTransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankTransactionItemWriter implements ItemWriter<BankTransactionInput> {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;
    @Override
    public void write(List<? extends BankTransactionInput> list) throws Exception {
        bankTransactionRepository.saveAll(list);
    }
}
