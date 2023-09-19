package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "liquid_lunch")
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String message;
    private Integer lunchCredits;
    private LocalDate dateCreated;

}
