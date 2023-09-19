package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String stack;
    private Integer lunchCredits;

    @ManyToOne(cascade = CascadeType.ALL)
    private Organization organization;

    @OneToOne()
    private AccountDetails accountDetails;

    @OneToMany
    private List<Lunch> lunches_sent;

    @OneToMany
    private List<Lunch> lunches_received;

}
