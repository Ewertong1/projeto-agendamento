package com.teste.pratico.service;

import com.teste.pratico.model.Solicitante;
import com.teste.pratico.repository.SolicitanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitanteService {

	@Autowired
	private SolicitanteRepository solicitanteRepository;

	public List<Solicitante> listarSolicitantes() {
		return solicitanteRepository.findAll();
	}

	public Optional<Solicitante> buscarPorId(Long id) {
		return solicitanteRepository.findById(id);
	}

	public Solicitante salvarSolicitante(Solicitante solicitante) {
		return solicitanteRepository.save(solicitante);
	}

	public void deletarSolicitante(Long id) {
		solicitanteRepository.deleteById(id);
	}
}
