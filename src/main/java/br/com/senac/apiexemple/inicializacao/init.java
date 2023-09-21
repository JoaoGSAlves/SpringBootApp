package br.com.senac.apiexemple.inicializacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.service.AlunoService;

@Component
public class init implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AlunoService alunoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
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
		
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		listaAlunos.forEach(aluno -> System.out.println(aluno.getNome()));
		
		Aluno alunoAlterado = new Aluno();
		
		alunoAlterado.setEmail("lucca@gmail.com");
		alunoAlterado.setNome("Lucca");
		alunoAlterado.setSobreNome("Motta");
		Aluno alunoAtualizado = alunoService.updateAluno(1, alunoAlterado);
		System.out.println("Aluno alterado: " + alunoAtualizado.getEmail());
	}
}