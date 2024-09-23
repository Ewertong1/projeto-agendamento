package com.teste.pratico.controller;

import java.io.Serializable;
import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.model.Vagas;
import com.teste.pratico.service.VagaService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Component // Esta anotação permite que o Spring gerencie o bean
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VagaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private VagaService vagaService;

	private Long id;
	private LocalDate dataInicio;
	private LocalDate dataTermino;
	private int quantidade;



	public void salvarVaga() {
		try {
			Vagas vaga = new Vagas();
			vaga.setInicio(this.dataInicio);
			vaga.setFim(this.dataTermino);
			vaga.setQuantidade(this.quantidade);

			vagaService.salvarVaga(vaga); 
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Vaga salva com sucesso!", null));
		} catch (ConstraintViolationException e) {
			for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, violation.getMessage(), null));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a vaga.", null));
		}
	}
}
