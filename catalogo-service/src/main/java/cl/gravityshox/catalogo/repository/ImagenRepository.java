package cl.gravityshox.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gravityshox.catalogo.entity.Imagen;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}
