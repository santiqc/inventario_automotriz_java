package com.nexos.inventario.config;

import com.nexos.inventario.service.usuario.UsuarioMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UsuarioMapper usuarioMapper() {
        return Mappers.getMapper(UsuarioMapper.class);
    }
}