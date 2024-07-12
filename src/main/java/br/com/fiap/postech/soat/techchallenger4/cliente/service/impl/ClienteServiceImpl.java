package br.com.fiap.postech.soat.techchallenger4.cliente.service.impl;

import br.com.fiap.postech.soat.techchallenger4.cliente.entity.Cliente;
import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;
import br.com.fiap.postech.soat.techchallenger4.cliente.repository.ClienteRepository;
import br.com.fiap.postech.soat.techchallenger4.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<ClienteRecord> findAllClientes() throws ClienteNotFoundException {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new ClienteNotFoundException();
        }
        return clientes.stream()
                .map(ClienteRecord::new)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteRecord findClienteById(Long id) throws ClienteNotFoundException {
        return null;
    }

    @Override
    public ClienteRecord save(ClienteRecord cliente) {
        return null;
    }

    @Override
    public void deleteCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
