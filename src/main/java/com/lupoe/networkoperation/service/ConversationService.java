package com.lupoe.networkoperation.service;

import com.lupoe.networkoperation.model.Conversation;
import com.lupoe.networkoperation.model.Customer;
import com.lupoe.networkoperation.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public Conversation save(final Conversation conversation){
        return conversationRepository.save(conversation);
    }

    public Conversation getActiveCallByCustomer(final Customer customer){
        return conversationRepository.findByCustomerAndEndTimeIsNull(customer).orElse(null);
    }

    public List<Conversation> getAllFinishedCalls(){
        return conversationRepository.findConversationByEndTimeIsNotNull();
    }

    public List<Conversation> getAllActiveCalls(){
        return conversationRepository.findConversationByEndTimeIsNull();
    }


}
