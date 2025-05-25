package com.nexos.inventario.service.cargo;

import com.nexos.inventario.dto.CargoDto;
import com.nexos.inventario.entity.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    Cargo toEntity(CargoDto cargoDto);

    static CargoDto toDto(Cargo cargo) {
        if (cargo == null) return null;

        return CargoDto.builder()
                .id(cargo.getId())
                .nombre(cargo.getNombre())
                .build();
    }
}