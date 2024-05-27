package br.com.fiap.globalsolution.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.globalsolution.model.Usuario;
import br.com.fiap.globalsolution.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    
    @Autowired
    UserRepository repository;

    @GetMapping
    public List<Usuario> index() {
        return repository.findAll();

    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Usuario create(@RequestBody Usuario user) { 
        log.info("cadastrando user {} ", user);
        return repository.save(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id) {
        log.info("buscando user por id {}", id);

        return repository
                .findById(id)
                .map(ResponseEntity::ok) 
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("apagando user");

        verificarSeExisteUser(id);
        repository.deleteById(id);
    }

    @PutMapping("{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario user) {
        log.info("atualizando user com id {} para {}", id, user);

        verificarSeExisteUser(id);
        user.setId(id);
        return repository.save(user);
    }

    private void verificarSeExisteUser(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Não existe user com o id informado. Consulte lista em /user"));
    }
}

