package br.com.senac.apiexemple.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.repository.AlunoRepository;
import br.com.senac.apiexemple.repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository repo;

	@Autowired
	TurmaService turmaService;

	@Autowired
	TurmaRepository turmaRepository;

	public Aluno salvar(Aluno aluno) {
		Turma turma = aluno.getTurma();
		if (turma != null) {
			Turma turmaExistente = turmaRepository.findTurmaByNome(turma.getNome());
			aluno.setTurma(turmaExistente); 
			return repo.save(aluno);
		} else { 
			throw new EntityNotFoundException("Turma não encontrada com o nome: "
			+ aluno.getTurma().getNome());
		}
	}

	public List<Aluno> buscarTodosAlunos() {
		return repo.findAll();
	}

	public Aluno getAlunoById(Integer id) throws ObjectNotFoundException {
		Optional<Aluno> aluno = repo.findById(id);
		return aluno.orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com o ID: " + id));
	}

	public Boolean deleteAluno(Integer id) {
		Aluno aluno = repo.findById(id).orElse(null);
		if (aluno != null) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	public Aluno atualizarAluno(Integer id, Aluno alunoAtualizado) {
		Optional<Aluno> alunoExistenteOptional = repo.findById(id);

		if (alunoExistenteOptional.isPresent()) {
			Aluno alunoAtual = alunoExistenteOptional.get();
			alunoAtual.setNome(alunoAtualizado.getNome());
			alunoAtual.setSobreNome(alunoAtualizado.getSobreNome());
			alunoAtual.setEmail(alunoAtualizado.getEmail());
			alunoAtual.setDisciplinas(alunoAtualizado.getDisciplinas());

			Turma turmaExistente = turmaService.getTurmaByName(alunoAtualizado.getTurma().getNome());
			if (turmaExistente == null) {
				throw new EntityNotFoundException("Turma não encontrada com o ID: " + id);
			}
			alunoAtual.setTurma(turmaExistente);

			repo.save(alunoAtual);
			return alunoAtual;
		} else {
			throw new EntityNotFoundException("Aluno não encontrado com o ID: " + id);
		}
	}
}
