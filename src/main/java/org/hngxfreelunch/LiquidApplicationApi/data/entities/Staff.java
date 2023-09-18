package org.hngxfreelunch.LiquidApplicationApi.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
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
    private UUID staff_id;
    private String firstname;
    private String lastname;
    private String stack;
    private Integer lunchCredits;
    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization organization;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Staff> team;
    @OneToOne
    @JoinColumn(name = "account_details_id", referencedColumnName = "id")
    private AccountDetails accountDetails;
    @OneToMany
    private List<Lunch> lunches_sent;
    @OneToMany
    private List<Lunch> lunches_received;
}
