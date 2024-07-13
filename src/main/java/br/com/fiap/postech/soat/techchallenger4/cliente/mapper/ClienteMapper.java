package br.com.fiap.postech.soat.techchallenger4.cliente.mapper;

import br.com.fiap.postech.soat.techchallenger4.cliente.entity.Cliente;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;

public class ClienteMapper {

    public static ClienteRecord mapToRecord(Cliente cliente) {
        return new ClienteRecord(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCPF()
        );
    }

    public static Cliente mapFromRecord(ClienteRecord clienteRecord) {
        return new Cliente(
                clienteRecord.id(),
                clienteRecord.nome(),
                clienteRecord.email(),
                clienteRecord.CPF()
        );
    }
}
