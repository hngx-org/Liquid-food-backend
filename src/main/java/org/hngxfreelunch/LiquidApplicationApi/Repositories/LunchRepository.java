package org.hngxfreelunch.LiquidApplicationApi.Repositories;

import org.hngxfreelunch.LiquidApplicationApi.Entities.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchRepository extends JpaRepository<Lunch,Long> {
}
