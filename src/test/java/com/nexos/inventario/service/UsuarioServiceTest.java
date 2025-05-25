package com.nexos.inventario.service;

import com.nexos.inventario.controller.UsuarioController;
import com.nexos.inventario.dto.CargoDto;
import com.nexos.inventario.dto.UsuarioDto;
import com.nexos.inventario.dto.UsuarioRequest;
import com.nexos.inventario.service.usuario.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerUsuarios_retornaListaUsuarios() {
        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .nombre("Juan")
                .edad(30)
                .cargo("Administrador")
                .fechaIngreso(LocalDate.now().minusYears(1))
                .build();

        when(usuarioService.obtenerTodos()).thenReturn(Collections.singletonList(usuario));

        ResponseEntity<List<UsuarioDto>> response = usuarioController.obtenerUsuarios();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Juan", response.getBody().get(0).getNombre());
    }

    @Test
    void obtenerUsuario_retornaUsuario() {
        UsuarioDto usuario = UsuarioDto.builder()
                .id(1L)
                .nombre("Ana")
                .edad(25)
                .cargo("Soporte")
                .fechaIngreso(LocalDate.now().minusYears(2))
                .build();

        when(usuarioService.obtenerPorId(1L)).thenReturn(usuario);

        ResponseEntity<UsuarioDto> response = usuarioController.obtenerUsuario(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Ana", response.getBody().getNombre());
    }

    @Test
    void crearUsuario_retornaUsuarioCreadoConStatus201() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Luis");
        request.setEdad(28);
        request.setCargoId(1L);
        request.setFechaIngreso(LocalDate.now().minusMonths(6));

        UsuarioDto usuarioCreado = UsuarioDto.builder()
                .id(2L)
                .nombre("Luis")
                .edad(28)
                .cargo("Asesor de ventas")
                .fechaIngreso(LocalDate.now().minusMonths(6))
                .build();

        when(usuarioService.crearUsuario(request)).thenReturn(usuarioCreado);

        ResponseEntity<UsuarioDto> response = usuarioController.crearUsuario(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Luis", response.getBody().getNombre());
    }

    @Test
    void actualizarUsuario_retornaUsuarioActualizado() {
        UsuarioRequest request = new UsuarioRequest();
        request.setNombre("Carlos");
        request.setEdad(35);
        request.setCargoId(2L);
        request.setFechaIngreso(LocalDate.now().minusYears(3));

        UsuarioDto usuarioActualizado = UsuarioDto.builder()
                .id(3L)
                .nombre("Carlos")
                .edad(35)
                .cargo("Administrador")
                .fechaIngreso(LocalDate.now().minusYears(3))
                .build();

        when(usuarioService.actualizarUsuario(3L, request)).thenReturn(usuarioActualizado);

        ResponseEntity<UsuarioDto> response = usuarioController.actualizarUsuario(3L, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Carlos", response.getBody().getNombre());
    }

    @Test
    void eliminarUsuario_retornaNoContent() {
        doNothing().when(usuarioService).eliminarUsuario(4L);

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(4L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).eliminarUsuario(4L);
    }

    @Test
    void obtenerCargos_retornaListaDeCargos() {
        List<CargoDto> cargos = Arrays.asList(
                new CargoDto("Administrador"),
                new CargoDto("Soporte"),
                new CargoDto("Asesor de ventas")
        );

        when(usuarioService.obtenerCargos()).thenReturn(cargos);

        ResponseEntity<List<CargoDto>> response = usuarioController.obtenerCargos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, response.getBody().size());
        assertEquals("Administrador", response.getBody().get(0).getNombre());
    }
}