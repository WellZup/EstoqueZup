package br.com.gerenciamentozupestoque.repository;

import br.com.gerenciamentozupestoque.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
