package br.ufg.inf.fs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.fs.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	Optional<Usuario> findByLogin(String login);
}
