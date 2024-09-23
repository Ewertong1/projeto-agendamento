package com.teste.pratico.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teste.pratico.model.Agendamento;
import com.teste.pratico.model.dto.AgendamentoDTO;
import com.teste.pratico.service.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping
	public List<Agendamento> listarAgendamentos() {
		return agendamentoService.listarAgendamentos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable Long id) {
		Optional<Agendamento> agendamento = agendamentoService.buscarPorId(id);
		return agendamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> criarAgendamento(@Valid @RequestBody Agendamento agendamento) {
		try {
			Agendamento novoAgendamento = agendamentoService.salvarAgendamento(agendamento);
			return ResponseEntity.ok(novoAgendamento);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
		agendamentoService.deletarAgendamento(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/consulta")
	public ResponseEntity<List<Agendamento>> buscarAgendamentosPorPeriodo(
			@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate inicio,
			@RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fim,
			@RequestParam(value = "solicitante", required = false) Long solicitanteId) {

		List<Agendamento> agendamentos = agendamentoService.buscarAgendamentosPorPeriodo(inicio, fim, solicitanteId);

		return ResponseEntity.ok(agendamentos);
	}

	@GetMapping("/total-por-solicitante")
	public ResponseEntity<Long> contarAgendamentosPorSolicitante(@RequestParam("solicitanteId") Long solicitanteId) {
		Long totalAgendamentos = agendamentoService.contarAgendamentosPorSolicitante(solicitanteId);
		return ResponseEntity.ok(totalAgendamentos);
	}

	@GetMapping("/resumo")
	public ResponseEntity<List<AgendamentoDTO>> obterResumoAgendamentos(
			@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate inicio,
			@RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate fim,
			@RequestParam("solicitante") Long solicitanteId) {

		List<AgendamentoDTO> resumo = agendamentoService.obterResumoAgendamentosPorPeriodo(inicio, fim, solicitanteId);
		return ResponseEntity.ok(resumo);
	}

}