--liquibase formatted sql

--changeset santiago.quintero:V1.003.0 splitStatements:false runOnChange:true
CREATE TABLE IF NOT EXISTS public.usuario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    edad INTEGER NOT NULL,
    fecha_ingreso DATE NOT NULL,
    cargo_id BIGINT,
    CONSTRAINT fk_usuario_cargo FOREIGN KEY (cargo_id) REFERENCES public.cargo(id)
);

--rollback DROP TABLE IF EXISTS public.usuario;