package br.fatec.financasspring.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fatec.financasspring.model.Conta;
import br.fatec.financasspring.model.Movimentacao;
import br.fatec.financasspring.model.TipoMovimentacao;
import br.fatec.financasspring.repositories.ContaRepository;
import br.fatec.financasspring.repositories.MovimentacaoRepository;

@Service
public class MovimentacaoService implements ServiceInterface<Movimentacao> {

	@Autowired
	private MovimentacaoRepository repo;

	@Autowired
	private ContaRepository contaRepo;

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

	public BigDecimal mediaMovimentaoesPorContaETipo(Long contaId, String tipo) {
		Optional<Conta> conta = contaRepo.findById(contaId);
		if (conta.isPresent()) {
			return repo.mediaMovimentaoesPorContaETipo(conta.get(), TipoMovimentacao.valueOf(tipo.toUpperCase()));
		}
		return null;
	}

	public Long numeroMovimentacoesPorConta(Long contaId) {
		Optional<Conta> conta = contaRepo.findById(contaId);
		if (conta.isPresent()) {
			return repo.numeroMovimentacoesPorConta(conta.get());
		}
		return null;
	}

	public BigDecimal maiorMovimentacaoPorContaETipo(Long contaId, Integer tipo) {
		Optional<Conta> conta = contaRepo.findById(contaId);
		if (conta.isPresent()) {
			return repo.maiorMovimentacaoPorContaETipo(conta.get(), TipoMovimentacao.toEnum(tipo));
		}
		return null;
	}

	public BigDecimal menorMovimentacaoPorContaETipo(Long contaId, Integer tipo) {
		Optional<Conta> conta = contaRepo.findById(contaId);
		if (conta.isPresent()) {
			return repo.menorMovimentacaoPorContaETipo(conta.get(), TipoMovimentacao.toEnum(tipo));
		}
		return null;
	}

	public BigDecimal somaMovimentacoesPorContaTipoEPeriodo(Long contaId, Integer tipo, String from, String to) {
		Optional<Conta> conta = contaRepo.findById(contaId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date inicio = dateFormat.parse(from);
			Date fim = dateFormat.parse(to);
			if (conta.isPresent()) {
				return repo.somaMovimentacoesPorContaTipoEPeriodo(conta.get(),
						TipoMovimentacao.toEnum(tipo), inicio, fim);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}
