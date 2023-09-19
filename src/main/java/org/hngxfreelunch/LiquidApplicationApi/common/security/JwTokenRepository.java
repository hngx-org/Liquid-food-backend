package org.hngxfreelunch.LiquidApplicationApi.common.security;

import org.hngxfreelunch.LiquidApplicationApi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JwTokenRepository extends JpaRepository<JwToken, Long> {
    List<JwToken> findAllByUser(User user);

    Optional<JwToken> findByAccessToken(String accessToken);
}
