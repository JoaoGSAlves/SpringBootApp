package br.com.senac.apiexemple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.apiexemple.entity.Professor;
import br.com.senac.apiexemple.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository repo;

	public Professor salvar(Professor professor) {
		return repo.save(professor);
	}

	public List<Professor> buscarTodosProfessores() {
		return repo.findAll();
	}

	public Professor getProfessorById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public Boolean deleteProfessor(Integer id) {
		Professor professor = repo.findById(id).orElse(null);
		if (professor != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	public Professor updateProfessor(Integer id,Professor professorAlterado) {
		Professor professorBD = repo.findById(id).orElse(null);
		if (professorBD != null) {
			professorBD.setNome(professorAlterado.getNome());
			professorBD.setEmail(professorAlterado.getEmail());
			professorBD.setSobreNome(professorAlterado.getSobreNome());
			return repo.save(professorBD);
		} else {
			return null;
		}
	}

}
