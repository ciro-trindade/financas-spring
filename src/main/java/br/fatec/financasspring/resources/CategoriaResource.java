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

import br.fatec.financasspring.model.Categoria;
import br.fatec.financasspring.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource implements ResourceInterface<Categoria> {
	@Autowired
	private CategoriaService categoriaService;

	@Override
	@GetMapping
	public ResponseEntity<List<Categoria>> get() {
		return ResponseEntity.ok(categoriaService.findAll());
	}

	@Override
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Categoria _categoria = categoriaService.findById(id);
		if (_categoria != null)
			return ResponseEntity.ok(_categoria);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Categoria> post(@RequestBody Categoria obj) {
		categoriaService.create(obj);
		return ResponseEntity.ok(obj);
	}

	@Override
	@PutMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> put(@RequestBody Categoria obj) {
		if (categoriaService.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (categoriaService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
