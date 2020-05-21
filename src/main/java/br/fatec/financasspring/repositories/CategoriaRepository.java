package br.fatec.financasspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fatec.financasspring.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
