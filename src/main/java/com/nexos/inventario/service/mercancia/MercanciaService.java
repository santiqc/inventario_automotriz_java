package com.nexos.inventario.service.mercancia;

import com.nexos.inventario.dto.MercanciaDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MercanciaService {

    MercanciaDto registrarMercancia(MercanciaDto mercanciaDto);

    MercanciaDto editarMercancia(Long id, MercanciaDto dto, Long usuarioModificadorId);

    void eliminarMercancia(Long id, Long usuarioId);

    List<MercanciaDto> buscarMercancias(Optional<String> nombre, Optional<Long> usuarioId, Optional<LocalDate> fecha);
}
