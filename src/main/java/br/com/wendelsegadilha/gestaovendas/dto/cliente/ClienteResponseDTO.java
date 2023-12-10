package br.com.wendelsegadilha.gestaovendas.dto.cliente;

import br.com.wendelsegadilha.gestaovendas.entidades.Cliente;
import br.com.wendelsegadilha.gestaovendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.util.Objects;

@ApiModel("Cliente retorno DTO")
public class ClienteResponseDTO {

    @ApiModelProperty(value = "Código")
    private Long codigo;
    @ApiModelProperty(value = "Nome")
    private String nome;
    @ApiModelProperty(value = "Telefone")
    private String telefone;
    @ApiModelProperty(value = "Ativo")
    private Boolean ativo;
    @ApiModelProperty(value = "Endereço")
    private EnderecoDTO endereco;

    public ClienteResponseDTO(){}

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo, EnderecoDTO endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.endereco = endereco;
    }

    public static ClienteResponseDTO converteParaClienteDTO(Cliente cliente){
        var end = cliente.getEndereco();
        var enderecoDTO = new EnderecoDTO(end.getLogradouro(), end.getNumero(), end.getComplemento(), end.getBairro(), end.getCep(), end.getCidade(), end.getEstado());
        return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(), enderecoDTO);
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClienteResponseDTO that = (ClienteResponseDTO) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(nome, that.nome) && Objects.equals(telefone, that.telefone) && Objects.equals(ativo, that.ativo) && Objects.equals(endereco, that.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, telefone, ativo, endereco);
    }
}
