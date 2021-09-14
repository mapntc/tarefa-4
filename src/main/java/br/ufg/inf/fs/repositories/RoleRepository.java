package br.ufg.inf.fs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufg.inf.fs.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
