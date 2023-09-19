package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Integer lunch_price;

    private String currency;

    @OneToMany(mappedBy = "organization")
    private List<Users> users;

    @OneToMany(mappedBy = "organization")
    private List<Organization_invites> invites;



}
