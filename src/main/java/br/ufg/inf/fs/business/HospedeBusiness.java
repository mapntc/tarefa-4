package br.ufg.inf.fs.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.exceptions.HospedeException;
import br.ufg.inf.fs.repositories.HospedeRepository;

@Service
public class HospedeBusiness {
	
	@Autowired
	private HospedeRepository repository;
	
	public Page<Hospede> findAll(Pageable pageable){
		return repository.findAll(pageable);		
	}
	
	public Hospede findById(Integer id) {
		Optional<Hospede> retorno = repository.findById(id);
		return retorno.get();
	}
	
	public Hospede findByCpf(String cpf) {
		Optional<Hospede> hospede = repository.findByCpf(cpf);
		return hospede.get();
	}
	
	public Hospede insert(Hospede hospede) throws HospedeException {
		validaHospede(hospede);
		return repository.save(hospede);
	}
	
	public Hospede update(Hospede hospede) throws HospedeException {
		validaHospede(hospede);
		return repository.save(hospede);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	private void validaHospede(Hospede hospede) throws HospedeException {
		if (hospede.getCpf() == null || hospede.getCpf().length() != 11) {
			throw new HospedeException("0201");
		}
		if (hospede.getNmHospede() == null || hospede.getNmHospede().length() > 80) {
			throw new HospedeException("0202");
		}
	}
}
