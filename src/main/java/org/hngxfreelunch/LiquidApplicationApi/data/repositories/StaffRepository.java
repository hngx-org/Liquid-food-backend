package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<users, Long> {
}
