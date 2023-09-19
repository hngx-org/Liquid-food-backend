package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<User, Long> {
}
