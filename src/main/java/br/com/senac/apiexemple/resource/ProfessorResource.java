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

import br.com.senac.apiexemple.contants.Messages;
import br.com.senac.apiexemple.entity.Professor;
import br.com.senac.apiexemple.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Professores")
@RestController
@RequestMapping("professores")
public class ProfessorResource {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProfessorService professorService;
	
	
	@Operation(description = Messages.SWAGGER_POST )
	@PostMapping
	public ResponseEntity<Professor> cadastrarAluno(@RequestBody Professor Professor) {
		Professor professor = mapper.map(Professor, Professor.class);
		professor = professorService.salvar(professor);
		Professor professorNovo = mapper.map(professor, Professor.class);
		return ResponseEntity.ok().body(professorNovo);

	}
	
	@Operation(description = Messages.SWAGGER_GET_ALL )
	@GetMapping
	public ResponseEntity<List<Professor>> buscarTodosAluno() {
		List<Professor> listaProfessores = professorService.buscarTodosProfessores();
		List<Professor> listaProfessor = listaProfessores.stream().map(aluno -> mapper.map(aluno, Professor.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaProfessor);
	}

	@Operation(description = Messages.SWAGGER_GET)
	@GetMapping("/{id}")
	public ResponseEntity<Professor> buscarAlunoPorID(@PathVariable("id") Integer id) {
		Professor professor = professorService.getProfessorById(id);
		Professor Professor = mapper.map(professor, Professor.class);
		return ResponseEntity.ok().body(Professor);
	}	

	@Operation(description = Messages.SWAGGER_PUT)
	@PutMapping("/{id}")
	public ResponseEntity<Professor> atualizarProfessor(@PathVariable("id") Integer id,
			@RequestBody Professor Professor) {
		Professor professor = mapper.map(Professor, Professor.class);
		professor = professorService.updateProfessor(id,professor);
		Professor professorAlteradoDTO = mapper.map(professor, Professor.class);
		return ResponseEntity.ok().body(professorAlteradoDTO);

	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirProfessor(@PathVariable("id") Integer id) {
		Boolean flag = professorService.deleteProfessor(id);
		return ResponseEntity.ok().body(flag);
	}
}
