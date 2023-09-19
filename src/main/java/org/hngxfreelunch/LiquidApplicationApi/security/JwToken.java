package org.hngxfreelunch.LiquidApplicationApi.security;

import jakarta.persistence.*;
import lombok.*;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "jwToken")
public class JwToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accessToken;
    private boolean isExpired;
    private boolean isRevoked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Staff staff;

    public JwToken(String accessToken, Staff staff){
        this.accessToken = accessToken;
        this.isRevoked = false;
        this.isExpired = false;
        this.staff = staff;
    }
}
