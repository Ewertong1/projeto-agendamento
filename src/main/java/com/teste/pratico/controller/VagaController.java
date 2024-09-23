package com.teste.pratico.controller;

import com.teste.pratico.model.Vagas;
import com.teste.pratico.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public List<Vagas> listarVagas() {
        return vagaService.listarVagas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vagas> buscarVagaPorId(@PathVariable Long id) {
        Optional<Vagas> vaga = vagaService.buscarPorId(id);
        return vaga.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vagas> criarVaga(@RequestBody Vagas vaga) {
        Vagas novaVaga = vagaService.salvarVaga(vaga);
        return ResponseEntity.ok(novaVaga);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }
}