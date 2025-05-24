package com.procurehackathon.repository;

import com.procurehackathon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom query methods can be added here
} 