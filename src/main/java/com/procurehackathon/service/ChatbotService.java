package com.procurehackathon.service;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.azure.core.credential.AzureKeyCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatbotService {

    @Value("${azure.openai.endpoint}")
    private String endpoint;

    @Value("${azure.openai.key}")
    private String key;

    @Value("${azure.openai.deployment-name}")
    private String deploymentName;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    private OpenAIClient client;
    private List<ChatMessage> conversationHistory;

    public ChatbotService() {
        this.conversationHistory = new ArrayList<>();
        // Add system message to set context
        String systemPrompt = "You are a helpful procurement assistant. You help users with purchase requests, " +
                       "supplier management, and product catalog queries. Keep responses concise and focused " +
                       "on procurement-related topics. You have access to the following information:\n" +
                       "1. Purchase request status and creation\n" +
                       "2. Product catalog and inventory\n" +
                       "3. Supplier information and onboarding\n" +
                       "4. Approval workflows and processes\n" +
                       "Always provide specific, actionable information based on the user's query.";
        conversationHistory.add(new ChatMessage(ChatRole.SYSTEM, systemPrompt));
    }

    private OpenAIClient getClient() {
        if (client == null) {
            client = new OpenAIClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(key))
                .buildClient();
        }
        return client;
    }

    public String processMessage(String message) {
        try {
            // Add user message to history
            conversationHistory.add(new ChatMessage(ChatRole.USER, message));

            // Add context about the system state
            String context = getProcurementContext();
            if (!context.isEmpty()) {
                conversationHistory.add(new ChatMessage(ChatRole.SYSTEM, "Current system state: " + context));
            }

            // Prepare options for chat completion
            ChatCompletionsOptions options = new ChatCompletionsOptions(conversationHistory)
                .setMaxTokens(800)
                .setTemperature(0.7)
                .setTopP(0.95);

            // Get response from Azure OpenAI
            ChatCompletions response = getClient().getChatCompletions(deploymentName, options);
            String aiResponse = response.getChoices().get(0).getMessage().getContent();

            // Add AI response to history
            conversationHistory.add(new ChatMessage(ChatRole.ASSISTANT, aiResponse));

            // Keep conversation history manageable
            if (conversationHistory.size() > 10) {
                conversationHistory = conversationHistory.subList(conversationHistory.size() - 10, conversationHistory.size());
            }

            return aiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return "I apologize, but I'm having trouble processing your request right now. Please try again later.";
        }
    }

    private String getProcurementContext() {
        StringBuilder context = new StringBuilder();
        try {
            // Add request statistics
            long pendingRequests = requestService.countPendingRequests();
            context.append("There are currently ").append(pendingRequests).append(" pending requests. ");

            // Add product catalog statistics
            long totalProducts = productService.countTotalProducts();
            context.append("The product catalog contains ").append(totalProducts).append(" items. ");

            // Add supplier statistics
            long activeSuppliers = supplierService.countActiveSuppliers();
            context.append("There are ").append(activeSuppliers).append(" active suppliers.");
        } catch (Exception e) {
            // If we can't get statistics, continue without them
        }
        return context.toString();
    }
} 