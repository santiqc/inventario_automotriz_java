--liquibase formatted sql

--changeset santiago.quintero:V1.002.0 splitStatements:false runOnChange:true
CREATE TABLE IF NOT EXISTS public.cargo (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

--rollback DROP TABLE IF EXISTS public.cargo;