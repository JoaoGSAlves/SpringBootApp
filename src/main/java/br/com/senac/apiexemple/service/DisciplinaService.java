package br.com.senac.apiexemple.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.apiexemple.entity.Disciplina;
import br.com.senac.apiexemple.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	DisciplinaRepository repo;
	
	public Disciplina salvar(Disciplina disciplina) {
		return repo.save(disciplina);
	}

	public List<Disciplina> buscarTodasDisciplinas() {
		return repo.findAll();
	}

	public Disciplina getDisciplinaById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public Boolean deleteDisciplina(Integer id) {
		Disciplina disciplina = repo.findById(id).orElse(null);
		if (disciplina != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	public Disciplina updateDisciplina(Integer id,Disciplina disciplinaAlterado) {
		Disciplina disciplinaBD = repo.findById(id).orElse(null);
		if (disciplinaBD != null) {
			disciplinaBD.setNome(disciplinaAlterado.getNome());
			return repo.save(disciplinaBD);
		} else {
			return null;
		}
	}

}
