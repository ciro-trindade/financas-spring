package br.fatec.financasspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financasspring.model.PessoaJuridica;
import br.fatec.financasspring.repositories.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService implements ServiceInterface<PessoaJuridica> {

	@Autowired
	private PessoaJuridicaRepository repo;
	
	public PessoaJuridicaService() {
	}
	
	@Override
	public PessoaJuridica create(PessoaJuridica obj) {
		repo.save(obj);
		return obj;
	}

	@Override
	public PessoaJuridica findById(Long id) {
		Optional<PessoaJuridica> _pj = repo.findById(id);
		return _pj.orElse(null);
	}

	@Override
	public List<PessoaJuridica> findAll() {
		return repo.findAll();
	}

	@Override
	public boolean update(PessoaJuridica obj) {
		if (repo.existsById(obj.getId())) {
			repo.save(obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		Optional<PessoaJuridica> _pj = repo.findById(id);
		if (_pj.isPresent()) {
			repo.delete(_pj.get());
			return true;
		}
		return false;
	}

}
