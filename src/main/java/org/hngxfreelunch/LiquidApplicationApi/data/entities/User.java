package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organizations organizations;

    private String profilePic;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String phone;

    private String refreshToken;

    private String passwordHash;

    private LocalDateTime updatedAt = LocalDateTime.now();

    private LocalDateTime createdAt = LocalDateTime.now();

    private String bankNumber;

    private String bankCode;

    private String bankName;

    private String bankRegion;

    private String currencyCode;

    private String currency = "NGN";

    private Boolean isAdmin;

    private BigInteger lunchCreditBalance = BigInteger.ZERO;

}
