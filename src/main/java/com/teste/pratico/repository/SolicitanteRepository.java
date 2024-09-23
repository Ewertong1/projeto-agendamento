package com.teste.pratico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.pratico.model.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long> {
}
