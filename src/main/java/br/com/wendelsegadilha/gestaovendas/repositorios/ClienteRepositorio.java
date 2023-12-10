package br.com.wendelsegadilha.gestaovendas.repositorios;

import br.com.wendelsegadilha.gestaovendas.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}
