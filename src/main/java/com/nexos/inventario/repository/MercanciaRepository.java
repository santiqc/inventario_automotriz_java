package com.nexos.inventario.repository;

import com.nexos.inventario.entity.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MercanciaRepository extends JpaRepository<Mercancia, Long>, JpaSpecificationExecutor<Mercancia> {

    boolean existsByNombre(String nombre);
}
