package br.com.wendelsegadilha.gestaovendas.servicos;

import br.com.wendelsegadilha.gestaovendas.entidades.Cliente;
import br.com.wendelsegadilha.gestaovendas.repositorios.ClienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    public Optional<Cliente> buscarPorCodigo(Long codigo) {
        return clienteRepositorio.findById(codigo);
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }

    public Cliente atualizar(Long codigo, Cliente cliente) {
        var clienteSalvo = validaClienteExiste(codigo);
        BeanUtils.copyProperties(cliente, clienteSalvo, "codigo");
        clienteRepositorio.save(clienteSalvo);
        return clienteSalvo;
    }

    public void excluir(Long codigo){
        Cliente cliente = validaClienteExiste(codigo);
        clienteRepositorio.delete(cliente);
    }

    private Cliente validaClienteExiste(Long codigo){
        Optional<Cliente> clienteSalvo = clienteRepositorio.findById(codigo);
        if (clienteSalvo.isEmpty())
            throw new EmptyResultDataAccessException(1);
        return clienteSalvo.get();
    }
}
