package com.lupoe.networkoperation.controller;

import com.lupoe.networkoperation.model.Customer;
import com.lupoe.networkoperation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
@RequiredArgsConstructor
public class CustomerController {

    final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    @PutMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody final Customer customer){
        return ResponseEntity.ok(customerService.saveCustomer(customer));

    }
}
