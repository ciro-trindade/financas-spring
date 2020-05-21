package br.fatec.financasspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financasspring.model.Movimentacao;
import br.fatec.financasspring.repositories.MovimentacaoRepository;

@Service
public class MovimentacaoService implements ServiceInterface<Movimentacao> {

	@Autowired
	private MovimentacaoRepository repo;
	
	@Override
	public Movimentacao create(Movimentacao obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public Movimentacao findById(Long id) {
		Optional<Movimentacao> _mov = repo.findById(id);
		return _mov.orElse(null);
	}

	@Override
	public List<Movimentacao> findAll() {
		return repo.findAll();
	}

	@Override
	public boolean update(Movimentacao obj) {
		if (repo.existsById(obj.getId())) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<Movimentacao> _mov = repo.findById(id);
		if (_mov.isPresent()) {
			repo.delete(_mov.get());
			return true;
		}
		return false;
	}

}
