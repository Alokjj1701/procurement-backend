package com.procurehackathon.service;

import com.procurehackathon.model.Request;
import com.procurehackathon.model.RequestStatus;
import com.procurehackathon.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public List<Request> getAllRequests() {
        List<Request> requests = requestRepository.findAll();
        // Eagerly load product data
        requests.forEach(request -> {
            if (request.getProduct() != null) {
                request.getProduct().getName(); // Force load
            }
        });
        return requests;
    }

    public List<Request> getRequestsByAssignee(Long assigneeId) {
        List<Request> requests = requestRepository.findByStatus("Pending");
        // Eagerly load product data
        requests.forEach(request -> {
            if (request.getProduct() != null) {
                request.getProduct().getName(); // Force load
            }
        });
        return requests;
    }

    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    public Request saveRequest(Request request) {
        // Add business logic before saving, e.g., validation, setting default status
        return requestRepository.save(request);
    }

    public Request updateRequest(Request request) {
        // Add business logic before saving, e.g., status transitions
        return requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public long countPendingRequests() {
        return requestRepository.countByStatus(RequestStatus.PENDING);
    }

    // Add more request-related business logic methods here
    // e.g., getRequestsByStatus, getRequestsByCategory, handleApprovalProcess, etc.
}
