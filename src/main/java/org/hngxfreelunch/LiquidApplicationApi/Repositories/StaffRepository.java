package org.hngxfreelunch.LiquidApplicationApi.Repositories;

import org.hngxfreelunch.LiquidApplicationApi.Entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
