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
import br.com.senac.apiexemple.entity.Disciplina;
import br.com.senac.apiexemple.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Disciplinas")
@RestController
@RequestMapping("disciplinas")
public class DisciplinaResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL )
	@GetMapping
	public ResponseEntity<List<Disciplina>> buscarTodasDisciplinas() {
		List<Disciplina> listaDisciplinas = disciplinaService.buscarTodasDisciplinas();
		List<Disciplina> listaDisciplina = listaDisciplinas.stream().map(disciplina -> mapper.map(disciplina, Disciplina.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDisciplina);
	}
	
	@Operation(description = Messages.SWAGGER_GET)
	@GetMapping("/{id}")
	public ResponseEntity<Disciplina> buscarDisciplinaPorID(@PathVariable("id") Integer id){
		Disciplina disciplina = disciplinaService.getDisciplinaById(id);
		Disciplina disciplinaAlterada = mapper.map(disciplina, Disciplina.class);
		return ResponseEntity.ok().body(disciplinaAlterada);
	}
	
	@Operation(description = Messages.SWAGGER_PUT)
	@PutMapping("/{id}")
	public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable("id") Integer id, @RequestBody Disciplina Disciplina) {
		Disciplina disciplina = mapper.map(Disciplina, Disciplina.class);
		disciplina = disciplinaService.updateDisciplina(id, disciplina);
		Disciplina disciplinaAlterada = mapper.map(disciplina, Disciplina.class);
		return ResponseEntity.ok().body(disciplinaAlterada);
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirDisciplina(@PathVariable("id") Integer id) {
		Boolean flag = disciplinaService.deleteDisciplina(id);
		return ResponseEntity.ok().body(flag);
	}
	
	@Operation(description = Messages.SWAGGER_POST)
	@PostMapping
	public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody Disciplina Disciplina) {
		Disciplina disciplina = mapper.map(Disciplina, Disciplina.class);
		disciplina = disciplinaService.salvar(disciplina);
		Disciplina disciplinaNova = mapper.map(disciplina, Disciplina.class);
		return ResponseEntity.ok().body(disciplinaNova);
	}
}
