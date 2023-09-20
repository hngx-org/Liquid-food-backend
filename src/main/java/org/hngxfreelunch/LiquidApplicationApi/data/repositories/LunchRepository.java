package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.lunches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends JpaRepository<lunches, Long> {
}
