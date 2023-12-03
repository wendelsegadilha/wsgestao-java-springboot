package br.com.wendelsegadilha.gestaovendas.repositorios;

import br.com.wendelsegadilha.gestaovendas.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaCodigo(Long codigoCategoria);
    @Query("select p from Produto as p where p.codigo = :codigoProduto and p.categoria.codigo = :codigoCategoria")
    Optional<Produto> buscarPorCodigo(Long codigoProduto, Long codigoCategoria);

    Optional<Produto> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);

}
