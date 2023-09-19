package org.hngxfreelunch.LiquidApplicationApi.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organization_lunch_wallet")
public class organization_lunch_wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long balance;

    @OneToOne
    @JoinColumn(name = "org_id")
    private organization organization;


}
