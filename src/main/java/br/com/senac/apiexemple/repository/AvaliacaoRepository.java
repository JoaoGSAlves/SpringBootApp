package br.com.senac.apiexemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.apiexemple.entity.AlunoDisciplina;
import br.com.senac.apiexemple.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {
	
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
}
