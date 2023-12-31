package br.com.senac.apiexemple.resource;

import java.util.List;
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

import br.com.senac.apiexemple.contants.Messages;
import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.service.AlunoService;
import br.com.senac.apiexemple.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Alunos")
@RestController
@RequestMapping("alunos")
public class AlunoResource {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	TurmaService turmaService;
	
	@Operation(description = Messages.SWAGGER_POST )
	@PostMapping
	public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {
	    aluno = alunoService.salvar(aluno);
	    Aluno alunoSalvo =  mapper.map(aluno, Aluno.class);  
	    return ResponseEntity.ok().body(alunoSalvo);
	}

	// @RequestMapping(method = RequestMethod.GET) -- Forma antiga de fazer
	@Operation(description = Messages.SWAGGER_GET_ALL )
	@GetMapping
	public ResponseEntity<List<Aluno>> buscarTodosAluno() {
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		List<Aluno> listaAluno = listaAlunos.stream().map(aluno -> mapper.map(aluno, Aluno.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaAluno);
	}
	
	@Operation(description = Messages.SWAGGER_GET )
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarAlunoPorID(@PathVariable("id") Integer id) {
		Aluno alunoOptional = alunoService.getAlunoById(id);
		if (alunoOptional == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(alunoService.getAlunoById(id));
	}
	
	@Operation(description = Messages.SWAGGER_PUT)
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizarAluno(@PathVariable("id") Integer id, @RequestBody Aluno alunoAtualizado) {
		Aluno alunoOptional = alunoService.getAlunoById(id);
		if (alunoOptional == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
		}
		alunoAtualizado.setId(alunoOptional.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.atualizarAluno( alunoAtualizado.getId(),alunoAtualizado));
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirAluno(@PathVariable("id") Integer id) {
		Boolean flag = alunoService.deleteAluno(id);
		return ResponseEntity.ok().body(flag);
	}
}