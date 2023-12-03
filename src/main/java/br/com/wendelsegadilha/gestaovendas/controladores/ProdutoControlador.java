package br.com.wendelsegadilha.gestaovendas.controladores;

import br.com.wendelsegadilha.gestaovendas.entidades.Produto;
import br.com.wendelsegadilha.gestaovendas.servicos.ProdutoServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;
    @ApiOperation(value = "Listar todos por categoria")
    @GetMapping
    public List<Produto> listarTodos(@PathVariable Long codigoCategoria) {
        return produtoServico.listarTodos(codigoCategoria);
    }

    @ApiOperation(value = "Listar por código de produto e de categoria")
    @GetMapping("/{codigoProduto}")
    public ResponseEntity<Optional<Produto>> buscarPorCodigo(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigoProduto, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(produto) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<Produto> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody Produto produto) {
        produto = produtoServico.salvar(codigoCategoria,produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produto> salvar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto, @Valid @RequestBody Produto produto) {
        produto = produtoServico.atualizar(codigoCategoria, codigoProduto, produto);
        return ResponseEntity.ok(produto);
    }

    @ApiOperation(value = "Excluir")
    @DeleteMapping("/{codigoProduto}")
    public ResponseEntity<Void> excluir(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        produtoServico.excluir(codigoCategoria, codigoProduto);
        return ResponseEntity.noContent().build();
    }

}
