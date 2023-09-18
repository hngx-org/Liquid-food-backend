package org.hngxfreelunch.LiquidApplicationApi.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends JpaRepository<Lunch,Long> {
}
