package com.teste.pratico.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.model.Vagas;

@Repository
public interface VagaRepository extends JpaRepository<Vagas, Long> {
	@Query("SELECT v FROM Vagas v WHERE :data BETWEEN v.inicio AND v.fim")
	Optional<Vagas> findVagasNoPeriodo(@Param("data") LocalDate data);

	
	@Query("SELECT v FROM Vagas v WHERE :data BETWEEN v.inicio AND v.fim")
	List<Vagas> findVagasByPeriodo(@Param("data") LocalDate data);

	@Query("SELECT SUM(v.quantidade) FROM Vagas v WHERE v.inicio <= :fim AND v.fim >= :inicio")
	Integer sumVagasByPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
