package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.PasswordReset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {
    boolean existsByToken(String otp);

    boolean existsByEmail(String email);

    PasswordReset findByEmail(String email);

    Optional<PasswordReset> findByTokenAndEmail(String otp, String email);
}
