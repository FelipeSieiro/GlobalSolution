package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long>{
    
}
