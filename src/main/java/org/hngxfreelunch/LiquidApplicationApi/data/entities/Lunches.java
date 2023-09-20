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
public class Lunches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToOne
    @JoinColumn(name = "senderId")
    private User sender;

    @OneToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    private String note;

    private BigInteger quantity;

    private Boolean redeemed;

    private LocalDateTime createdAt;

}
