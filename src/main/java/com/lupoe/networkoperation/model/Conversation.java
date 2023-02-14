package com.lupoe.networkoperation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conversation_seq", sequenceName = "conversation_seq", allocationSize = 1)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    private long getDuration(){
        return ChronoUnit.MINUTES.between(startTime, endTime);
    }

    @Override
    public String toString() {
        return "Conversation von: "
                + customer.getFirstName() + " "
                + customer.getLastName()
                + " Beginn: " + startTime
                + " Ende: " + endTime
                + " Dauer: " + getDuration() + " Minuten";
    }
}
