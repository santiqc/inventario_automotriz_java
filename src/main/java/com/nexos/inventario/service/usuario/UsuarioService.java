package com.nexos.inventario.service.usuario;

import com.nexos.inventario.dto.CargoDto;
import com.nexos.inventario.dto.UsuarioDto;
import com.nexos.inventario.dto.UsuarioRequest;

import java.util.List;

public interface UsuarioService {

    List<UsuarioDto> obtenerTodos();

    UsuarioDto obtenerPorId(Long id);

    UsuarioDto crearUsuario(UsuarioRequest request);

    UsuarioDto actualizarUsuario(Long id, UsuarioRequest request);

    void eliminarUsuario(Long id);

    List<CargoDto> obtenerCargos();

    CargoDto crearCargo(CargoDto request);

    void eliminarCargo(Long id);
}
