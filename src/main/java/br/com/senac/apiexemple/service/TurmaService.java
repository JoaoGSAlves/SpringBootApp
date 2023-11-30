package br.com.senac.apiexemple.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository repo;
	
	public Turma salvar(Turma turma) {
		return repo.save(turma);
	}

	public List<Turma> buscarTodasTurmas() {
		return repo.findAll();
	}

	public Turma getTurmaById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
    public Turma getTurmaByName(String nome) {
        return repo.findTurmaByNome(nome);
    }
    
	public Boolean deleteTurma(Integer id) {
		Turma turma = repo.findById(id).orElse(null);
		if (turma != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	public Turma updateTurma(Integer id,Turma turmaAlterado) {
		Turma turmaBD = repo.findById(id).orElse(null);
		if (turmaBD != null) {
			turmaBD.setNome(turmaAlterado.getNome());
			return repo.save(turmaBD);
		} else {
			return null;
		}
	}

}
