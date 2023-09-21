package br.com.senac.apiexemple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	AlunoRepository repo;
	
	public Aluno salvar(Aluno aluno) {
		
		return repo.save(aluno);
		
	}
	
	public List<Aluno> buscarTodosAlunos() {
		return repo.findAll();
	}
	
	public Aluno getAlunoById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	public Boolean deleteAluno(Integer id) {
		Aluno aluno =  repo.findById(id).orElse(null);
		if (aluno != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	public Aluno updateAluno(Integer id, Aluno alunoAlterado) {
		Aluno alunoBD = repo.findById(id).orElse(null);
		if(alunoBD != null) {
			alunoBD.setNome(alunoAlterado.getNome());
			alunoBD.setEmail(alunoAlterado.getEmail());
			alunoBD.setSobreNome(alunoAlterado.getSobreNome());
			return repo.save(alunoBD);
		} else {
			return null;
		}
	}
	
}
