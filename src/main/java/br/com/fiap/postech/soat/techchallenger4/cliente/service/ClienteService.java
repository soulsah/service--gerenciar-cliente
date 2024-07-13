package br.com.fiap.postech.soat.techchallenger4.cliente.service;

import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;

import java.util.List;

public interface ClienteService {

    List<ClienteRecord> findAllClientes() throws ClienteNotFoundException;

    ClienteRecord findClienteById(Long id) throws ClienteNotFoundException;

    void deleteCliente(Long id);

    ClienteRecord updateCliente(Long id, ClienteRecord clienteRecord) throws ClienteNotFoundException;

    ClienteRecord createCliente(ClienteRecord clienteRecord);
}
