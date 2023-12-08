package br.com.wendelsegadilha.gestaovendas.dto.produto;

import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.wendelsegadilha.gestaovendas.entidades.Categoria;
import br.com.wendelsegadilha.gestaovendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto resposta DTO")
public class ProdutoResponseDTO {
    @ApiModelProperty(value = "Código")
    private Long codigo;
    @ApiModelProperty(value = "Descrição")
    private String descricao;
    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;
    @ApiModelProperty(value = "Preço de custo")
    private BigDecimal precoCusto;
    @ApiModelProperty(value = "Preço de venda")
    private BigDecimal precoVenda;
    @ApiModelProperty(value = "Observação")
    private String observacao;
    @ApiModelProperty(value = "Categoria")
    private CategoriaResponseDTO categoria;

    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda, String observacao, CategoriaResponseDTO categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public static ProdutoResponseDTO converteParaProdutoDTO(Produto produto) {
        return new ProdutoResponseDTO(produto.getCodigo(), produto.getDescricao(), produto.getQuantidade(), produto.getPrecoCusto(), produto.getPrecoVenda(),
                produto.getObservacao(), CategoriaResponseDTO.converterParaCategoriaDTO(produto.getCategoria()));
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponseDTO categoria) {
        this.categoria = categoria;
    }
}
