package com.lupoe.networkoperation.controller;

import com.lupoe.networkoperation.model.Conversation;
import com.lupoe.networkoperation.model.Customer;
import com.lupoe.networkoperation.service.ConversationService;
import com.lupoe.networkoperation.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;
    private final CustomerService customerService;

    @GetMapping("/startCall/{id}")
    public ResponseEntity<Conversation> startCall(@PathVariable final Long id) {

        final Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final Conversation conversation = conversationService.getActiveCallByCustomer(customer);
        if (conversation != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(
                conversationService.save(Conversation.builder()
                        .customer(customer)
                        .startTime(LocalDateTime.now())
                        .build())
        );
    }

    @GetMapping("/endCall/{id}")
    public ResponseEntity<Conversation> endCall(@PathVariable final Long id) {

        final Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final Conversation conversation = conversationService.getActiveCallByCustomer(customer);
        if (conversation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        conversation.setEndTime(LocalDateTime.now());
        return ResponseEntity.ok(conversationService.save(conversation));
    }

    @GetMapping("/calls")
    public ResponseEntity<List<String>> getAllFinishedCalls() {
        final List<Conversation> finishedCalls = conversationService.getAllFinishedCalls();
        return ResponseEntity.ok(finishedCalls.stream().map(Conversation::toString).toList());
    }

    @GetMapping("/activeCalls")
    public ResponseEntity<List<Conversation>> getAllActiveCalls() {
        final List<Conversation> activeCalls = conversationService.getAllActiveCalls();
        return ResponseEntity.ok(activeCalls);

    }
}
