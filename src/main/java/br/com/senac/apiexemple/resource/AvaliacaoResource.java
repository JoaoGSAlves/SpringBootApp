package br.com.senac.apiexemple.resource;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senac.apiexemple.entity.Aluno;
import br.com.senac.apiexemple.entity.AlunoDisciplina;
import br.com.senac.apiexemple.entity.Avaliacao;
import br.com.senac.apiexemple.entity.Disciplina;
import br.com.senac.apiexemple.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
		
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	@PostMapping
	public ResponseEntity<UriComponents> inserir(@RequestBody Avaliacao objAvaliacao){
		
		Map<String,Object> params = new HashMap();
		
		objAvaliacao = service.save(objAvaliacao);
		UriComponentsBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentContextPath();
		uriBuilder.path("/{id}/{idDisciplina}");
		params.put("idAluno", objAvaliacao.getAlunoDisciplina().getAluno().getId());
		params.put("idDisciplina", objAvaliacao.getAlunoDisciplina().getDisciplina().getId());
		URI uri = uriBuilder.buildAndExpand(params).toUri();
		
		return ResponseEntity.created(uri).build();
		}
	
	@RequestMapping(value="/{idAluno}/{idDisciplina}", method=RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina) {
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disc = new Disciplina();
		disc.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disc);
		
		Avaliacao av = service.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(av);
	}
}
