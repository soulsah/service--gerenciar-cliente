package br.com.fiap.postech.soat.techchallenger4.cliente;

import br.com.fiap.postech.soat.techchallenger4.cliente.controller.ClienteController;
import br.com.fiap.postech.soat.techchallenger4.cliente.exception.ClienteNotFoundException;
import br.com.fiap.postech.soat.techchallenger4.cliente.records.ClienteRecord;
import br.com.fiap.postech.soat.techchallenger4.cliente.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

	@InjectMocks
	private ClienteController clienteController;

	@Mock
	private ClienteService clienteService;

	private ClienteRecord clienteRecord;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		clienteRecord = new ClienteRecord(1L, "Cliente 1", "cliente1@example.com", "44534008823");
	}

	@Test
	void getAllClientes_ShouldReturnListOfClientes() throws ClienteNotFoundException {
		when(clienteService.findAllClientes()).thenReturn(Collections.singletonList(clienteRecord));

		ResponseEntity<List<ClienteRecord>> response = clienteController.getAllClientes();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(1, response.getBody().size());
		assertEquals("Cliente 1", response.getBody().get(0).nome());
	}

	@Test
	void getClienteById_ShouldReturnCliente_WhenFound() throws ClienteNotFoundException {
		when(clienteService.findClienteById(1L)).thenReturn(clienteRecord);

		ResponseEntity<ClienteRecord> response = clienteController.getClienteById(1L);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Cliente 1", response.getBody().nome());
	}

	@Test
	void getClienteById_ShouldReturnNotFound_WhenNotFound() throws ClienteNotFoundException{
		when(clienteService.findClienteById(2L)).thenThrow(new ClienteNotFoundException());

		ResponseEntity<ClienteRecord> response = clienteController.getClienteById(2L);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void createCliente_ShouldReturnCreatedCliente() {
		when(clienteService.createCliente(any(ClienteRecord.class))).thenReturn(clienteRecord);

		ResponseEntity<ClienteRecord> response = clienteController.createCliente(clienteRecord);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Cliente 1", response.getBody().nome());
	}

	@Test
	void updateCliente_ShouldReturnUpdatedCliente_WhenFound() throws ClienteNotFoundException{
		when(clienteService.updateCliente(eq(1L), any(ClienteRecord.class))).thenReturn(clienteRecord);

		ResponseEntity<ClienteRecord> response = clienteController.updateCliente(1L, clienteRecord);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Cliente 1", response.getBody().nome());
	}

	@Test
	void updateCliente_ShouldReturnNotFound_WhenNotFound() throws ClienteNotFoundException{
		when(clienteService.updateCliente(eq(2L), any(ClienteRecord.class))).thenThrow(new ClienteNotFoundException());

		ResponseEntity<ClienteRecord> response = clienteController.updateCliente(2L, clienteRecord);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	void deleteCliente_ShouldReturnNoContent() {
		doNothing().when(clienteService).deleteCliente(1L);

		ResponseEntity<Void> response = clienteController.deleteCliente(1L);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(clienteService, times(1)).deleteCliente(1L);
	}
}
