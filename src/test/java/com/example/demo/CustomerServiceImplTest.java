package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.CustomerDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Customer;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

	private static final int NOT_FOUND_STATUS_CODE = 400;
    private static final int CREATED_STATUS_CODE = 201;

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private CustomerRepository customerRepository;

	private CustomerService customerService;

	@BeforeEach
	void initialize() {
		this.customerService = new CustomerServiceImpl(customerRepository, accountRepository);
	}

	@Test
	public void inputWithBlankCustomerNameGeneratesBadRequest() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			null,
			"12345",
			"aaaa@ggg.com",
			"address1",
			"address2",
			"S"
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}
	
	@Test
	public void inputWithBlankCustomerMobileGeneratesBadRequest() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			null,
			"aaaa@ggg.com",
			"address1",
			"address2",
			"S"
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}	

	@Test
	public void inputWithBlankCustomerEmailGeneratesBadRequest() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			"12345",
			null,
			"address1",
			"address2",
			"S"
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}	

	@Test
	public void inputWithBlankCustomerAddress1GeneratesBadRequest() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			"12345",
			"aaaa@ggg.com",
			null,
			"address2",
			"S"
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}
	
	@Test
	public void inputWithBlankCustomerAccountTypeGeneratesBadRequest() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			"12345",
			"aaaa@ggg.com",
			"address1",
			"address2",
			null
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}	


	@Test
	public void inputWithInvalidCustomerAccountTypeGeneratesBadRequest() {
		//Given
		String invalidAccountType = "A";
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			"12345",
			"aaaa@ggg.com",
			"address1",
			"address2",
			invalidAccountType
		);

		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNull();
	}	

	@Test
	public void validInputGeneratesCustomer() {
		//Given
		CustomerDTO customerDTO = new CustomerDTO(
			"Elton",
			"12345",
			"aaaa@ggg.com",
			"address1",
			"address2",
			"C"
		);

		Customer customerOutput = new Customer(1L, "Elton", "12345", "aaaa@gggg.com", "address1", "address2");
		Account account = new Account (1L, customerOutput, AccountType.C);
		org.mockito.BDDMockito.given(customerRepository.save(any()))
            .willReturn(customerOutput);
		org.mockito.BDDMockito.given(accountRepository.save(any()))
            .willReturn(account);


		//When
		CustomerCreationResponse response = this.customerService.generateCustomer(customerDTO);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(CREATED_STATUS_CODE);
		org.assertj.core.api.BDDAssertions.then(response.customerNumber).isNotNull();
		org.mockito.Mockito.verify(customerRepository, times(1)).save(
			new Customer("Elton", "12345", "aaaa@ggg.com", "address1", "address2")
		);
		org.mockito.Mockito.verify(accountRepository, times(1)).save(
			new Account(customerOutput, AccountType.C)
		);
	}	




}
