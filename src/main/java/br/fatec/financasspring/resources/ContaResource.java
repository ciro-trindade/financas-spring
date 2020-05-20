package br.fatec.financasspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fatec.financasspring.model.Conta;
import br.fatec.financasspring.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaResource implements ResourceInterface<Conta> {
	@Autowired
	private ContaService contas;
	
	
	public ContaResource() {
	}

	@Override
	@GetMapping
	public ResponseEntity<List<Conta>> get() {
		return ResponseEntity.ok(contas.findAll());
	}
	
	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Conta _conta = contas.findById(id);
		if (_conta != null) {
			return ResponseEntity.ok(_conta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	public ResponseEntity<Conta> post(Conta obj) {
		contas.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	public ResponseEntity<?> put(Conta obj) {
		if (contas.update(obj)) {
			return ResponseEntity.ok(obj);				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

	@Override
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {		
		if (contas.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
