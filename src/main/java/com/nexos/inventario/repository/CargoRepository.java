package com.nexos.inventario.repository;

import com.nexos.inventario.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    boolean existsByNombreIgnoreCase(String nombre);
}
