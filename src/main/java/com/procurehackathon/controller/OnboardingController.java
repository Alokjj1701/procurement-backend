package com.procurehackathon.controller;

import com.procurehackathon.model.Onboarding;
import com.procurehackathon.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;

    @Autowired
    public OnboardingController(OnboardingService onboardingService) {
        this.onboardingService = onboardingService;
    }

    @GetMapping
    public List<Onboarding> getAllOnboardingRequests() {
        return onboardingService.getAllOnboardingRequests();
    }

    @GetMapping("/{engagementId}")
    public ResponseEntity<Onboarding> getOnboardingRequestById(@PathVariable Long engagementId) {
        Optional<Onboarding> onboarding = onboardingService.getOnboardingRequestById(engagementId);
        return onboarding.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Onboarding> createOnboardingRequest(@RequestBody Onboarding onboarding) {
        Onboarding savedOnboarding = onboardingService.saveOnboardingRequest(onboarding);
        return new ResponseEntity<>(savedOnboarding, HttpStatus.CREATED);
    }

    @PutMapping("/{engagementId}")
    public ResponseEntity<Onboarding> updateOnboardingRequest(@PathVariable Long engagementId, @RequestBody Onboarding onboardingDetails) {
        Optional<Onboarding> optionalOnboarding = onboardingService.getOnboardingRequestById(engagementId);
        if (optionalOnboarding.isPresent()) {
            Onboarding existingOnboarding = optionalOnboarding.get();
            // Update existingOnboarding with details from onboardingDetails
            existingOnboarding.setStatus(onboardingDetails.getStatus());
            existingOnboarding.setSupplier(onboardingDetails.getSupplier());
            existingOnboarding.setDocuments(onboardingDetails.getDocuments());
            existingOnboarding.setNotes(onboardingDetails.getNotes());
            existingOnboarding.setSubmittedAt(onboardingDetails.getSubmittedAt());

            Onboarding updatedOnboarding = onboardingService.saveOnboardingRequest(existingOnboarding);
            return ResponseEntity.ok(updatedOnboarding);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{engagementId}")
    public ResponseEntity<Void> deleteOnboardingRequest(@PathVariable Long engagementId) {
        if (onboardingService.getOnboardingRequestById(engagementId).isPresent()) {
            onboardingService.deleteOnboardingRequest(engagementId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add more controller methods as needed
} 