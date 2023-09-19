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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String stack;

//    private Integer lunchCredits;
      private Integer phonenumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id")
    private Organization organization;

    @OneToOne()
    private AccountDetails accountDetails;

    @OneToMany
    private List<Lunches> lunches_sent;

    @OneToMany
    private List<Lunches> lunches_received;

    private String email;

    private String password_hash;

}
