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
public class MercanciaDto {

    @NotBlank(message = "Nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "Cantidad es requerida")
    @Min(value = 0, message = "Cantidad debe ser >= 0")
    private Integer cantidad;

    @NotNull(message = "Fecha de ingreso es requerida")
    private LocalDate fechaIngreso;

    @NotNull(message = "UsuarioId es requerido")
    private Long usuarioId;

}
