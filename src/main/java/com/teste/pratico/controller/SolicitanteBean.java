package com.teste.pratico.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.teste.pratico.model.Solicitante;
import com.teste.pratico.service.SolicitanteService;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
@Getter
@Setter
@Component
public class SolicitanteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String telefone;

    @Autowired
    private SolicitanteService solicitanteService;

    public void salvarSolicitante() {
        try {
            Solicitante solicitante = new Solicitante();
            solicitante.setNome(nome);

            solicitanteService.salvarSolicitante(solicitante);

            
            limparCampos();

        } catch (Exception e) {
            System.err.println("Erro ao salvar o solicitante: " + e.getMessage());
        }
    }

    public void limparCampos() {
        this.nome = "";
    }
}
