package br.fatec.financasspring.resources;

import java.util.List;

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

import br.fatec.financasspring.model.PessoaJuridica;
import br.fatec.financasspring.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoas_juridicas")
public class PessoaJuridicaResource implements ResourceInterface<PessoaJuridica> {

	@Autowired
	private PessoaJuridicaService pjService;
	
	public PessoaJuridicaResource() {
	}

	@Override
	@GetMapping
	public ResponseEntity<List<PessoaJuridica>> get() {		
		return ResponseEntity.ok(pjService.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		PessoaJuridica _pj = pjService.findById(id);
		if (_pj != null) {
			return ResponseEntity.ok(_pj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<PessoaJuridica> post(@RequestBody PessoaJuridica obj) {
		pjService.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody PessoaJuridica obj) {
		if (pjService.update(obj)) {
			return ResponseEntity.ok(obj);				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (pjService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
