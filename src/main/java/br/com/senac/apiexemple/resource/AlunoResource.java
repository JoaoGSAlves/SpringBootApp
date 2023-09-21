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

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.dto.AlunoDTO;
import br.com.senac.apiexemple.service.AlunoService;

@RestController
@RequestMapping("alunos")
public class AlunoResource {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private AlunoService alunoService;
	
	@PostMapping
	public ResponseEntity<AlunoDTO> cadastrarAluno(@RequestBody AlunoDTO alunoDTO) {
		
		Aluno aluno = mapper.map(alunoDTO, Aluno.class);
		
		aluno = alunoService.salvar(aluno);
		
		AlunoDTO alunoNovo = mapper.map(aluno,AlunoDTO.class);
		
		return ResponseEntity.ok().body(alunoNovo);
		
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> buscarTodosAluno(){
		List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
		
		List<AlunoDTO> listaAlunoDTO = listaAlunos.stream().map(aluno -> 
		mapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaAlunoDTO);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarAlunoPorID(@PathVariable("id") Integer id){
		Aluno aluno = alunoService.getAlunoById(id);
		AlunoDTO alunoDTO = mapper.map(aluno, AlunoDTO.class);
		return ResponseEntity.ok().body(alunoDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoDTO> atualizarAluno(@PathVariable("id") Integer id, @RequestBody AlunoDTO alunoDTO){
		
		Aluno aluno = mapper.map(alunoDTO, Aluno.class);
		
		aluno = alunoService.updateAluno(id, aluno);
		
		AlunoDTO alunoAlteradoDTO = mapper.map(aluno, AlunoDTO.class);
		
		return ResponseEntity.ok().body(alunoAlteradoDTO);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> excluirAluno(@PathVariable("id") Integer id){
		Boolean flag = alunoService.deleteAluno(id);
		return ResponseEntity.ok().body(flag);
	}
}
