package br.com.gerenciamentozupestoque.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "TB_Estoque")
@Getter
@Setter

@NoArgsConstructor
public class Estoque implements Serializable {

    private final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String plataforma;
    @Column
    private Integer quantidade;
    @Column
    private Number preco;

}
