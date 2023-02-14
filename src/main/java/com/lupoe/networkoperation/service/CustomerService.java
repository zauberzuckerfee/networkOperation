package com.lupoe.networkoperation.service;

import com.lupoe.networkoperation.model.Customer;
import com.lupoe.networkoperation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(final Long id){
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer saveCustomer(final Customer customer){
        return customerRepository.save(customer);
    }
}
