package br.com.wendelsegadilha.gestaovendas.dto.cliente;

import br.com.wendelsegadilha.gestaovendas.entidades.Cliente;
import br.com.wendelsegadilha.gestaovendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@ApiModel("Cliente requisição DTO")
public class ClienteRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome")
    private String nome;
    @NotBlank(message = "Telefone")
    @ApiModelProperty(value = "Telefone")
    private String telefone;
    @NotNull(message = "Ativo")
    @ApiModelProperty(value = "Ativo")
    private Boolean ativo;
    @NotNull(message = "Endereço")
    @ApiModelProperty(value = "Endereço")
    private EnderecoDTO endereco;

    public Cliente converteParaCliente() {
        var end = new Endereco(endereco.getEstado(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getEstado());
        return new Cliente(nome, telefone, ativo, end);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

}
