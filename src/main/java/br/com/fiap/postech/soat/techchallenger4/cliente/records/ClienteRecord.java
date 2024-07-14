package br.com.fiap.postech.soat.techchallenger4.cliente.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Valid
public record ClienteRecord(Long id, @NotEmpty(message = "Nome não pode estar vazio")String nome, @NotEmpty(message = "Email não pode estar vazio")String email, @CPF(message="cpf inválido")
String cpf) {
}
