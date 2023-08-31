package br.com.gerenciamentozupestoque.repository;

import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
