package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Customer;
import com.example.demo.entity.FinancialTransaction;
import com.example.demo.domain.AccountSavings;

public interface FinancialTransactionRepository extends CrudRepository<FinancialTransaction, Long>{


    @Query("""
        SELECT new com.example.demo.domain.AccountSavings(a.accountNumber, a.accountType, COALESCE(SUM(ft.amount), 0)) 
        FROM Account a LEFT OUTER JOIN FinancialTransaction ft 
            ON ft.account.accountNumber = a.accountNumber 
        WHERE a.customer = :customer 
        GROUP BY a.accountNumber""")
    List<AccountSavings> findListOfAccountSavingsByCustomer(Customer customer);

}
