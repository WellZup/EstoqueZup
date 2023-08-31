package br.com.gerenciamentozupestoque.controller;

import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import br.com.gerenciamentozupestoque.domain.mapper.EstoqueMapper;
import br.com.gerenciamentozupestoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstoqueController {
    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping(path = "v1/estoque/criar")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estoque> criarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        Estoque estoque = EstoqueMapper.fromDTO(estoqueDTO);
        return ResponseEntity.ok(estoque);
    }

    @GetMapping(path = "v1/estoque/buscarTodos")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Estoque>> buscarTodoEstoque() {
        List<Estoque> estoques = estoqueService.buscarTodoEstoque();
        return ResponseEntity.ok(estoques);
    }

    @GetMapping(path = "v1/estoque/buscarId/{id}" )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Estoque> buscarPorId(@PathVariable("id") Long id) {
        Estoque estoque = estoqueService.buscarPorId(id);
        return ResponseEntity.ok(estoque);
    }

    @PutMapping("v1/estoque/atualizarEstoque/{id}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
        Estoque estoque = estoqueService.atualizarEstoque(id, estoqueDTO);
        return ResponseEntity.ok(estoque);
    }

    @DeleteMapping("v1/estoque/deletarEstoque/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        estoqueService.deletarEstoque(id);
        return ResponseEntity.noContent().build();
    }


}
