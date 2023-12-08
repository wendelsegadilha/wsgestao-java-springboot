package br.com.wendelsegadilha.gestaovendas.controladores;

import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaRequestDTO;
import br.com.wendelsegadilha.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.wendelsegadilha.gestaovendas.entidades.Categoria;
import br.com.wendelsegadilha.gestaovendas.servicos.CategoriaServico;
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

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @ApiOperation(value = "Listar todas")
    @GetMapping
    public List<CategoriaResponseDTO> listarTodas() {
        List<CategoriaResponseDTO> categorias = categoriaServico.listarTodas().stream().map(categoria ->
                CategoriaResponseDTO.converterParaCategoriaDTO(categoria)).collect(Collectors.toList());
        return categorias;
    }

    @ApiOperation(value = "Listar por código ")
    @GetMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarPorCodigo(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria.get())) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        var categoria = categoriaServico.salvar(categoriaDTO.converteParaCategoria());
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoriaDTO(categoria));
    }

    @ApiOperation(value = "Atualizar")
    @PutMapping("/{codigo}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
        var categoria = categoriaServico.atualizar(codigo, categoriaDTO.converteParaCategoria(codigo));
        return ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoriaDTO(categoria));
    }

    @ApiOperation(value = "Exluir")
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable Long codigo) {
        categoriaServico.excluir(codigo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
