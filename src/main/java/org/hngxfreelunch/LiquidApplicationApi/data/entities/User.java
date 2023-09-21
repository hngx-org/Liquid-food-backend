package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

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
    private Organization organization;

    private String profile_picture;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String refreshToken;

    private String passwordHash;

    private LocalDateTime updatedAt;

    private LocalDateTime createdAt;

    private String bankNumber;

    private String bankCode;

    private String bankName;

    private String bankRegion;

    private String currencyCode;

    private Boolean isAdmin;

    private BigInteger lunchCreditBalance;

    @OneToMany(mappedBy = "user")
    private List<Withdrawals> withdrawals;

}
