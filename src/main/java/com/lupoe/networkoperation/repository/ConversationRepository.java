package com.lupoe.networkoperation.repository;

import com.lupoe.networkoperation.model.Conversation;
import com.lupoe.networkoperation.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByCustomerAndEndTimeIsNull(Customer customer);
}
