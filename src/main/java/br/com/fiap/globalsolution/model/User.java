package br.com.fiap.globalsolution.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{user.nome.notblank}")
    private String nome;
 
    @NotBlank(message = "{user.email.notblank}")
    private String email;

    @NotBlank(message = "{user.senha.notblank}")
    @Size(min = 6, max = 15, message = "{user.senha.size}")
    private String senha;
    
}
