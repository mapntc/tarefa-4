package br.ufg.inf.fs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.fs.entities.Hospede;

public interface HospedeRepository extends JpaRepository<Hospede, Integer>{

	Optional<Hospede> findByCpf(String cpf);

}
