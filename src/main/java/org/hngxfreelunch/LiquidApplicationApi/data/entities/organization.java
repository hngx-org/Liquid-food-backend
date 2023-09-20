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
public class organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigInteger lunch_price;

    private String currency;

    @OneToMany(mappedBy = "organization")
    private List<users> users;

    @OneToMany(mappedBy = "organization")
    private List<organization_invites> invites;


    @OneToOne
    private organization_lunch_wallet wallet;

}