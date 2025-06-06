package com.nexos.inventario.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private Long id;

    private String nombre;

    private Integer edad;

    private String cargo;

    private Long cargoId;

    private LocalDate fechaIngreso;
}
