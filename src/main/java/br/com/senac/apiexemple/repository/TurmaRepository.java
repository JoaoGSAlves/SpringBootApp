package br.com.senac.apiexemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.senac.apiexemple.entity.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {
	
    @Query("SELECT t FROM Turma t WHERE t.nome = :nome")
    Turma findTurmaByNome(String nome);
}
