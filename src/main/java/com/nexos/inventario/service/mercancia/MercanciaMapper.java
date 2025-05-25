package com.nexos.inventario.service.mercancia;

import com.nexos.inventario.dto.MercanciaDto;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;

public class MercanciaMapper {
    public static Mercancia toEntity(MercanciaDto dto, Usuario usuarioRegistro) {
        Mercancia m = new Mercancia();
        m.setNombre(dto.getNombre());
        m.setCantidad(dto.getCantidad());
        m.setFechaIngreso(dto.getFechaIngreso());
        m.setUsuarioRegistro(usuarioRegistro);
        return m;
    }

    public static MercanciaDto toDTO(Mercancia m) {
        MercanciaDto dto = new MercanciaDto();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setCantidad(m.getCantidad());
        dto.setFechaIngreso(m.getFechaIngreso());
        dto.setUsuarioId(m.getUsuarioRegistro().getId());
        return dto;
    }
}