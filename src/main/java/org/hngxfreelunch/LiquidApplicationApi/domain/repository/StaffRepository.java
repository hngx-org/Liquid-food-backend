package org.hngxfreelunch.LiquidApplicationApi.domain.repository;

import org.hngxfreelunch.LiquidApplicationApi.domain.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByEmail(String email);

}
