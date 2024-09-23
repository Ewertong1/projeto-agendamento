package com.teste.pratico.controller;

import com.teste.pratico.model.Solicitante;
import com.teste.pratico.service.SolicitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitantes")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitanteService;

    @GetMapping
    public List<Solicitante> listarSolicitantes() {
        return solicitanteService.listarSolicitantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitante> buscarSolicitantePorId(@PathVariable Long id) {
        Optional<Solicitante> solicitante = solicitanteService.buscarPorId(id);
        return solicitante.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/solicitantes")
    public Solicitante criarSolicitante(@RequestBody Solicitante solicitante) {
        return solicitanteService.salvarSolicitante(solicitante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSolicitante(@PathVariable Long id) {
        solicitanteService.deletarSolicitante(id);
        return ResponseEntity.noContent().build();
    }
}
