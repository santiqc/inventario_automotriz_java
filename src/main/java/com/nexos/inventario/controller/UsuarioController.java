package com.nexos.inventario.controller;

import com.nexos.inventario.dto.CargoDto;
import com.nexos.inventario.dto.UsuarioDto;
import com.nexos.inventario.dto.UsuarioRequest;
import com.nexos.inventario.service.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> crearUsuario(@RequestBody UsuarioRequest request) {
        return new ResponseEntity<>(usuarioService.crearUsuario(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cargos")
    public ResponseEntity<List<CargoDto>> obtenerCargos() {
        return ResponseEntity.ok(usuarioService.obtenerCargos());
    }

    @PostMapping("/cargos/crear")
    public ResponseEntity<CargoDto> crearCargo(@RequestBody CargoDto request) {
        return new ResponseEntity<>(usuarioService.crearCargo(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/cargos/{id}")
    public ResponseEntity<Void> eliminarCargo(@PathVariable Long id) {
        usuarioService.eliminarCargo(id);
        return ResponseEntity.noContent().build();
    }
}
