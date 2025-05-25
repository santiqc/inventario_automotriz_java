package com.nexos.inventario.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nexos.inventario.dto.MercanciaDto;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.exeption.InventarioException;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;

import com.nexos.inventario.service.mercancia.MercanciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

public class MercanciaServiceTest {

    private MercanciaRepository mercanciaRepo;
    private UsuarioRepository usuarioRepo;
    private MercanciaServiceImpl mercanciaService;

    @BeforeEach
    void setUp() {
        mercanciaRepo = mock(MercanciaRepository.class);
        usuarioRepo = mock(UsuarioRepository.class);
        mercanciaService = new MercanciaServiceImpl(mercanciaRepo, usuarioRepo);
    }

    @Test
    void registrarMercancia_nombreRepetido_lanzaExcepcion() {
        MercanciaDto dto = new MercanciaDto();
        dto.setNombre("Producto1");
        dto.setCantidad(10);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUsuarioId(1L);

        when(mercanciaRepo.existsByNombre("Producto1")).thenReturn(true);

        InventarioException ex = assertThrows(InventarioException.class,
                () -> mercanciaService.registrarMercancia(dto));
        assertEquals("Ya existe una mercancía con ese nombre", ex.getMessage());
    }

    @Test
    void registrarMercancia_fechaFutura_lanzaExcepcion() {
        MercanciaDto dto = new MercanciaDto();
        dto.setNombre("ProductoNuevo");
        dto.setCantidad(10);
        dto.setFechaIngreso(LocalDate.now().plusDays(1));
        dto.setUsuarioId(1L);

        when(mercanciaRepo.existsByNombre("ProductoNuevo")).thenReturn(false);

        InventarioException ex = assertThrows(InventarioException.class,
                () -> mercanciaService.registrarMercancia(dto));
        assertEquals("La fecha de ingreso no puede ser futura", ex.getMessage());
    }

    @Test
    void registrarMercancia_usuarioNoEncontrado_lanzaExcepcion() {
        MercanciaDto dto = new MercanciaDto();
        dto.setNombre("ProductoNuevo");
        dto.setCantidad(10);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUsuarioId(99L);

        when(mercanciaRepo.existsByNombre("ProductoNuevo")).thenReturn(false);
        when(usuarioRepo.findById(99L)).thenReturn(Optional.empty());

        InventarioException ex = assertThrows(InventarioException.class,
                () -> mercanciaService.registrarMercancia(dto));
        assertEquals("Usuario no encontrado", ex.getMessage());
    }

    @Test
    void registrarMercancia_ok() {
        MercanciaDto dto = new MercanciaDto();
        dto.setNombre("ProductoNuevo");
        dto.setCantidad(10);
        dto.setFechaIngreso(LocalDate.now());
        dto.setUsuarioId(1L);

        when(mercanciaRepo.existsByNombre("ProductoNuevo")).thenReturn(false);
        Usuario user = new Usuario();
        user.setId(1L);
        when(usuarioRepo.findById(1L)).thenReturn(Optional.of(user));
        when(mercanciaRepo.save(any(Mercancia.class))).thenAnswer(inv -> inv.getArgument(0));

        MercanciaDto resultado = mercanciaService.registrarMercancia(dto);
        assertEquals("ProductoNuevo", resultado.getNombre());
        assertEquals(10, resultado.getCantidad());
    }
}