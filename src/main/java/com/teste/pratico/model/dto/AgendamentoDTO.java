package com.teste.pratico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO {
    private String nomeSolicitante; 
    private Long totalAgendamentos; 
    private int  totalVagas;        
    private double percentual;      
}
