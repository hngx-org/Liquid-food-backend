package org.hngxfreelunch.LiquidApplicationApi.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization_lunch_wallet")
public class Organization_lunch_wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long balance;

    @OneToOne
    @JoinColumn(name = "org_id")
    private Organization organization;


}
