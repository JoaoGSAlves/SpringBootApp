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
import br.com.senac.apiexemple.entity.Turma;
import br.com.senac.apiexemple.service.TurmaService;

@RestController
@RequestMapping("turmas")
public class TurmaResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TurmaService turmaService;
	
	@GetMapping
	public ResponseEntity<List<Turma>> buscarTodasTurmas() {
		List<Turma> listaTurmas = turmaService.buscarTodasTurmas();
		List<Turma> listaTurma = listaTurmas.stream().map(turma -> mapper.map(turma, Turma.class))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaTurma);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> buscarTurmaPorID(@PathVariable("id") Integer id){
		Turma turma = turmaService.getTurmaById(id);
		Turma turmaAlterada = mapper.map(turma, Turma.class);
		return ResponseEntity.ok().body(turmaAlterada);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turma> atualizarTurma(@PathVariable("id") Integer id, @RequestBody Turma Turma) {
		Turma turma = mapper.map(Turma, Turma.class);
		turma = turmaService.updateTurma(id ,turma);
		Turma turmaAlterada = mapper.map(turma, Turma.class);
		return ResponseEntity.ok().body(turmaAlterada);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirTurma(@PathVariable("id") Integer id) {
		Boolean flag = turmaService.deleteTurma(id);
		return ResponseEntity.ok().body(flag);
	}
	
	@PostMapping
	public ResponseEntity<Turma> cadastrarTurma(@RequestBody Turma Turma) {
		Turma turma = mapper.map(Turma, Turma.class);
		turma = turmaService.salvar(turma);
		Turma turmaNova = mapper.map(turma, Turma.class);
		return ResponseEntity.ok().body(turmaNova);
	}
}
