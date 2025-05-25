--liquibase formatted sql

--changeset santiago.quintero:V1.004.0 splitStatements:false runOnChange:true
CREATE TABLE IF NOT EXISTS public.mercancia (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    cantidad INTEGER NOT NULL,
    fecha_ingreso DATE NOT NULL,
    usuario_registro_id BIGINT,
    usuario_modificacion_id BIGINT,
    fecha_modificacion DATE,
    CONSTRAINT fk_mercancia_usuario_registro FOREIGN KEY (usuario_registro_id) REFERENCES public.usuario(id),
    CONSTRAINT fk_mercancia_usuario_modificacion FOREIGN KEY (usuario_modificacion_id) REFERENCES public.usuario(id)
);

--rollback DROP TABLE IF EXISTS public.mercancia;