package com.giggi.osterianapulion_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.giggi.osterianapulion_be.entity.Tavolo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TavoloRepository extends JpaRepository<Tavolo, Long> {
    List<Tavolo> findAllByMinimoPostiGreaterThanEqualAndAttivoTrue(int numeroCoperti);

    List<Tavolo> findAllByMinimoPostiLessThanEqualAndMassimoPostiGreaterThanEqualAndAttivoTrue(int coperti, int coperti2);
}