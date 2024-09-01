package br.com.fiap.postech.soat.techchallenger4.cliente.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome não pode estar vazio")
    private String nome;

    @NotEmpty(message = "Email não pode estar vazio")
    private String email;

    @NotEmpty(message = "CPF não pode estar vazio")
    private String cpf;

    @NotEmpty(message = "Telefone não pode estar vazio")
    private String telefone;

    @NotEmpty(message = "Endereco não pode estar vazio")
    private String endereco;
}
