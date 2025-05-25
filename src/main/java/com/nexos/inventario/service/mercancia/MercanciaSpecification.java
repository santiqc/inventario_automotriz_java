package com.nexos.inventario.service.mercancia;

import com.nexos.inventario.entity.Mercancia;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class MercanciaSpecification {

    public static Specification<Mercancia> nombreContiene(String nombre) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("nombre")), "%" + nombre.toLowerCase() + "%");
    }

    public static Specification<Mercancia> usuarioIdEs(Long usuarioId) {
        return (root, query, cb) -> cb.equal(root.get("usuarioRegistro").get("id"), usuarioId);
    }

    public static Specification<Mercancia> fechaIngresoEs(LocalDate fecha) {
        return (root, query, cb) -> cb.equal(root.get("fechaIngreso"), fecha);
    }
}