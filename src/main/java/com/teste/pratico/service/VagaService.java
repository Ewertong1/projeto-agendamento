package com.teste.pratico.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pratico.model.Vagas;
import com.teste.pratico.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
	private VagaRepository vagaRepository;

	public List<Vagas> listarVagas() {
		return vagaRepository.findAll();
	}

	public Optional<Vagas> buscarPorId(Long id) {
		return vagaRepository.findById(id);
	}

	public Vagas salvarVaga(Vagas vaga) {
		if (vaga.getInicio().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Não é permitido criar vagas com data de início retroativa.");
		}

		if (vaga.getFim().isBefore(vaga.getInicio())) {
			throw new IllegalArgumentException("A data de término não pode ser anterior à data de início.");
		}

		return vagaRepository.save(vaga);
	}

	public void deletarVaga(Long id) {
		vagaRepository.deleteById(id);
	}
}
