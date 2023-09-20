package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
