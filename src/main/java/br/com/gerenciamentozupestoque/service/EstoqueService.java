package br.com.gerenciamentozupestoque.service;

import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import br.com.gerenciamentozupestoque.domain.mapper.EstoqueMapper;
import br.com.gerenciamentozupestoque.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public Estoque criarEstoque(EstoqueDTO estoqueDTO) {
        Estoque estoque = EstoqueMapper.fromDTO(estoqueDTO);
        estoque.setTitulo(estoqueDTO.getTitulo());
        estoque.setPlataforma(estoqueDTO.getPlataforma());
        estoque.setQuantidade(estoqueDTO.getQuantidade());
        estoque.setPreco(estoqueDTO.getPreco());

        return estoqueRepository.save(estoque);
    }

    public List<Estoque> buscarTodoEstoque() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(Long id) { return estoqueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()); }


    public Estoque atualizarEstoque(Long id, EstoqueDTO estoqueDTO) {
        Estoque estoque = buscarPorId(id);
        estoque.setTitulo(estoqueDTO.getTitulo());
        estoque.setPlataforma(estoqueDTO.getPlataforma());
        estoque.setQuantidade(estoqueDTO.getQuantidade());
        estoque.setPreco(estoqueDTO.getPreco());

        return estoqueRepository.save(estoque);

    }

    public void deletarEstoque(Long id) {
        Estoque estoque = buscarPorId(id);
        estoqueRepository.delete(estoque);
    }
}
