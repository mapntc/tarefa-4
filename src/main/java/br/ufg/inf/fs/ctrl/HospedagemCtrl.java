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
import br.ufg.inf.fs.business.HospedagemBusiness;
import br.ufg.inf.fs.entities.Hospedagem;
import br.ufg.inf.fs.exceptions.HospedagemException;

@RestController
@RequestMapping(value="hospedagens")
public class HospedagemCtrl {

	@Autowired
	private HospedagemBusiness business;
	
	@GetMapping
	public ResponseEntity<Page<Hospedagem>> findAll(@PageableDefault(sort = "idHospedagem", direction = Direction.ASC, page = 0, size = 3) Pageable pageable){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		Page<Hospedagem> list = new PageImpl<>(Collections.EMPTY_LIST);
		try {
			list = business.findAll(pageable);
			if(list.getSize() == 0) {
				headers.add("message", Messages.get("0308"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Page<Hospedagem>>(list, headers, status);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hospedagem> findById(@PathVariable Integer id){
		Hospedagem retorno = new Hospedagem();

		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findById(id);
			if(retorno == null) {
				headers.add("message", Messages.get("0309"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0002"));
		}
		return new ResponseEntity<Hospedagem>(retorno, headers, status);
	}
	
	@PostMapping
	public ResponseEntity<Hospedagem> insert(@RequestBody Hospedagem hospedagem){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			hospedagem = business.insert(hospedagem);
			headers.add("message", Messages.get("0305"));
		} catch (HospedagemException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0306"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Hospedagem>(hospedagem, headers, status);
	}
	
	@PutMapping
	public ResponseEntity<Hospedagem> update(@RequestBody Hospedagem hospedagem){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;

		try {
			hospedagem = business.update(hospedagem);
			headers.add("message", Messages.get("0203"));
		} catch (HospedagemException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0204"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Hospedagem>(hospedagem, headers, status);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NO_CONTENT;

		try {
			business.delete(id);
		} catch (Exception e) {
			headers.add("message", Messages.get("0307"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(headers, status);
	}
	
}
