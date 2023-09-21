package org.hngxfreelunch.LiquidApplicationApi.data.repositories;


import org.hngxfreelunch.LiquidApplicationApi.data.entities.Withdrawals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawals, Long> {
}
