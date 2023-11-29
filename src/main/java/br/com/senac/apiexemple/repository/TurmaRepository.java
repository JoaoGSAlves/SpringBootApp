package br.com.senac.apiexemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senac.apiexemple.entity.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
