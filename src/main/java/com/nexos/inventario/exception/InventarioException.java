package com.nexos.inventario.exception;

public class InventarioException extends RuntimeException {
    public InventarioException(String mensaje) {
        super(mensaje);
    }
}