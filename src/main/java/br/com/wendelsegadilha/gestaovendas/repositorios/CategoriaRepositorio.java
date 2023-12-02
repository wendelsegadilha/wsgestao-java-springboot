package br.com.wendelsegadilha.gestaovendas.repositorios;

import br.com.wendelsegadilha.gestaovendas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);
}
