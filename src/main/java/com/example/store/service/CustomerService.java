package com.example.store.service;

import com.example.store.entity.Customer;
import com.example.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Customer> searchByName(String q) {
        return customerRepository.findByNameContainingIgnoreCase(q);
    }

    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
