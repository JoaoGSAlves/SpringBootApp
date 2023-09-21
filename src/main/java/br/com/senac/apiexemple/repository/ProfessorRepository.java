package br.com.senac.apiexemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
