package com.procurehackathon.controller;

import com.procurehackathon.model.Request;
import com.procurehackathon.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long id) {
        Optional<Request> request = requestService.getRequestById(id);
        return request.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request savedRequest = requestService.saveRequest(request);
        return new ResponseEntity<>(savedRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Request> updateRequest(@PathVariable Long id, @RequestBody Request requestDetails) {
        Optional<Request> optionalRequest = requestService.getRequestById(id);
        if (optionalRequest.isPresent()) {
            Request existingRequest = optionalRequest.get();
            // Update existingRequest with details from requestDetails
            existingRequest.setStatus(requestDetails.getStatus());
            existingRequest.setQuantity(requestDetails.getQuantity());
            existingRequest.setJustification(requestDetails.getJustification());
            existingRequest.setProduct(requestDetails.getProduct());
            existingRequest.setRequestedBy(requestDetails.getRequestedBy());

            Request updatedRequest = requestService.saveRequest(existingRequest);
            return ResponseEntity.ok(updatedRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        if (requestService.getRequestById(id).isPresent()) {
            requestService.deleteRequest(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add more controller methods as needed
} 