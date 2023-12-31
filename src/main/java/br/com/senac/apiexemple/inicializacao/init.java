package br.com.senac.apiexemple.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.AlunoDisciplina;
import br.com.senac.apiexemple.entity.Avaliacao;
import br.com.senac.apiexemple.entity.Disciplina;
import br.com.senac.apiexemple.entity.Professor;
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.repository.AlunoRepository;
import br.com.senac.apiexemple.repository.AvaliacaoRepository;
import br.com.senac.apiexemple.repository.DisciplinaRepository;
import br.com.senac.apiexemple.repository.ProfessorRepository;
import br.com.senac.apiexemple.repository.TurmaRepository;
import br.com.senac.apiexemple.service.AlunoService;
import br.com.senac.apiexemple.service.AvaliacaoService;
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
	@Autowired
	AvaliacaoRepository avaliacaoRepo;

	@Autowired
	AvaliacaoService avaliacaoService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// Turmas
		Turma ads = new Turma();
		ads.setNome("ADS");
		Turma rede = new Turma();
		rede.setNome("Rede");
		
		// Alunos
		Aluno aluno1 = new Aluno();
		aluno1.setNome("teste");
		aluno1.setEmail("teste@gmail.com");
		aluno1.setSobreNome("testinho");

		Aluno aluno2 = new Aluno();
		aluno2.setNome("Luca");
		aluno2.setEmail("lucadpagode@gmail.com");
		aluno2.setSobreNome("do Pagode");

		Aluno aluno3 = new Aluno();
		aluno3.setNome("Joao");
		aluno3.setEmail("joaoaugusto@gmail.com");
		aluno3.setSobreNome("Alosno");

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
		
		// Disciplinas
		Disciplina JavaWeb = new Disciplina();
		Disciplina Estrutura_de_Dados = new Disciplina();
		Disciplina mobile = new Disciplina();
		mobile.setNome("Programação Mobile");
		Disciplina arquitetura = new Disciplina();
		
		JavaWeb.setNome("Java Web");
		Estrutura_de_Dados.setNome("Estrutura de Dados");
		arquitetura.setNome("Arquitetura de Computadores");
		
		aluno1.setTurma(ads);
		aluno2.setTurma(rede);
		aluno3.setTurma(rede);
		
		aluno1.setDisciplinas(Arrays.asList(JavaWeb,Estrutura_de_Dados,arquitetura));
		aluno2.setDisciplinas(Arrays.asList(JavaWeb,arquitetura));
		aluno3.setDisciplinas(Arrays.asList(Estrutura_de_Dados));
//		List<Turma> listaTurmas = turmaService.buscarTodasTurmas();
//		listaTurmas.forEach(turma -> System.out.println("Nome da turma: " + turma.getNome()));

		turmaRepo.saveAll(Arrays.asList(ads, rede));
		disciplinaRepo.saveAll(Arrays.asList(JavaWeb, Estrutura_de_Dados, arquitetura));
		alunoRepo.saveAll(Arrays.asList(aluno1,aluno2,aluno3));
		
		Avaliacao avaliacaoAluno1 = new Avaliacao();
		Avaliacao avaliacaoAluno2 = new Avaliacao();
		Avaliacao avaliacaoAluno3 = new Avaliacao();
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		AlunoDisciplina alunoDisciplina2 = new AlunoDisciplina();
		AlunoDisciplina alunoDisciplina3 = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno1);
		alunoDisciplina2.setAluno(aluno2);
		alunoDisciplina3.setAluno(aluno3);
		alunoDisciplina.setDisciplina(arquitetura);
		alunoDisciplina2.setDisciplina(Estrutura_de_Dados);
		alunoDisciplina3.setDisciplina(JavaWeb);
		
		avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina2);
		avaliacaoAluno2.setAlunoDisciplina(alunoDisciplina);
		avaliacaoAluno3.setAlunoDisciplina(alunoDisciplina3);
		avaliacaoAluno1.setConceito("A");
		avaliacaoAluno2.setConceito("B");
		avaliacaoAluno3.setConceito("A");
		avaliacaoService.save(avaliacaoAluno1);
		avaliacaoService.save(avaliacaoAluno2);
		avaliacaoService.save(avaliacaoAluno3);
		
		Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
		System.out.println("Avaliacao: " + aval.getConceito());

	}
}