package br.com.gerenciamentozupestoque.domain.mapper;


import br.com.gerenciamentozupestoque.domain.dto.EstoqueDTO;
import br.com.gerenciamentozupestoque.domain.entity.Estoque;

public class EstoqueMapper {

    public static Estoque fromDTO(EstoqueDTO dto) {
        Estoque estoque = new Estoque();
        estoque.setTitulo(dto.getTitulo());
        estoque.setPlataforma(dto.getPlataforma());
        estoque.setPreco(dto.getPreco());
        estoque.setQuantidade(dto.getQuantidade());
        return estoque;
    }

    public static EstoqueDTO toDTO(Estoque estoque) {
        EstoqueDTO dto = new EstoqueDTO();
        dto.setPreco(estoque.getPreco());
        dto.setQuantidade(estoque.getQuantidade());
        return dto;
    }


}
