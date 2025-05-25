package com.nexos.inventario.controller;

import com.nexos.inventario.dto.MercanciaDto;
import com.nexos.inventario.service.mercancia.MercanciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mercancias")
public class MercanciaController {

    private final MercanciaService mercanciaService;

    public MercanciaController(MercanciaService mercanciaService) {
        this.mercanciaService = mercanciaService;
    }

    @PostMapping
    public ResponseEntity<MercanciaDto> registrar(@Valid @RequestBody MercanciaDto dto) {
        MercanciaDto creado = mercanciaService.registrarMercancia(dto);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MercanciaDto> editar(
            @PathVariable Long id,
            @Valid @RequestBody MercanciaDto dto,
            @RequestParam Long usuarioModificadorId
    ) {
        MercanciaDto actualizado = mercanciaService.editarMercancia(id, dto, usuarioModificadorId);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id,
            @RequestParam Long usuarioId
    ) {
        mercanciaService.eliminarMercancia(id, usuarioId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MercanciaDto>> buscar(
            @RequestParam Optional<String> nombre,
            @RequestParam Optional<Long> usuarioId,
            @RequestParam Optional<String> fecha
    ) {
        Optional<LocalDate> fechaOpt = fecha.map(LocalDate::parse);
        List<MercanciaDto> resultados = mercanciaService.buscarMercancias(nombre, usuarioId, fechaOpt);
        return ResponseEntity.ok(resultados);
    }
}
