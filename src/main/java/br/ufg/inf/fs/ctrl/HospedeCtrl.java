package br.ufg.inf.fs.ctrl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.HospedeBusiness;
import br.ufg.inf.fs.entities.Hospede;
import br.ufg.inf.fs.exceptions.HospedeException;

@RestController
@RequestMapping(value="hospedes")
public class HospedeCtrl {

	@Autowired
	private HospedeBusiness business;
	
	@GetMapping
	public ResponseEntity<Page<Hospede>> findAll(@PageableDefault(sort = "idHospede", direction = Direction.ASC, page = 0, size = 3) Pageable pageable){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		Page<Hospede> list = new PageImpl<>(Collections.EMPTY_LIST);
		try {
			list = business.findAll(pageable);
			if(list.getSize() == 0) {
				headers.add("message", Messages.get("0208"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Page<Hospede>>(list, headers, status);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospede> findById(@PathVariable Integer id){
		Hospede retorno = new Hospede();

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findById(id);
			if(retorno == null) {
				headers.add("message", Messages.get("0209"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Hospede>(retorno, headers, status);
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Hospede> findByCpf(@PathVariable String cpf){
		Hospede retorno = new Hospede();

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findByCpf(cpf);
			if(retorno == null) {
				headers.add("message", Messages.get("0210"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Hospede>(retorno, headers, status);
	}
	
	@PostMapping
	public ResponseEntity<Hospede> insert(@RequestBody Hospede hospede){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			hospede = business.insert(hospede);
			headers.add("message", Messages.get("0205"));
		} catch (HospedeException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0206"));
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Hospede>(hospede, headers, status);
	}
	
	@PutMapping
	public ResponseEntity<Hospede> update(@RequestBody Hospede hospede){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			hospede = business.update(hospede);
			headers.add("message", Messages.get("0203"));
		} catch (HospedeException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0204"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Hospede>(hospede, headers, status);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			business.delete(id);
		} catch (Exception e) {
			headers.add("message", Messages.get("0207"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(headers, status);
	}
	
}
