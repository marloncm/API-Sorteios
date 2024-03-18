package com.dell.desafio.desafiosorteio.repositories;

import com.dell.desafio.desafiosorteio.entities.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
