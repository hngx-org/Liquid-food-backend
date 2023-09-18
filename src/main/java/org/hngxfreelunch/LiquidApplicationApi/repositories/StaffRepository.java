package org.hngxfreelunch.LiquidApplicationApi.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
