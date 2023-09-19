package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lunches")

public class Lunches {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "senderId")
    private users sender;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private users receiver;

    private String note;

    private Integer quantity;

    private Boolean redeemed;

    private LocalDate created_at;

}
