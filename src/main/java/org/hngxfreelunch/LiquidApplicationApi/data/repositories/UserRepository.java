package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT u FROM users u WHERE u.first_name = :name OR u.last_name = :name")
    List<User> findByFirstNameOrLastName(@Param("name") String name);

    @Query("SELECT u FROM users u WHERE u.first_name = :name")
    Optional<User> findByName(String name);
}
