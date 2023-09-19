package org.hngxfreelunch.LiquidApplicationApi.common.security;

import jakarta.persistence.*;
import lombok.*;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.User;

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
    private User user;

    public JwToken(String accessToken, User user){
        this.accessToken = accessToken;
        this.isRevoked = false;
        this.isExpired = false;
        this.user = user;
    }
}
