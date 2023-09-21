package br.com.senac.apiexemple.resource;


import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
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

import br.com.senac.apiexemple.entity.Professor;
import br.com.senac.apiexemple.dto.ProfessorDTO;
import br.com.senac.apiexemple.service.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<ProfessorDTO> cadastrarAluno(@RequestBody ProfessorDTO professorDTO) {
		
		Professor professor = mapper.map(professorDTO, Professor.class);
		
		professor = professorService.salvar(professor);
		
		ProfessorDTO professorNovo = mapper.map(professor,ProfessorDTO.class);
		
		return ResponseEntity.ok().body(professorNovo);
		
	}
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> buscarTodosAluno(){
		List<Professor> listaProfessores = professorService.buscarTodosProfessores();
		
		List<ProfessorDTO> listaProfessorDTO = listaProfessores.stream().map(aluno -> 
		mapper.map(aluno, ProfessorDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaProfessorDTO);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> buscarAlunoPorID(@PathVariable("id") Integer id){
		Professor professor = professorService.getProfessorById(id);
		ProfessorDTO professorDTO = mapper.map(professor, ProfessorDTO.class);
		return ResponseEntity.ok().body(professorDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessorDTO> atualizarProfessor(@PathVariable("id") Integer id, @RequestBody ProfessorDTO professorDTO){
		
		Professor professor = mapper.map(professorDTO, Professor.class);
		
		professor = professorService.updateProfessor(id, professor);
		
		ProfessorDTO professorAlteradoDTO = mapper.map(professor, ProfessorDTO.class);
		
		return ResponseEntity.ok().body(professorAlteradoDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirProfessor(@PathVariable("id") Integer id){
		Boolean flag = professorService.deleteProfessor(id);
		return ResponseEntity.ok().body(flag);
	}
}
