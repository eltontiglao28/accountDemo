package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.CustomerInquiryResponse;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FinancialTransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerInquiryServiceImpl implements CustomerInquiryService {

    private static final int NOT_FOUND_STATUS_CODE = 401;
    private static final int CUSTOMER_FOUND_STATUS_CODE = 302;

    private final CustomerRepository customerRepository;
    private final FinancialTransactionRepository financialTransactionRepository;

    @Override
    public CustomerInquiryResponse findByCustomerId(String customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        
        if (!customer.isPresent())
            return new CustomerInquiryResponse(NOT_FOUND_STATUS_CODE, "Customer not found");

        Customer existingCustomer = customer.get();
        CustomerInquiryResponse response = new CustomerInquiryResponse();
        response.setCustomerName(existingCustomer.getCustomerName());
        response.setCustomerNumber(existingCustomer.getCustomerNumber());
        response.setCustomerMobile(existingCustomer.getCustomerMobile());
        response.setCustomerEmail(existingCustomer.getCustomerEmail());
        response.setAddress1(existingCustomer.getAddress1());
        response.setAddress2(existingCustomer.getAddress2());
        response.setSavings(financialTransactionRepository.findListOfAccountSavingsByCustomer(existingCustomer));

        response.setTransactionStatusCode(CUSTOMER_FOUND_STATUS_CODE);
        response.setTransactionStatusDescription("Customer Account found");
        return response;
    }
    
}
