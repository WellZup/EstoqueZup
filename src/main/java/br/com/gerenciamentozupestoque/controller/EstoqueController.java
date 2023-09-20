package br.com.gerenciamentozupestoque.controller;

import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import br.com.gerenciamentozupestoque.domain.mapper.EstoqueMapper;
import br.com.gerenciamentozupestoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/v1/estoque")
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class EstoqueController {
    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }


    @PostMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Realiza a criação de novos itens", method = "POST")
    public ResponseEntity<Estoque> criarEstoque(@RequestBody EstoqueDTO estoqueDTO) {
        try {
        Estoque estoque = EstoqueMapper.fromDTO(estoqueDTO);
        return ResponseEntity.ok(estoqueService.criarEstoque(estoqueDTO));
        } catch (Exception e) {
            System.out.println("Erro ao criar novo item.");
        } return null;
    }

    // TODO: 01/09/2023 Alterar a Entidade para Entidade DTO criando o mapper from to 
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Realiza a busca de todos os itens cadastrados", method = "GET")
    public ResponseEntity<List<Estoque>> buscarTodoEstoque() {
        List<Estoque> estoques = estoqueService.buscarTodoEstoque();
        return ResponseEntity.ok(estoques); }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    @Operation(summary = "Realiza a busca de item por ID informado", method = "GET")
    public ResponseEntity<Estoque> buscarPorId(@PathVariable("id") Long id) {
        try{
        Estoque estoque = estoqueService.buscarPorId(id);
        return ResponseEntity.ok(estoque);
        }catch (EstoqueNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e) {System.out.println(e.getMessage());} return null;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Realiza a atualização pelo ID informado", method = "PUT")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
        try {
        Estoque estoque = estoqueService.atualizarEstoque(id, estoqueDTO);
        return ResponseEntity.ok(estoque);
        }catch (EstoqueNotFoundException e) {return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o item pelo ID informado", method = "DELETE")
    public ResponseEntity<Void> deletarEstoque(@PathVariable Long id) {
        try {

        estoqueService.deletarEstoque(id);
        return ResponseEntity.noContent().build();
        }catch (EstoqueNotFoundException e) {return ResponseEntity.notFound().build();
        }
    }

    public class EstoqueNotFoundException extends RuntimeException {
        public EstoqueNotFoundException(Long id) {
            super("Estoque com ID " + id + " não encontrado.");
        }
    }


}
