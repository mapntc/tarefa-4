package br.ufg.inf.fs.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Usuario;
import br.ufg.inf.fs.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> possivelUsuario = repository.findByLogin(username);
		
		if (possivelUsuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuário " + username + " não encotnrado.");
		}
		
		return possivelUsuario.get();
	}

}
