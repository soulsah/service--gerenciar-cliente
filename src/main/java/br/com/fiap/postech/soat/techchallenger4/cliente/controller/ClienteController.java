package br.com.fiap.postech.soat.techchallenger4.cliente.controller;

import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;
import br.com.fiap.postech.soat.techchallenger4.cliente.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping
    public ResponseEntity<List<ClienteRecord>> getAllClientes() {
        try {
            List<ClienteRecord> clientes = clienteService.findAllClientes();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteRecord> getClienteById(@PathVariable Long id) {
        try {
            ClienteRecord cliente = clienteService.findClienteById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ClienteRecord> createCliente(@RequestBody ClienteRecord clienteRecord) {
        ClienteRecord createdCliente = clienteService.createCliente(clienteRecord);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteRecord> updateCliente(@PathVariable Long id, @RequestBody ClienteRecord clienteRecord) {
        try {
            ClienteRecord updatedCliente = clienteService.updateCliente(id, clienteRecord);
            return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
        } catch (ClienteNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
