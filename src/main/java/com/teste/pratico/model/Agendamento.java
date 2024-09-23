package com.teste.pratico.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data do agendamento é obrigatória.")
    private LocalDate data;

    @NotNull(message = "O número do agendamento é obrigatório.")
    @Size(min = 1, message = "O número do agendamento não pode estar vazio.")
    private String numero;

    @NotNull(message = "O motivo do agendamento é obrigatório.")
    @Size(min = 1, message = "O motivo do agendamento não pode estar vazio.")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "solicitante_id", nullable = false)
    @NotNull(message = "O solicitante do agendamento é obrigatório.")
    private Solicitante solicitante;
    


}
