package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private organization organization;

    private String profile_picture;

    private String first_name;

    private String last_name;

    private String email;

    private Integer phonenumber;

    private String refresh_token;

    private String password_hash;

    private LocalDateTime updated_at;

    private LocalDateTime created_at;

    private String bank_number;

    private String bank_code;

    private String bank_name;

    private Boolean isAdmin;

    private String lunch_credit_balance;



    @OneToMany(mappedBy = "user")
    private List<withdrawals> withdrawals;





}
