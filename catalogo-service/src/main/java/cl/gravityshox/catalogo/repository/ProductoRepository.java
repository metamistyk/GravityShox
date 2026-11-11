package cl.gravityshox.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.gravityshox.catalogo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

}
