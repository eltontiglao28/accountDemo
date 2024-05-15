package com.example.demo;

import java.math.BigDecimal;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.entity.FinancialTransaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FinancialTransactionRepository;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	// Test only scenarios where Accounts have Financial Transactions
	@Bean
	ApplicationRunner runner(AccountRepository accountRepository, CustomerRepository customerRepository,
			FinancialTransactionRepository financialTransactionRepository) {
		return args -> {
			Customer customer = customerRepository.save(new Customer("Elton", "12345", "aaaa@gmail.com", "12345", "6788 Street"));
			Account account = accountRepository.save(new Account(customer, AccountType.S));
			FinancialTransaction ft1 = financialTransactionRepository.save(
				new FinancialTransaction(account, new BigDecimal(100.00))
			);

			FinancialTransaction ft2 = financialTransactionRepository.save(
				new FinancialTransaction(account, new BigDecimal(200.30))
			);
		};
	}

}
