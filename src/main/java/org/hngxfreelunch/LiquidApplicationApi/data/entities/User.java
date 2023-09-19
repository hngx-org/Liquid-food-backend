package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.Instant;
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
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;

    private String first_name;
    private String last_name;
    private String profile_picture;
    private String email;
    private String phonenumber;
    private String password_hash;
    private boolean isAdmin;
    private BigInteger lunch_credit_balance;
    private String refresh_token;
    private Instant updated_at;
    private String bank_number;
    private String bank_code;
    private String bank_name;
    private Instant created_at;

    @OneToMany
    private List<Lunch> lunches;

}
