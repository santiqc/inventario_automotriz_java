package com.nexos.inventario.service.mercancia;

import com.nexos.inventario.dto.MercanciaDto;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.exeption.InventarioException;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MercanciaServiceImpl implements MercanciaService {

    private final MercanciaRepository mercanciaRepository;
    private final UsuarioRepository usuarioRepository;

    public MercanciaServiceImpl(MercanciaRepository mercanciaRepository, UsuarioRepository usuarioRepository) {
        this.mercanciaRepository = mercanciaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public MercanciaDto registrarMercancia(MercanciaDto mercanciaDto) {

        if (mercanciaRepository.existsByNombre(mercanciaDto.getNombre())) {
            throw new InventarioException("Ya existe una mercancía con ese nombre");
        }

        if (mercanciaDto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new InventarioException("La fecha de ingreso no puede ser futura");
        }

        Usuario usuario = usuarioRepository.findById(mercanciaDto.getUsuarioId())
                .orElseThrow(() -> new InventarioException("Usuario no encontrado"));

        Mercancia mercancia = MercanciaMapper.toEntity(mercanciaDto, usuario);
        Mercancia guardada = mercanciaRepository.save(mercancia);

        return MercanciaMapper.toDTO(guardada);
    }

    @Override
    public MercanciaDto editarMercancia(Long id, MercanciaDto dto, Long usuarioModificadorId) {
        Mercancia mercancia = mercanciaRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Mercancía no encontrada"));


        if (!mercancia.getNombre().equals(dto.getNombre()) &&
                mercanciaRepository.existsByNombre(dto.getNombre())) {
            throw new InventarioException("Ya existe una mercancía con ese nombre");
        }

        if (dto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new InventarioException("La fecha de ingreso no puede ser futura");
        }

        Usuario usuarioMod = usuarioRepository.findById(usuarioModificadorId)
                .orElseThrow(() -> new InventarioException("Usuario modificador no encontrado"));

        mercancia.setNombre(dto.getNombre());
        mercancia.setCantidad(dto.getCantidad());
        mercancia.setFechaIngreso(dto.getFechaIngreso());
        mercancia.setUsuarioModificacion(usuarioMod);
        mercancia.setFechaModificacion(LocalDate.now());

        Mercancia actualizada = mercanciaRepository.save(mercancia);
        return MercanciaMapper.toDTO(actualizada);
    }


    @Override
    public void eliminarMercancia(Long id, Long usuarioId) {
        Mercancia mercancia = mercanciaRepository.findById(id)
                .orElseThrow(() -> new InventarioException("Mercancía no encontrada"));

        if (!mercancia.getUsuarioRegistro().getId().equals(usuarioId)) {
            throw new InventarioException("Solo el usuario que registró la mercancía puede eliminarla");
        }

        mercanciaRepository.delete(mercancia);
    }

    @Override
    public List<MercanciaDto> buscarMercancias(Optional<String> nombre, Optional<Long> usuarioId, Optional<LocalDate> fecha) {
        if (nombre.isEmpty() && usuarioId.isEmpty() && fecha.isEmpty()) {
            throw new InventarioException("Debe buscar al menos por un filtro");
        }

        Specification<Mercancia> spec = (root, query, cb) -> cb.conjunction();

        if (nombre.isPresent()) {
            spec = spec.and(MercanciaSpecification.nombreContiene(nombre.get()));
        }
        if (usuarioId.isPresent()) {
            spec = spec.and(MercanciaSpecification.usuarioIdEs(usuarioId.get()));
        }
        if (fecha.isPresent()) {
            spec = spec.and(MercanciaSpecification.fechaIngresoEs(fecha.get()));
        }

        List<Mercancia> resultados = mercanciaRepository.findAll(spec);

        return resultados.stream().map(MercanciaMapper::toDTO).collect(Collectors.toList());
    }
}
