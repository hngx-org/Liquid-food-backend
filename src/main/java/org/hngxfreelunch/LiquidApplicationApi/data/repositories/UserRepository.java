package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByFirstName(String name);

    List<User> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName);

    List<User> findAllByOrganization_Id(Long organizationId);

}
