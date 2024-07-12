package br.com.fiap.postech.soat.techchallenger4.cliente.controller;

import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;
import br.com.fiap.postech.soat.techchallenger4.cliente.service.ClienteService;
import br.com.fiap.postech.soat.techchallenger4.cliente.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
    }

    @GetMapping
    public ResponseEntity<List<ClienteRecord>> getAllPedidos() throws ClienteNotFoundException {

        return ResponseEntity.ok(clienteService.findAllClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteRecord> getClienteById(@PathVariable Long id) throws ClienteNotFoundException {

        return ResponseEntity.ok(clienteService.findClienteById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteRecord> createCliente(@RequestBody ClienteRecord cliente){

        return ResponseEntity.ok(clienteService.save(cliente));
    }

}
