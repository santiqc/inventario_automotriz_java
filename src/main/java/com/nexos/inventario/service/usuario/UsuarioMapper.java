package com.nexos.inventario.service.usuario;

import com.nexos.inventario.dto.UsuarioDto;
import com.nexos.inventario.dto.UsuarioRequest;
import com.nexos.inventario.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "cargo.nombre", target = "cargo")
    @Mapping(source = "cargo.id", target = "cargoId")
    UsuarioDto toDto(Usuario usuario);

    Usuario toEntity(UsuarioRequest request);
}