package com.teste.pratico.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teste.pratico.model.Agendamento;
import com.teste.pratico.model.dto.AgendamentoDTO;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
	List<Agendamento> findByDataBetween(LocalDate inicio, LocalDate fim);

	List<Agendamento> findByDataBetweenAndSolicitante_Id(LocalDate inicio, LocalDate fim, Long solicitanteId);

	@Query("SELECT COUNT(a) FROM Agendamento a WHERE a.solicitante.id = :solicitanteId")
	Long contarAgendamentosPorSolicitante(@Param("solicitanteId") Long solicitanteId);

	@Query("SELECT a FROM Agendamento a WHERE a.solicitante.id = :solicitanteId AND a.data = :data")
	List<Agendamento> findBySolicitanteIdAndData(@Param("solicitanteId") Long solicitanteId,
			@Param("data") LocalDate data);

	@Query("SELECT COUNT(a) FROM Agendamento a WHERE a.data BETWEEN :inicio AND :fim")
	long countByDataBetween(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

	@Query("SELECT COUNT(a) FROM Agendamento a WHERE a.solicitante.id = :solicitanteId AND a.data BETWEEN :inicio AND :fim")
	Long countBySolicitanteIdAndDataBetween(@Param("solicitanteId") Long solicitanteId,
			@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

	@Query("SELECT s.nome, COUNT(a), v.quantidade, (COUNT(a) * 100.0 / v.quantidade) " + "FROM Solicitante s "
			+ "LEFT JOIN Agendamento a ON s.id = a.solicitante.id "
			+ "JOIN Vagas v ON a.data BETWEEN v.inicio AND v.fim " + "WHERE (:inicio IS NULL OR a.data >= :inicio) "
			+ "AND (:fim IS NULL OR a.data <= :fim) " + "AND (:solicitanteId IS NULL OR s.id = :solicitanteId) "
			+ "GROUP BY s.nome, v.quantidade")
	List<Object[]> findResumoAgendamentosByPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim,
			@Param("solicitanteId") Long solicitanteId);

}
