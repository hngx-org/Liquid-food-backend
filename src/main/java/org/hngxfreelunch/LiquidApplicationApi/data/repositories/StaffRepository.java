package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<Users, Long> {
}
