package br.com.senac.apiexemple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.apiexemple.entity.AlunoDisciplina;
import br.com.senac.apiexemple.entity.Avaliacao;
import br.com.senac.apiexemple.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	AvaliacaoRepository repo;
	
	public Avaliacao save(Avaliacao avaliacao) {
		return repo.save(avaliacao);
	}

	public List<Avaliacao> findAll(){
		return repo.findAll();
	}
	
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return repo.findByAlunoDisciplina(alunoDisciplina);
	}
}


