package com.example.demo;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.AccountSavings;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FinancialTransactionRepository;
import com.example.demo.service.CustomerInquiryService;
import com.example.demo.service.CustomerInquiryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CustomerInquiryServiceTest {
    
    private static final int NOT_FOUND_STATUS_CODE = 401;
    private static final int CUSTOMER_FOUND_STATUS_CODE = 302;

	@Mock
	private FinancialTransactionRepository financialTransactionRepository;

	@Mock
	private CustomerRepository customerRepository;

	private CustomerInquiryService customerInquiryService;

	@BeforeEach
	void initialize() {
		this.customerInquiryService = new CustomerInquiryServiceImpl(customerRepository, financialTransactionRepository);
	}

    @Test
	public void inputWithInvalidCustomerNumberGeneratesBadRequest() {
		//Given
		Long invalidCustomerId = 20L;
		org.mockito.BDDMockito.given(customerRepository.findById(invalidCustomerId))
            .willReturn(Optional.empty());

		//When
		CustomerInquiryResponse response = this.customerInquiryService.findByCustomerId(invalidCustomerId);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(NOT_FOUND_STATUS_CODE);
        org.mockito.Mockito.verify(customerRepository, times(1)).findById(invalidCustomerId);
	}


    @Test
	public void inputWithValidCustomerNumberGeneratesCustomerDataResponse() {
		//Given
		Long validCustomerId = 20L;

        Customer customerOutput = new Customer (20L, "Elton", "12345", "aaa@gmail.com", "address1", "address2");
		org.mockito.BDDMockito.given(customerRepository.findById(validCustomerId))
            .willReturn(Optional.of(customerOutput));
        org.mockito.BDDMockito.given(financialTransactionRepository.findListOfAccountSavingsByCustomer(customerOutput))
            .willReturn(List.of(new AccountSavings(12345L, AccountType.S, new BigDecimal(30.00))));
        

		//When
		CustomerInquiryResponse response = this.customerInquiryService.findByCustomerId(validCustomerId);

		//Then
		org.assertj.core.api.BDDAssertions.then(response.transactionStatusCode).isEqualTo(CUSTOMER_FOUND_STATUS_CODE);
        org.assertj.core.api.BDDAssertions.then(response.savings).isEqualTo(List.of(new AccountSavings(12345L, AccountType.S, new BigDecimal(30.00))));
        org.assertj.core.api.BDDAssertions.then(response.customerNumber).isEqualTo(20L);
        org.assertj.core.api.BDDAssertions.then(response.customerName).isEqualTo("Elton");
        org.assertj.core.api.BDDAssertions.then(response.customerMobile).isEqualTo("12345");
        org.assertj.core.api.BDDAssertions.then(response.customerEmail).isEqualTo("aaa@gmail.com");
        org.assertj.core.api.BDDAssertions.then(response.address1).isEqualTo("address1");
        org.assertj.core.api.BDDAssertions.then(response.address2).isEqualTo("address2");
        org.mockito.Mockito.verify(customerRepository, times(1)).findById(validCustomerId);
        org.mockito.Mockito.verify(financialTransactionRepository, times(1)).findListOfAccountSavingsByCustomer(customerOutput);
	}
}
