package br.com.fiap.postech.soat.techchallenger4.cliente.repository;

import br.com.fiap.postech.soat.techchallenger4.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
