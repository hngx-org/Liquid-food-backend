package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organizations;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface LunchRepository extends JpaRepository<Lunches, Long> {
    List<Lunches> findAllBySenderOrReceiver(User user, User user1);

    List<Lunches> findAllByOrganization(Organizations organizations);
}