package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lunches")

public class lunches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "senderId")
    private users sender;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private users receiver;

    private String note;

    private BigInteger quantity;

    private Boolean redeemed;

    private LocalDateTime created_at;

}
