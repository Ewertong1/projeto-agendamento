package com.teste.pratico.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.model.Agendamento;
import com.teste.pratico.model.Solicitante;
import com.teste.pratico.model.dto.AgendamentoDTO;
import com.teste.pratico.service.AgendamentoService;
import com.teste.pratico.service.SolicitanteService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@ViewScoped
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;


	private LocalDate inicio;
	private LocalDate fim;
	private Solicitante solicitanteSelecionado; 
	private List<Agendamento> listaAgendamentos;

	private Long id;
	private LocalDate data;
	private String numero;
	private String motivo;
	private Solicitante solicitante = new Solicitante();

	@Autowired
	private AgendamentoService agendamentoService;

	@Autowired
	private SolicitanteService solicitanteService;

	private List<AgendamentoDTO> listaResumoAgendamentos = new ArrayList<>();

	// Método para salvar o agendamento
	public void salvarAgendamento() {
		try {
			Agendamento agendamento = new Agendamento();
			agendamento.setId(this.id);
			agendamento.setData(this.data);
			agendamento.setNumero(this.numero);
			agendamento.setMotivo(this.motivo);
			agendamento.setSolicitante(this.solicitante);

			Agendamento savedAgendamento = agendamentoService.salvarAgendamento(agendamento);
			System.out.println("Agendamento salvo com sucesso: " + savedAgendamento);
		} catch (IllegalArgumentException e) {
			
			System.err.println("Erro ao salvar agendamento: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro inesperado ao salvar agendamento: " + e.getMessage());
		}
	}

	
	public void consultarAgendamentos() {
		listaAgendamentos = agendamentoService.buscarAgendamentosPorPeriodo(inicio, fim,
				solicitanteSelecionado != null ? solicitanteSelecionado.getId() : null);
	}


	public List<Solicitante> sugerirSolicitantes(String query) {
		return solicitanteService.listarSolicitantes().stream()
				.filter(s -> s.getNome().toLowerCase().contains(query.toLowerCase())).toList();
	}

	
	public Converter getSolicitanteConverter() {
		return new Converter() {
			@Override
			public Object getAsObject(FacesContext context, UIComponent component, String value) {
				if (value == null || value.isEmpty()) {
					return null;
				}
				return solicitanteService.buscarPorId(Long.valueOf(value)).orElse(null);
			}

			@Override
			public String getAsString(FacesContext context, UIComponent component, Object value) {
				if (value == null) {
					return "";
				}
				return String.valueOf(((Solicitante) value).getId());
			}
		};
	}

	
	public void consultarTotalAgendamentos() {
		listaResumoAgendamentos.clear();
		listaResumoAgendamentos = agendamentoService.obterResumoAgendamentosPorPeriodo(inicio, fim,
				solicitanteSelecionado != null ? solicitanteSelecionado.getId() : null);
	}

}
