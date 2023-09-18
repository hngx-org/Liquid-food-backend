package org.hngxfreelunch.LiquidApplicationApi.Repositories;

import org.hngxfreelunch.LiquidApplicationApi.Entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {
}
