package org.hngxfreelunch.LiquidApplicationApi.security;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JwTokenRepository extends JpaRepository<JwToken, Long> {
    List<JwToken> findAllByStaff(Staff staff);

    Optional<JwToken> findByAccessToken(String accessToken);
}
