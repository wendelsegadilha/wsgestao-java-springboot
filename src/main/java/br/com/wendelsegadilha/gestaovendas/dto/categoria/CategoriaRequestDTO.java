package br.com.wendelsegadilha.gestaovendas.dto.categoria;

import br.com.wendelsegadilha.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel("Categoria requisição DTO")
public class CategoriaRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    public Categoria converteParaCategoria() {
        return new Categoria(this.nome);
    }

    public Categoria converteParaCategoria(Long codigo) {
        return new Categoria(codigo, nome);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

}
