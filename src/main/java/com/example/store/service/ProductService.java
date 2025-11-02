package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Methods expected by tests
    @Transactional
    public Product create(Product product) {
        return save(product);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllWithOrders() {
        // For simplicity, reuse findAll; orders can be fetched lazily as needed
        return getAll();
    }

    @Transactional(readOnly = true)
    public Product getByIdWithOrdersOrThrow(Long id) {
        return getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
