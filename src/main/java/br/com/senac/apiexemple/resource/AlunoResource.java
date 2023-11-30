package br.com.senac.apiexemple.resource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.service.AlunoService;
import br.com.senac.apiexemple.service.TurmaService;

@RestController
@RequestMapping("alunos")
public class AlunoResource {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	TurmaService turmaService;

	@PostMapping
	public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno Aluno) {
		Aluno aluno = mapper.map(Aluno, Aluno.class);
		aluno = alunoService.salvar(aluno);
		Aluno alunoNovo = mapper.map(aluno, Aluno.class);
		return ResponseEntity.ok().body(alunoNovo);
	}

	// @RequestMapping(method = RequestMethod.GET) -- Forma antiga de fazer
	@GetMapping
	public ResponseEntity<List<Aluno>> buscarTodosAluno() {
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		List<Aluno> listaAluno = listaAlunos.stream().map(aluno -> mapper.map(aluno, Aluno.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaAluno);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarAlunoPorID(@PathVariable("id") Integer id) {
		Optional<Aluno> alunoOptional = alunoService.getAlunoById(id);
		if (alunoOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAlunoById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarAluno(@PathVariable("id") Integer id, @RequestBody Aluno alunoAtualizado) {
		Optional<Aluno> alunoOptional = alunoService.getAlunoById(id);
		if (alunoOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
		}
		turmaService.salvar(alunoAtualizado.getTurma());
		alunoAtualizado.setId(alunoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvar(alunoAtualizado)); 
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirAluno(@PathVariable("id") Integer id) {
		Boolean flag = alunoService.deleteAluno(id);
		return ResponseEntity.ok().body(flag);
	}
}
