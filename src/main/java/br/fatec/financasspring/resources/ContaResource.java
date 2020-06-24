package br.fatec.financasspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PreAuthorize("hasAnyRole('ADMIN')")
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
	@PostMapping
	public ResponseEntity<Conta> post(@RequestBody Conta obj) {
		contas.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping	
	public ResponseEntity<?> put(@RequestBody Conta obj) {
		if (contas.update(obj)) {
			return ResponseEntity.ok(obj);				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {		
		if (contas.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/banco/{banco}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getByBanco(
	                @PathVariable("banco") String banco) {
	   return ResponseEntity.ok(
	                 contas.listarPorBanco(banco));
	}
	
	@GetMapping(value = "/banco/{banco}/{from}/{to}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getByBancoENumero(
	         @PathVariable("banco") String banco,
	         @PathVariable("from") Integer from, 
	         @PathVariable("to") Integer to) {
	   return ResponseEntity.ok(
	      contas.listarPorBancoENumero(banco, from, to));
	}

	@GetMapping(value = "/cliente/{nome}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getByNomeCliente(
	              @PathVariable("nome") String nome) {
	   return ResponseEntity.ok(
	                 contas.listarPorNomeCliente(nome));
	}
}
