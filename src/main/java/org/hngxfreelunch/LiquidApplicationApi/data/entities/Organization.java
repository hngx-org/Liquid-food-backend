package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigInteger lunchPrice;

    private String currency;

    @OneToMany(mappedBy = "organization")
    private List<User> Users;

    @OneToOne
    private OrganizationLunchWallet wallet;

    private String email;

    @OneToMany(mappedBy = "organization", orphanRemoval = true)
    private List<User> staff;

}
