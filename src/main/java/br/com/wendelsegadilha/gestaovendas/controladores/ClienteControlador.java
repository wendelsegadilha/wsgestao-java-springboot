package br.com.wendelsegadilha.gestaovendas.controladores;

import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaRequestDTO;
import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.wendelsegadilha.gestaovendas.dto.cliente.ClienteRequestDTO;
import br.com.wendelsegadilha.gestaovendas.dto.cliente.ClienteResponseDTO;
import br.com.wendelsegadilha.gestaovendas.entidades.Cliente;
import br.com.wendelsegadilha.gestaovendas.servicos.ClienteServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServico clienteServico;

    @ApiOperation(value = "Listar todos")
    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return clienteServico.listarTodos().stream().map(cliente -> ClienteResponseDTO.converteParaClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código ")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converteParaClienteDTO(cliente.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteDTO) {
        var cliente = clienteServico.salvar(clienteDTO.converteParaCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converteParaClienteDTO(cliente));
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteDTO) {
        var cliente = clienteServico.atualizar(codigo, clienteDTO.converteParaCliente());
        return ResponseEntity.ok(ClienteResponseDTO.converteParaClienteDTO(cliente));
    }

    @ApiOperation(value = "Exluir")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        clienteServico.excluir(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
