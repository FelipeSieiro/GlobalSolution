package br.com.fiap.globalsolution.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.globalsolution.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}