package com.procurehackathon.config;

import com.procurehackathon.model.UserModel;
import com.procurehackathon.entity.UserRole;
import com.procurehackathon.model.*;
import com.procurehackathon.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OnboardingRepository onboardingRepository;

    @Override
    public void run(String... args) {
        // Initialize Users
        UserModel user1 = new UserModel();
        user1.setEmail("user1@example.com");
        user1.setPassword("password123");
        user1.setName("User One");
        user1.setDepartment("IT");
        user1.setRole(UserRole.USER);
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user1);

        // Initialize Suppliers
        Supplier supplier1 = new Supplier();
        supplier1.setName("Tech Supplies Inc");
        supplier1.setEmail("contact@techsupplies.com");
        supplier1.setPhone("123-456-7890");
        supplier1.setCategory("Technology");
        supplier1.setContactPerson("John Doe");
        supplier1.setStatus("Active");
        supplierRepository.save(supplier1);

        Supplier supplier2 = new Supplier();
        supplier2.setName("Office Essentials");
        supplier2.setEmail("info@officeessentials.com");
        supplier2.setPhone("987-654-3210");
        supplier2.setCategory("Office Supplies");
        supplier2.setContactPerson("Jane Smith");
        supplier2.setStatus("Active");
        supplierRepository.save(supplier2);

        // Initialize Products
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setDescription("High-performance laptop");
        product1.setCategory("Electronics");
        product1.setPrice(999.99);
        product1.setSupplier(supplier1);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Office Chair");
        product2.setDescription("Ergonomic office chair");
        product2.setCategory("Furniture");
        product2.setPrice(199.99);
        product2.setSupplier(supplier2);
        productRepository.save(product2);

        // Initialize Requests
        Request request1 = new Request();
        request1.setRequestedBy(user1.getEmail());
        request1.setQuantity(2);
        request1.setStatus("Pending");
        request1.setJustification("Need for new employees");
        request1.setProduct(product1);
        requestRepository.save(request1);

        // Initialize Onboarding
        Onboarding onboarding1 = new Onboarding();
        onboarding1.setStatus("In Progress");
        onboarding1.setDocuments("Business License, Tax Certificate");
        onboarding1.setNotes("Pending verification");
        onboarding1.setSupplier(supplier1);
        onboarding1.setSubmittedAt(LocalDateTime.now());
        onboardingRepository.save(onboarding1);
    }
} 