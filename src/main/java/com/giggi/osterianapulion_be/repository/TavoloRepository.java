package com.giggi.osterianapulion_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.osterianapulion_be.entity.Tavolo;
import org.springframework.stereotype.Repository;

@Repository
public interface TavoloRepository extends JpaRepository<Tavolo, Long> {
}