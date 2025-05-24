package com.procurehackathon.repository;

import com.procurehackathon.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    // Custom query methods can be added here
} 