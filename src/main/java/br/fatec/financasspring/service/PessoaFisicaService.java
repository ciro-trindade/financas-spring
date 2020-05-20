package br.fatec.financasspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financasspring.model.PessoaFisica;
import br.fatec.financasspring.repositories.PessoaFisicaRepository;

@Service
public class PessoaFisicaService implements ServiceInterface<PessoaFisica> {

	@Autowired
	private PessoaFisicaRepository repo;
	
	
	public PessoaFisicaService() {
	}

	@Override
	public PessoaFisica create(PessoaFisica obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public PessoaFisica findById(Long id) {
		Optional<PessoaFisica> _pf = repo.findById(id);
		return _pf.orElse(null);
	}

	@Override
	public List<PessoaFisica> findAll() {
		return repo.findAll();
	}

	@Override
	public boolean update(PessoaFisica obj) {
		if (repo.existsById(obj.getId())) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<PessoaFisica> _pf = repo.findById(id);
		if (_pf.isPresent()) {
			repo.delete(_pf.get());
			return true;
		}
		return false;
	}


}
