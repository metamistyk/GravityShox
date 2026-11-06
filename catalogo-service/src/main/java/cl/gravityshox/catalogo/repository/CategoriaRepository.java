package cl.gravityshox.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gravityshox.catalogo.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
