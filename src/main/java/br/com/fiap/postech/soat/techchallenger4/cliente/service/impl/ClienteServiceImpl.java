package br.com.fiap.postech.soat.techchallenger4.cliente.service.impl;

import br.com.fiap.postech.soat.techchallenger4.cliente.entity.Cliente;
import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.mapper.ClienteMapper;
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
                .map(ClienteMapper::mapToRecord)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteRecord findClienteById(Long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(ClienteNotFoundException::new);
        return ClienteMapper.mapToRecord(cliente);
    }

    @Override
    public ClienteRecord createCliente(ClienteRecord clienteRecord) {
        Cliente cliente = ClienteMapper.mapFromRecord(clienteRecord);
        Cliente savedCliente = clienteRepository.save(cliente);
        return ClienteMapper.mapToRecord(savedCliente);
    }

    @Override
    public void deleteCliente(Long id) {
        Cliente cliente = null;
        try {
            cliente = clienteRepository.findById(id)
                    .orElseThrow(ClienteNotFoundException::new);
        } catch (ClienteNotFoundException e) {
            throw new RuntimeException(e);
        }
        clienteRepository.deleteById(id);
    }

    @Override
    public ClienteRecord updateCliente(Long id, ClienteRecord clienteRecord) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(ClienteNotFoundException::new);
        cliente.setNome(clienteRecord.nome());
        cliente.setEmail(clienteRecord.email());
        cliente.setCPF(clienteRecord.CPF());
        Cliente updatedCliente = clienteRepository.save(cliente);
        return ClienteMapper.mapToRecord(updatedCliente);
    }

}
