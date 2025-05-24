package com.procurehackathon.repository;

import com.procurehackathon.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Custom query methods can be added here
} 