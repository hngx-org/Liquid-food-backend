package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.OrganizationInvites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationInvitesRepository extends JpaRepository<OrganizationInvites, Long> {

    Optional<OrganizationInvites> findByToken(String token);

}
