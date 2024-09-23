package com.teste.pratico.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VAGAS")
public class Vagas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data de início é obrigatória.")
    @FutureOrPresent(message = "A data de início deve ser uma data futura.")
    private LocalDate inicio;

    @NotNull(message = "A data de fim é obrigatória.")
    @Future(message = "A data de fim deve ser uma data futura.")
    private LocalDate fim;

    @NotNull(message = "A quantidade de vagas é obrigatória.")
    @Min(value = 1, message = "A quantidade de vagas deve ser pelo menos 1.")
    private Integer quantidade;
}
