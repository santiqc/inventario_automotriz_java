package com.nexos.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CargoDto {
    private Long id;
    private String nombre;

    public CargoDto(String administrador) {
    }
}