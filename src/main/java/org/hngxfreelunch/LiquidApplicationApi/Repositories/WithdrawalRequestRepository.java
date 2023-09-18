package org.hngxfreelunch.LiquidApplicationApi.Repositories;

import org.hngxfreelunch.LiquidApplicationApi.Entities.WithdrawalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {
}
