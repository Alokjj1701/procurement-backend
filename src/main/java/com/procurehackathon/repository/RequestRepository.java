package com.procurehackathon.repository;

import com.procurehackathon.model.Request;
import com.procurehackathon.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(String status);
    long countByStatus(RequestStatus status);
} 