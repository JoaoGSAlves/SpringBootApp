package br.com.senac.apiexemple.resource;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.senac.apiexemple.service.AlunoService;

@RestController
@RequestMapping("alunos")
public class AlunoResource {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlunoService alunoService;

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
	public ResponseEntity<Aluno> buscarAlunoPorID(@PathVariable("id") Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		Aluno Aluno = mapper.map(aluno, Aluno.class);
		return ResponseEntity.ok().body(Aluno);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Aluno> atualizarAluno(@PathVariable("id") Integer id, @RequestBody Aluno Aluno) {
		Aluno aluno = mapper.map(Aluno, Aluno.class);
		aluno = alunoService.updateAluno(id, aluno);
		Aluno alunoAlteradoDTO = mapper.map(aluno, Aluno.class);
		return ResponseEntity.ok().body(alunoAlteradoDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirAluno(@PathVariable("id") Integer id) {
		Boolean flag = alunoService.deleteAluno(id);
		return ResponseEntity.ok().body(flag);
	}
}
