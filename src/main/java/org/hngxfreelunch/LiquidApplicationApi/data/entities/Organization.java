package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigInteger lunch_price;

    private String currency;

    @OneToMany(mappedBy = "organization")
    private List<User> User;

    @OneToMany(mappedBy = "organization")
    private List<OrganizationInvites> invites;


    @OneToOne
    private OrganizationLunchWallet wallet;

    private String email;
    @OneToMany(mappedBy = "organization", orphanRemoval = true)
    private List<Staff> staff;
}
