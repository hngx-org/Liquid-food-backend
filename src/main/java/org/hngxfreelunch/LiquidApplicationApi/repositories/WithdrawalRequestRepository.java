package org.hngxfreelunch.LiquidApplicationApi.repositories;

import org.hngxfreelunch.LiquidApplicationApi.data.entities.WithdrawalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {
}
