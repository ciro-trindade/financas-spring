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

import br.fatec.financasspring.model.Movimentacao;
import br.fatec.financasspring.model.PessoaFisica;
import br.fatec.financasspring.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoResource implements ResourceInterface<Movimentacao> {

	@Autowired
	private MovimentacaoService movService;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Movimentacao>> get() {		
		return ResponseEntity.ok(movService.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Movimentacao _mov = movService.findById(id);
		if (_mov != null) {
			return ResponseEntity.ok(_mov);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	public ResponseEntity<Movimentacao> post(@RequestBody Movimentacao obj) {
		movService.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Movimentacao obj) {
		if (movService.update(obj)) {
			return ResponseEntity.ok(obj);				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (movService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
