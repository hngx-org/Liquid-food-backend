package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.organization_invites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface organization_invitesRepository extends JpaRepository<organization_invites, Long> {
}
