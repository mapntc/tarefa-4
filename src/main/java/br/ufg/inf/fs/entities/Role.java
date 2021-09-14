package br.ufg.inf.fs.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	private String role;
	
	@ManyToMany
	private List<Usuario> usuarios = new ArrayList<>();

	@Deprecated
	public Role() { }
	
	
	public Role(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	public String getRole() {
		return role;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
}
