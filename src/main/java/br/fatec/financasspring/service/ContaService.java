package br.fatec.financasspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financasspring.model.Conta;
import br.fatec.financasspring.repositories.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepo;
	
	public ContaService() {}
	
	public void add(Conta conta) {
		contaRepo.save(conta);
	}
	
	public List<Conta> getAll() {
		return contaRepo.findAll();
	}
	
	/*
	public List<Conta> getByBank(String banco) {
		List<Conta> _contas = new ArrayList<>();
		for (Conta c : contas) {
			if (c.getBanco().equalsIgnoreCase(banco)) {
				_contas.add(c);
			}
		}
		return _contas;
	}
	
	public List<Conta> getByTitular(String titular) {
		List<Conta> _contas = new ArrayList<>();
		titular = titular.toLowerCase();
		for (Conta c : contas) {
			if (c.getTitular().toLowerCase().startsWith(titular)) {
				_contas.add(c);
			}
		}
		return _contas;
	}
	*/
	
	public Conta get(Conta conta) {
		Optional<Conta> _conta = contaRepo.findById(conta.getId());
		return _conta.orElse(null);
	}

	public Conta get(Long id) {
		return get(new Conta(id));
	}

	public boolean update(Conta conta) {
		if (contaRepo.existsById(conta.getId())) {
			contaRepo.save(conta);
			return true;
		}
		return false;
	}
	
	public boolean delete(Long id) {
		Optional<Conta> _conta = contaRepo.findById(id);
		if (_conta.isPresent()) {
			contaRepo.delete(_conta.get());
			return true;
		}
		return false;
	}
	
}

