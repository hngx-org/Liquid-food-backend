package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    boolean existsByOrganizationName(String organizationName);
}