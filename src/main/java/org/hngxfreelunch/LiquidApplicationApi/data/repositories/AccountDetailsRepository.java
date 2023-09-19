package org.hngxfreelunch.LiquidApplicationApi.data.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
