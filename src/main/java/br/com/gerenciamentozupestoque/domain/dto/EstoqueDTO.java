package br.com.gerenciamentozupestoque.domain.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@NoArgsConstructor
public class EstoqueDTO {

    private String titulo;

    private String plataforma;

    private Integer quantidade;

    private Number preco;
}
