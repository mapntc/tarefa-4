package br.ufg.inf.fs.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.exceptions.HospedagemException;
import br.ufg.inf.fs.repositories.HospedagemRepository;

@Service
public class HospedagemBusiness {
	
	@Autowired
	private HospedagemRepository repository;
	
	public Page<Hospedagem> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	public Hospedagem findById(Integer id) {
		Optional<Hospedagem> retorno = repository.findById(id);
		return retorno.get();
	}
	
	public Hospedagem insert(Hospedagem hospedagem) throws HospedagemException {
		validaHospedagem(hospedagem);
		return repository.save(hospedagem);
	}
	
	public Hospedagem update(Hospedagem hospedagem) throws HospedagemException {
		validaHospedagem(hospedagem);
		return repository.save(hospedagem);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	private void validaHospedagem(Hospedagem hospedagem) throws HospedagemException {
		if (hospedagem.getIdQuarto().getIdQuarto() == null) {
			throw new HospedagemException("0301");
		}
		if (hospedagem.getIdHospede().getIdHospede() == null) {
			throw new HospedagemException("0302");
		}
	}
}
