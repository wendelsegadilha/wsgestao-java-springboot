package br.com.wendelsegadilha.gestaovendas.servicos;

import br.com.wendelsegadilha.gestaovendas.entidades.Produto;
import br.com.wendelsegadilha.gestaovendas.excecao.RegraNegocioException;
import br.com.wendelsegadilha.gestaovendas.repositorios.ProdutoRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaServico categoriaServico;

    public List<Produto> listarTodos(Long codigoCategoria) {
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produto> buscarPorCodigo(Long codigoProduto, Long codigoCategoria) {
        return produtoRepositorio.buscarPorCodigo(codigoProduto, codigoCategoria);
    }

    public Produto salvar(Long codigoCategoria, Produto produto) {
        validarCategoriaProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        return produtoRepositorio.save(produto);
    }

    public Produto atualizar(Long codigoCategoria, Long codigoProduto, Produto produto) {
        Produto produtoSalvar = validarProdutoExiste(codigoCategoria, codigoProduto);
        validarCategoriaProdutoExiste(codigoCategoria);
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar, "codigo");
        return produtoRepositorio.save(produtoSalvar);
    }

    public void excluir(Long codigoCategoria, Long codigoProduto) {
        Produto produto = validarProdutoExiste(codigoCategoria, codigoProduto);
        produtoRepositorio.delete(produto);
    }

    private Produto validarProdutoExiste(Long codigoCategoria, Long codigoProduto) {
        Optional<Produto> produto = produtoRepositorio.buscarPorCodigo(codigoProduto, codigoCategoria);
        if (produto.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();
    }

    private void validarProdutoDuplicado(Produto produto){
        Optional<Produto> produtoPorDescricao =  produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if (produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
            throw new RegraNegocioException(String.format("Produto %s já cadastrado.", produto.getDescricao().toUpperCase()));
        }
    }

    private void validarCategoriaProdutoExiste(Long codigoCategoria) {
        if (codigoCategoria == null) {
            throw new RegraNegocioException("A categoria não pode ser nula.");
        }
        if (categoriaServico.buscarPorCodigo(codigoCategoria).isEmpty()) {
            throw new RegraNegocioException(String.format("A categoria de código %s informada não existe.", codigoCategoria));
        }
    }

}
