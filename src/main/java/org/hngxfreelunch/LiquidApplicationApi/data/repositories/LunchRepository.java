package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LunchRepository extends JpaRepository<Lunches, Long> {
}
