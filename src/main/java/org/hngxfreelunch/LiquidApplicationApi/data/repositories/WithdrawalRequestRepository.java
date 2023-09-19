package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.WithdrawalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {
}
