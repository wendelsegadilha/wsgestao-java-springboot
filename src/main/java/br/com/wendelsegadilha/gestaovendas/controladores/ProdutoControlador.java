package br.com.wendelsegadilha.gestaovendas.controladores;

import br.com.wendelsegadilha.gestaovendas.dto.produto.ProdutoRequestDTO;
import br.com.wendelsegadilha.gestaovendas.dto.produto.ProdutoResponseDTO;
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
import java.util.stream.Collectors;

@Api(tags = "Produto")
@RestController
@RequestMapping("/categoria/{codigoCategoria}/produto")
public class ProdutoControlador {

    @Autowired
    private ProdutoServico produtoServico;
    @ApiOperation(value = "Listar todos por categoria")
    @GetMapping
    public List<ProdutoResponseDTO> listarTodos(@PathVariable Long codigoCategoria) {
        var produtos = produtoServico.listarTodos(codigoCategoria);
        return produtos.stream().map(produto -> ProdutoResponseDTO.converteParaProdutoDTO(produto)).collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por código de produto e de categoria")
    @GetMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        Optional<Produto> produto = produtoServico.buscarPorCodigo(codigoProduto, codigoCategoria);
        return produto.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converteParaProdutoDTO(produto.get())) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @Valid @RequestBody ProdutoRequestDTO produtoDTO) {
        var produto = produtoServico.salvar(codigoCategoria, produtoDTO.converteParaProduto(codigoCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoResponseDTO.converteParaProdutoDTO(produto));
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto, @Valid @RequestBody ProdutoRequestDTO produtoDTO) {
        var produto = produtoServico.atualizar(codigoCategoria, codigoProduto, produtoDTO.converteParaProduto(codigoCategoria, codigoProduto));
        return ResponseEntity.ok(ProdutoResponseDTO.converteParaProdutoDTO(produto));
    }

    @ApiOperation(value = "Excluir")
    @DeleteMapping("/{codigoProduto}")
    public ResponseEntity<Void> excluir(@PathVariable Long codigoCategoria, @PathVariable Long codigoProduto) {
        produtoServico.excluir(codigoCategoria, codigoProduto);
        return ResponseEntity.noContent().build();
    }

}
