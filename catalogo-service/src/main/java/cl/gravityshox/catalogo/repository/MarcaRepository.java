package cl.gravityshox.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gravityshox.catalogo.entity.Marca;

public interface MarcaRepository extends JpaRepository <Marca, Long> {

}
