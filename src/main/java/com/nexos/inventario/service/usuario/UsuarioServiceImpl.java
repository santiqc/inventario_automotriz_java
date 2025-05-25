package com.nexos.inventario.service.usuario;

import com.nexos.inventario.dto.CargoDto;
import com.nexos.inventario.dto.UsuarioDto;
import com.nexos.inventario.dto.UsuarioRequest;
import com.nexos.inventario.entity.Cargo;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.repository.CargoRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.cargo.CargoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final CargoRepository cargoRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, CargoRepository cargoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDto obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioRequest request) {
        Cargo cargo = cargoRepository.findById(request.getCargoId())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .edad(request.getEdad())
                .fechaIngreso(request.getFechaIngreso())
                .cargo(cargo)
                .build();

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDto actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cargo cargo = cargoRepository.findById(request.getCargoId())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));

        existente.setNombre(request.getNombre());
        existente.setEdad(request.getEdad());
        existente.setCargo(cargo);
        existente.setFechaIngreso(request.getFechaIngreso());

        return usuarioMapper.toDto(usuarioRepository.save(existente));
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<CargoDto> obtenerCargos() {
        return cargoRepository.findAll().stream()
                .map(CargoMapper::toDto)
                .collect(Collectors.toList());
    }
}
