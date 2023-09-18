package org.hngxfreelunch.LiquidApplicationApi.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
