package br.com.gerenciamentozupestoque.domain.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

// TODO: 20/09/2023 Estudar sobre Build 
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDTO {
@NotBlank
    private String titulo;

    private String plataforma;

    private Integer quantidade;

    private BigDecimal preco;
}
