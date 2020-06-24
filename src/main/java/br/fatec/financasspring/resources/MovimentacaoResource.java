package br.fatec.financasspring.resources;

import java.math.BigDecimal;
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

import br.fatec.financasspring.model.Movimentacao;
import br.fatec.financasspring.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoResource implements ResourceInterface<Movimentacao> {

	@Autowired
	private MovimentacaoService movService;
	
	@Override
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<Movimentacao>> get() {		
		return ResponseEntity.ok(movService.findAll());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
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
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> put(@RequestBody Movimentacao obj) {
		if (movService.update(obj)) {
			return ResponseEntity.ok(obj);				
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

	@Override
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (movService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/media/{conta}/{tipo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getMediaPorContaETipo(@PathVariable("conta") Long contaId, @PathVariable("tipo") String tipo) {
		BigDecimal media = movService.mediaMovimentaoesPorContaETipo(contaId, tipo);
		if (media != null) {
			return ResponseEntity.ok(media);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping(value = "/numero/{conta}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getNumeroPorConta(@PathVariable("conta") Long contaId) {
		Long numero = movService.numeroMovimentacoesPorConta(contaId);
		if (numero != null) {
			return ResponseEntity.ok(numero);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
		
	@GetMapping(value = "/maior/{conta}/{tipo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getMaiorPorContaETipo(@PathVariable("conta") Long contaId, @PathVariable("tipo") Integer tipo) {
		BigDecimal maior = movService.maiorMovimentacaoPorContaETipo(contaId, tipo);
		if (maior != null) {
			return ResponseEntity.ok(maior);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
	
	@GetMapping(value = "/menor/{conta}/{tipo}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getMenorPorContaETipo(@PathVariable("conta") Long contaId, @PathVariable("tipo") Integer tipo) {
		BigDecimal menor = movService.menorMovimentacaoPorContaETipo(contaId, tipo);
		if (menor != null) {
			return ResponseEntity.ok(menor);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}
	
	@GetMapping(value = "/soma/{conta}/{tipo}/{from}/{to}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> getsomaPorContaTipoEPeriodo(@PathVariable("conta") Long contaId, 
			@PathVariable("tipo") Integer tipo, @PathVariable("from") String from, @PathVariable("to") String to) {
		BigDecimal soma = movService.somaMovimentacoesPorContaTipoEPeriodo(contaId, tipo, from, to);
		if (soma != null) {
			return ResponseEntity.ok(soma);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();		
	}

}
