package com.teste.pratico.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.model.Agendamento;
import com.teste.pratico.model.Vagas;
import com.teste.pratico.model.dto.AgendamentoDTO;
import com.teste.pratico.repository.AgendamentoRepository;
import com.teste.pratico.repository.VagaRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	@Autowired
	private VagaRepository vagaRepository;

	public List<Agendamento> listarAgendamentos() {
		return agendamentoRepository.findAll();
	}

	public Optional<Agendamento> buscarPorId(Long id) {
		return agendamentoRepository.findById(id);
	}

	public Agendamento salvarAgendamento(Agendamento agendamento) {
		if (agendamento.getData().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException(
					"Não é permitido criar agendamentos com data/hora de início no passado.");
		}

		List<Agendamento> agendamentosExistentes = agendamentoRepository
				.findBySolicitanteIdAndData(agendamento.getSolicitante().getId(), agendamento.getData());
		if (!agendamentosExistentes.isEmpty()) {
			throw new IllegalArgumentException("Já existe um agendamento para este solicitante no mesmo horário.");
		}

		if (agendamento.getNumero() == null || agendamento.getNumero().isEmpty()) {
			throw new IllegalArgumentException("O número do agendamento não pode ser nulo ou vazio.");
		}

		List<Vagas> vagasDisponiveisList = vagaRepository.findVagasByPeriodo(agendamento.getData());
		if (vagasDisponiveisList.isEmpty()) {
			throw new IllegalArgumentException("Não há vagas disponíveis para o período selecionado.");
		}

		Vagas vagasDisponiveis = vagasDisponiveisList.get(0);

		long totalAgendamentosNoPeriodo = agendamentoRepository.countByDataBetween(vagasDisponiveis.getInicio(),
				vagasDisponiveis.getFim());

		if (totalAgendamentosNoPeriodo >= vagasDisponiveis.getQuantidade()) {
			throw new IllegalArgumentException("Não há vagas disponíveis para o período selecionado.");
		}

		// Verificar se o solicitante já excedeu 25% do total de vagas disponíveis
		long totalAgendamentosDoSolicitante = agendamentoRepository.countBySolicitanteIdAndDataBetween(
				agendamento.getSolicitante().getId(), vagasDisponiveis.getInicio(), vagasDisponiveis.getFim());

		long limiteMaximoAgendamentosPorSolicitante = Math.round(vagasDisponiveis.getQuantidade() * 0.25);
		if (totalAgendamentosDoSolicitante >= limiteMaximoAgendamentosPorSolicitante) {
			throw new IllegalArgumentException(
					"O solicitante excedeu o limite máximo permitido de agendamentos para este período.");
		}

		return agendamentoRepository.save(agendamento);
	}

	public List<Agendamento> findBySolicitanteIdAndData(Long solicitanteId, LocalDate data) {
		return agendamentoRepository.findBySolicitanteIdAndData(solicitanteId, data);
	}

	public void deletarAgendamento(Long id) {
		agendamentoRepository.deleteById(id);
	}

	public List<Agendamento> buscarAgendamentosPorPeriodo(LocalDate inicio, LocalDate fim, Long solicitanteId) {
		if (solicitanteId != null) {
			return agendamentoRepository.findByDataBetweenAndSolicitante_Id(inicio, fim, solicitanteId);
		} else {
			return agendamentoRepository.findByDataBetween(inicio, fim);
		}
	}

	public Long contarAgendamentosPorSolicitante(Long solicitanteId) {
		return agendamentoRepository.contarAgendamentosPorSolicitante(solicitanteId);
	}

	public List<AgendamentoDTO> obterResumoAgendamentosPorPeriodo(LocalDate inicio, LocalDate fim, Long solicitanteId) {
		List<Object[]> resultados = agendamentoRepository.findResumoAgendamentosByPeriodo(inicio, fim, solicitanteId);

		return resultados.stream().map(obj -> new AgendamentoDTO((String) obj[0], // Nome do Solicitante
				(Long) obj[1], // Total de Agendamentos
				(int) obj[2], // Quantidade de Vagas
				(Double) obj[3] // Percentual
		)).collect(Collectors.toList());

	}

}
