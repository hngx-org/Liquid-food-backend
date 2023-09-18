package org.hngxfreelunch.LiquidApplicationApi.Repositories;

import org.hngxfreelunch.LiquidApplicationApi.Entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
