package com.dell.desafio.desafiosorteio.repositories;

import com.dell.desafio.desafiosorteio.entities.Draw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrawRepository extends JpaRepository<Draw, Long> {
}
