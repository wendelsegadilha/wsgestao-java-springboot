package br.com.wendelsegadilha.gestaovendas.dto.produto;

import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.wendelsegadilha.gestaovendas.entidades.Categoria;
import br.com.wendelsegadilha.gestaovendas.entidades.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Produto requisição DTO")
public class ProdutoRequestDTO {
    @ApiModelProperty(value = "Descrição")
    @NotBlank(message = "Descrição")
    @Length(min = 3, max = 100, message = "Descrição")
    private String descricao;
    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade")
    private Integer quantidade;
    @ApiModelProperty(value = "Preço de custo")
    @NotNull(message = "Preço de custo")
    private BigDecimal precoCusto;
    @ApiModelProperty(value = "Preço de venda")
    @NotNull(message = "Preço de venda")
    private BigDecimal precoVenda;
    @ApiModelProperty(value = "Observação")
    @Length(max = 500, message = "Observação")
    private String observacao;

    public Produto converteParaProduto(Long codigoCategoria) {
        return new Produto(null, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
    }

    public Produto converteParaProduto(Long codigoCategoria, Long codigoProduto) {
        return new Produto(codigoProduto, descricao, quantidade, precoCusto, precoVenda, observacao, new Categoria(codigoCategoria));
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

}
