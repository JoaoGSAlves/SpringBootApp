package br.com.senac.apiexemple.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.Disciplina;
import br.com.senac.apiexemple.entity.Professor;
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.repository.AlunoRepository;
import br.com.senac.apiexemple.repository.DisciplinaRepository;
import br.com.senac.apiexemple.repository.ProfessorRepository;
import br.com.senac.apiexemple.repository.TurmaRepository;
import br.com.senac.apiexemple.service.AlunoService;
import br.com.senac.apiexemple.service.ProfessorService;
import br.com.senac.apiexemple.service.TurmaService;

@Component
public class init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private TurmaService turmaService;
	@Autowired
	private ProfessorService professorService;

	@Autowired
	AlunoRepository alunoRepo;
	@Autowired
	TurmaRepository turmaRepo;
	@Autowired
	ProfessorRepository professorRepo;
	@Autowired
	DisciplinaRepository disciplinaRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		// Alunos
		Aluno aluno1 = new Aluno();
		aluno1.setNome("teste");
		aluno1.setEmail("teste@gmail.com");
		aluno1.setSobreNome("testinho");
		alunoService.salvar(aluno1);

		Aluno aluno2 = new Aluno();
		aluno2.setNome("Luca");
		aluno2.setEmail("lucadpagode@gmail.com");
		aluno2.setSobreNome("do Pagode");
		alunoService.salvar(aluno2);

		Aluno aluno3 = new Aluno();
		aluno3.setNome("Joao");
		aluno3.setEmail("joaoaugusto@gmail.com");
		aluno3.setSobreNome("Alosno");
		alunoService.salvar(aluno3);

		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		listaAlunos.forEach(aluno -> System.out.println(aluno.getNome()));

//		Aluno alunoAlterado = new Aluno();
//
//		alunoAlterado.setEmail("cleber@gmail.com");
//		alunoAlterado.setNome("Cleber");
//		alunoAlterado.setSobreNome("Alonso");
//		Aluno alunoAtualizado = alunoService.updateAluno(1, alunoAlterado);
//		System.out.println("Aluno alterado: " + alunoAtualizado.getEmail());

		// Professores
		Professor professorJava = new Professor();
		professorJava.setEmail("marcelo@gmail.com");
		professorJava.setNome("Marcelo");
		professorJava.setSobreNome("Struc");

		Professor professorMobile = new Professor();
		professorMobile.setEmail("anderson@gmail.com");
		professorMobile.setNome("Anderson");
		professorMobile.setSobreNome("Santos");

		professorRepo.saveAll(Arrays.asList(professorJava, professorMobile));

		List<Professor> listaProfessores = professorService.buscarTodosProfessores();
		listaProfessores.forEach(professor -> System.out.println("Nome do professor: " + professor.getNome()));
		;

		// Turmas
		Turma ads = new Turma();
		ads.setNome("ADS");
		Turma rede = new Turma();
		rede.setNome("Rede");
		
		aluno1.setTurma(ads);
		aluno2.setTurma(rede);
		aluno3.setTurma(rede);

		List<Turma> listaTurmas = turmaService.buscarTodasTurmas();
		listaTurmas.forEach(turma -> System.out.println("Nome da turma: " + turma.getNome()));

		turmaRepo.saveAll(Arrays.asList(ads, rede));
		alunoRepo.saveAll(Arrays.asList(aluno1,aluno2,aluno3));
		

		// Disciplinas
		Disciplina JavaWeb = new Disciplina();
		JavaWeb.setNome("Java Web");
		Disciplina Estrutura_de_Dados = new Disciplina();
		Estrutura_de_Dados.setNome("Estrutura de Dados");
		Disciplina mobile = new Disciplina();
		mobile.setNome("Programação Mobile");

		disciplinaRepo.saveAll(Arrays.asList(JavaWeb, Estrutura_de_Dados));

	}
}