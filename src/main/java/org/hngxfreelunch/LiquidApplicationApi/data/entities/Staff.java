package org.hngxfreelunch.LiquidApplicationApi.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "liquid_staff")
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
