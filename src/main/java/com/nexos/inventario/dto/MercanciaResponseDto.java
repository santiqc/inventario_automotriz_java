package com.nexos.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MercanciaResponseDto {

    private Long id;

    private String nombre;

    private Integer cantidad;

    private LocalDate fechaIngreso;

    private Long usuarioId;

    private String usuarioRegistro;

    private String usuarioModificacion;

    private String fechaModificacion;

}
