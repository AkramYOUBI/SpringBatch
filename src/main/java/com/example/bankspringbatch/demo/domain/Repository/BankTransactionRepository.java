package com.example.bankspringbatch.demo.domain.Repository;

import com.example.bankspringbatch.demo.domain.Entity.BankTransactionInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransactionInput, String> {
}
