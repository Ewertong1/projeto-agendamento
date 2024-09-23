package com.teste.pratico.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SOLICITANTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Solicitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome do solicitante é obrigatório.")
    @Size(min = 1, message = "O nome do solicitante não pode estar vazio.")
    @Column(nullable = false)
    private String nome;
}
