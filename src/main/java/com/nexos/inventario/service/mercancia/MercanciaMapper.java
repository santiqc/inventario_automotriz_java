package com.nexos.inventario.service.mercancia;

import com.nexos.inventario.dto.MercanciaDto;
import com.nexos.inventario.dto.MercanciaResponseDto;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;

import java.util.Optional;

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

    public static MercanciaResponseDto toDTOS(Mercancia m) {
        MercanciaResponseDto dto = new MercanciaResponseDto();

        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setCantidad(m.getCantidad());
        dto.setFechaIngreso(m.getFechaIngreso());
        dto.setUsuarioId(m.getUsuarioRegistro().getId());

        dto.setUsuarioRegistro(Optional.ofNullable(m.getUsuarioRegistro())
                .map(u -> u.getNombre())
                .orElse("-"));

        dto.setUsuarioModificacion(Optional.ofNullable(m.getUsuarioModificacion())
                .map(u -> u.getNombre())
                .orElse("-"));

        dto.setFechaModificacion(Optional.ofNullable(m.getFechaModificacion())
                .map(Object::toString)
                .orElse("-"));
        return dto;
    }
}