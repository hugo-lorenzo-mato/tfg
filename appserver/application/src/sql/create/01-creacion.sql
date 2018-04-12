-- Created by hlorenzo on 08/08/17 --

-- script creacion Postgres ACERVO JOSUE GUIMARAES --

-- Eliminamos y creamos la BD
DROP DATABASE IF EXISTS GGAL1701;
CREATE DATABASE GGAL1701 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';
--
\CONNECT GGAL1701

CREATE SCHEMA IF NOT EXISTS public;

CREATE EXTENSION IF NOT EXISTS unaccent;

-- usuario --

DROP TABLE IF EXISTS usuario CASCADE;
CREATE TABLE usuario (
    id       BIGSERIAL,
    login    VARCHAR(32),
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(32)  NOT NULL,
    locked   BOOLEAN      NOT NULL DEFAULT FALSE,
    version  INTEGER      NOT NULL DEFAULT 0,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Gestión de páginas estáticas

DROP TABLE IF EXISTS estatica CASCADE;
CREATE TABLE estatica (
    id            BIGSERIAL,
    identificador TEXT UNIQUE,
    CONSTRAINT estatica_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS estatica_i18n CASCADE;
CREATE TABLE estatica_i18n (
    id            BIGSERIAL,
    titulo        VARCHAR(100),
    contenido     TEXT,
    idioma_codigo VARCHAR(20),
    estatica_id   BIGINT,
    CONSTRAINT estatica_i18n_pk PRIMARY KEY (id),
    CONSTRAINT estatica_i18n_fk_estatica FOREIGN KEY (estatica_id) REFERENCES estatica (id) ON DELETE CASCADE
);

-- ============================== DOMINIO ==============================

-- pais --
DROP TABLE IF EXISTS pais CASCADE;
CREATE TABLE pais (
    id     BIGSERIAL,
    nombre TEXT NOT NULL,
    nombre_en TEXT,
    nombre_gl TEXT,
    CONSTRAINT pais_pk PRIMARY KEY (id)
);

-- ciudad --
DROP TABLE IF EXISTS ciudad CASCADE;
CREATE TABLE ciudad (
    id      BIGSERIAL,
    nombre  TEXT NOT NULL,
    nombre_en TEXT NOT NULL,
    nombre_gl TEXT NOT NULL,
    pais_id BIGINT,
    CONSTRAINT ciudad_pk PRIMARY KEY (id),
    CONSTRAINT ciudad_fk_pais FOREIGN KEY (pais_id) REFERENCES pais (id) ON DELETE SET NULL
);

-- 1.1 prototexto --

DROP TABLE IF EXISTS prototexto CASCADE;
CREATE TABLE prototexto (
    -- Campos comunes de la superclase GenericEntity --
    id     BIGSERIAL,
    -- Campos comunes de la superclase ProduccionActiva --
    numeracion_automatica VARCHAR(15) DEFAULT NULL,
    revisado BOOLEAN DEFAULT FALSE,
    concluido BOOLEAN DEFAULT FALSE,
    genero   VARCHAR(20),
    assunto   VARCHAR(20),
    resumo TEXT,
    iconografia BOOLEAN DEFAULT FALSE,
    rasuras BOOLEAN DEFAULT FALSE,
    sublinhado BOOLEAN DEFAULT FALSE,
    anotasoes BOOLEAN DEFAULT FALSE,
    citado BOOLEAN DEFAULT FALSE,
    notas TEXT,
    -- Campos comunes a la subidaPrototextos de ficheros: imagenes, crítica, etc. --
    completo_path TEXT,
    imagen_path TEXT,
    transcripcion_path TEXT,
    genetica_path TEXT,
    -- Fin campos comunes --
    titulo TEXT NOT NULL,
    publicacion_dia INTEGER,
    publicacion_mes INTEGER,
    publicacion_ano INTEGER,
    primeira_linha TEXT,
    ultima_linha TEXT,
    numero_paginas BIGINT,
    pais_id BIGINT,
    ciudad_id BIGINT,
    descrisao TEXT,
    localizasao TEXT,
    tipo   VARCHAR(3),
    folha   VARCHAR(3),
    pagina_cuaderno BIGINT,
    hipervinculo VARCHAR(40),
    autor_biblio VARCHAR(30),
    ano_biblio INTEGER,
    hipervinculoCorrespondencia VARCHAR(40),
    autorCorrespondencia VARCHAR(30),
    fecha_correspondenciaDia INTEGER,
    fecha_CorrespondenciaMes INTEGER,
    fecha_correspondenciaAno INTEGER,
    pagina_inibiblio INTEGER,
    pagina_finbiblio INTEGER,
    folhas_soltas BOOLEAN DEFAULT FALSE,
    caderno BOOLEAN DEFAULT FALSE,
    material_autonomo BOOLEAN DEFAULT FALSE,
    biblioteca BOOLEAN DEFAULT FALSE,
    correspondencia BOOLEAN DEFAULT FALSE,
    -- Constraints --
    CONSTRAINT prototexto_pk PRIMARY KEY (id),
    CONSTRAINT prototexto_fk_pais FOREIGN KEY (pais_id) REFERENCES pais (id) ON DELETE SET NULL,
    CONSTRAINT prototexto_fk_cidade FOREIGN KEY (ciudad_id) REFERENCES ciudad (id) ON DELETE SET NULL
);

-- Recursos de la producción pasiva citados en los prototextos --

DROP TABLE IF EXISTS cita_prototexto CASCADE;
CREATE TABLE cita_prototexto (
    id      BIGSERIAL,
    referencia_pasivo VARCHAR(30),
    autor_citado VARCHAR(30),
    ano_citado BIGINT,
    pagina_ini_citado BIGINT,
    pagina_fin_citado BIGINT,
    prototexto_id BIGINT,
    CONSTRAINT cita_prototexto_pk PRIMARY KEY (id),
    CONSTRAINT cita_fk_prototexto FOREIGN KEY (prototexto_id) REFERENCES prototexto (id) ON DELETE SET NULL

);

-- Otras subidaPrototextos múltiples de archivos a prototexto --

DROP TABLE IF EXISTS subida_prototexto CASCADE;
CREATE TABLE subida_prototexto (
    id      BIGSERIAL,
    otro_path TEXT,
    prototexto_id BIGINT,
    CONSTRAINT subida_prototexto_pk PRIMARY KEY (id),
    CONSTRAINT subida_prototexto_fk_prototexto FOREIGN KEY (prototexto_id) REFERENCES prototexto (id) ON DELETE SET NULL

);

-- 1.2 libro --

DROP TABLE IF EXISTS libro CASCADE;
CREATE TABLE libro (
    -- Campos comunes de la superclase GenericEntity --
    id     BIGSERIAL,
    -- Campos comunes de la superclase ProduccionActiva --
    numeracion_automatica VARCHAR(15) DEFAULT NULL,
    revisado BOOLEAN DEFAULT FALSE,
    concluido BOOLEAN DEFAULT FALSE,
    genero   VARCHAR(20),
    assunto   VARCHAR(20),
    resumo TEXT,
    iconografia BOOLEAN DEFAULT FALSE,
    rasuras BOOLEAN DEFAULT FALSE,
    sublinhado BOOLEAN DEFAULT FALSE,
    anotasoes BOOLEAN DEFAULT FALSE,
    citado BOOLEAN DEFAULT FALSE,
    notas TEXT,
    -- Campos comunes a la subida de ficheros: imagenes, crítica, etc. --
    completo_path TEXT,
    imagen_path TEXT,
    transcripcion_path TEXT,
    genetica_path TEXT,
    -- Fin campos comunes --
    titulo TEXT,
    autoria TEXT,
    cdu    VARCHAR(30),
    repositorio TEXT,
    primera_ed_dia INTEGER,
    primera_ed_mes INTEGER,
    primera_ed_ano INTEGER,
    tipo   VARCHAR(30) NOT NULL,
    CONSTRAINT libro_pk PRIMARY KEY (id)
);

-- Recursos de la producción pasiva citados en los libros --

DROP TABLE IF EXISTS cita_libro CASCADE;
CREATE TABLE cita_libro (
    id      BIGSERIAL,
    referencia_pasivo VARCHAR(30),
    autor_citado VARCHAR(30),
    ano_citado BIGINT,
    pagina_ini_citado BIGINT,
    pagina_fin_citado BIGINT,
    libro_id BIGINT,
    CONSTRAINT cita_libro_pk PRIMARY KEY (id),
    CONSTRAINT cita_libro_fk_libro FOREIGN KEY (libro_id) REFERENCES libro (id) ON DELETE SET NULL
);


-- Otras subidaPrototextos múltiples de archivos a libros --

DROP TABLE IF EXISTS subida_libro CASCADE;
CREATE TABLE subida_libro (
    id      BIGSERIAL,
    otro_path TEXT,
    libro_id BIGINT,
    CONSTRAINT subida_libro_pk PRIMARY KEY (id),
    CONSTRAINT subida_libro_fk_libro FOREIGN KEY (libro_id) REFERENCES libro (id) ON DELETE SET NULL
);

-- Ediciones de cada libro --

DROP TABLE IF EXISTS edicion CASCADE;
CREATE TABLE edicion (
    id      BIGSERIAL,
    titulo TEXT,
    edicion_dia INT,
    edicion_mes INT,
    edicion_ano INT,
    pais_id BIGINT,
    ciudad_id BIGINT,
    numero_paginas BIGINT,
    coleccion TEXT,
    numero_coleccion INT,
    isbn VARCHAR(40),
    tirada BIGINT,
    descripcion TEXT,
    localizacion TEXT,
    libro_id BIGINT,
    CONSTRAINT edicion_pk PRIMARY KEY (id),
    CONSTRAINT edicion_fk_pais FOREIGN KEY (pais_id) REFERENCES pais(id) ON DELETE SET NULL,
    CONSTRAINT edicion_fk_cidade FOREIGN KEY (ciudad_id) REFERENCES ciudad (id) ON DELETE SET NULL,
    CONSTRAINT edicion_fk_libro FOREIGN KEY (libro_id) REFERENCES libro (id) ON DELETE SET NULL

);

-- 1.3 periodico --

DROP TABLE IF EXISTS periodico CASCADE;
CREATE TABLE periodico (
    -- Campos comunes de la superclase GenericEntity --
    id     BIGSERIAL,
    -- Campos comunes de la superclase ProduccionActiva --
    numeracion_automatica VARCHAR(15) DEFAULT NULL,
    revisado BOOLEAN DEFAULT FALSE,
    concluido BOOLEAN DEFAULT FALSE,
    genero   VARCHAR(20),
    assunto   VARCHAR(20),
    resumo TEXT,
    iconografia BOOLEAN DEFAULT FALSE,
    rasuras BOOLEAN DEFAULT FALSE,
    sublinhado BOOLEAN DEFAULT FALSE,
    anotasoes BOOLEAN DEFAULT FALSE,
    citado BOOLEAN DEFAULT FALSE,
    notas TEXT,
    -- Campos comunes a la subidaPrototextos de ficheros: imagenes, crítica, etc. --
    completo_path TEXT,
    imagen_path TEXT,
    transcripcion_path TEXT,
    genetica_path TEXT,
    -- Fin campos comunes --
    titulo TEXT,
    pseudonimo BOOLEAN DEFAULT FALSE,
    lide TEXT,
    numero_volume BIGINT,
    periodico_dia INTEGER,
    periodico_mes INTEGER,
    periodico_ano INTEGER,
    pais_id BIGINT,
    ciudad_id BIGINT,
    numero_paginas BIGINT,
    pagina_inicial BIGINT,
    pagina_final BIGINT,
    repositorio TEXT,
    descrisao TEXT,
    localisasao TEXT,
    tipo VARCHAR(30) NOT NULL,
    CONSTRAINT periodico_pk PRIMARY KEY (id),
    CONSTRAINT periodico_fk_pais FOREIGN KEY (pais_id) REFERENCES pais (id) ON DELETE SET NULL,
    CONSTRAINT periodico_fk_cidade FOREIGN KEY (ciudad_id) REFERENCES ciudad (id) ON DELETE SET NULL
);

-- Recursos de la producción pasiva citados en los periodicos --

DROP TABLE IF EXISTS cita_periodico CASCADE;
CREATE TABLE cita_periodico (
    id      BIGSERIAL,
    referencia_pasivo VARCHAR(30),
    autor_citado VARCHAR(30),
    ano_citado BIGINT,
    pagina_ini_citado BIGINT,
    pagina_fin_citado BIGINT,
    periodico_id BIGINT,
    CONSTRAINT cita_periodico_pk PRIMARY KEY (id),
    CONSTRAINT cita_periodico_fk_periodico FOREIGN KEY (periodico_id) REFERENCES periodico (id) ON DELETE SET NULL

);

-- Otras subidaPrototextos múltiples de archivos a periodicos --

DROP TABLE IF EXISTS subida_periodico CASCADE;
CREATE TABLE subida_periodico (
    id      BIGSERIAL,
    otro_path TEXT,
    periodico_id BIGINT,
    CONSTRAINT subida_periodico_pk PRIMARY KEY (id),
    CONSTRAINT subida_periodico_fk_periodico FOREIGN KEY (periodico_id) REFERENCES periodico (id) ON DELETE SET NULL
);



-- CAMPOS COMUNES A LIBROS Y PERIODICOS --


-- Agente --

DROP TABLE IF EXISTS agente CASCADE;
CREATE TABLE agente (
    id       BIGSERIAL,
    nombre   VARCHAR(40),
    apellidos VARCHAR(40),
    CONSTRAINT agente_pk PRIMARY KEY (id)
);

-- Organización --

DROP TABLE IF EXISTS organizacion CASCADE;
CREATE TABLE organizacion (
    id       BIGSERIAL,
    nombre TEXT NOT NULL,
    nombre_en TEXT,
    nombre_gl TEXT,
    CONSTRAINT organizacion_pk PRIMARY KEY (id)
);

-- Funcion de agentes --

DROP TABLE IF EXISTS funcion_agente CASCADE;
CREATE TABLE funcion_agente (
    id       BIGSERIAL,
    nombre TEXT NOT NULL,
    nombre_en TEXT,
    nombre_gl TEXT,
    CONSTRAINT funcion_agente_pk PRIMARY KEY (id)
);


-- Funcion de organizaciones --

DROP TABLE IF EXISTS funcion_organizacion CASCADE;
CREATE TABLE funcion_organizacion (
    id       BIGSERIAL,
    nombre TEXT NOT NULL,
    nombre_en TEXT,
    nombre_gl TEXT,
    CONSTRAINT funcion_organizacion_pk PRIMARY KEY (id)
);

-- Agentes de cada edicion --

DROP TABLE IF EXISTS edicion_agente CASCADE;
CREATE TABLE edicion_agente (
    id      BIGSERIAL,
    edicion_id BIGINT,
    agente_id BIGINT,
    funcion_agente_id BIGINT,
    CONSTRAINT edicion_agente_pk PRIMARY KEY (id),
    CONSTRAINT edicion_agente_fk_edicion FOREIGN KEY (edicion_id) REFERENCES edicion (id) ON DELETE SET NULL,
    CONSTRAINT edicion_agente_fk_agente FOREIGN KEY (agente_id) REFERENCES agente (id) ON DELETE SET NULL,
    CONSTRAINT edicion_agente_fk_funcion_agente FOREIGN KEY (funcion_agente_id) REFERENCES funcion_agente (id) ON DELETE SET NULL
);

-- Agentes de cada periodico --

DROP TABLE IF EXISTS periodico_agente CASCADE;
CREATE TABLE periodico_agente (
    id      BIGSERIAL,
    periodico_id BIGINT,
    agente_id BIGINT,
    funcion_agente_id BIGINT,
    CONSTRAINT periodico_agente_pk PRIMARY KEY (id),
    CONSTRAINT periodico_agente_fk_periodico FOREIGN KEY (periodico_id) REFERENCES periodico (id) ON DELETE SET NULL,
    CONSTRAINT periodico_agente_fk_agente FOREIGN KEY (agente_id) REFERENCES agente (id) ON DELETE SET NULL,
    CONSTRAINT periodico_agente_fk_funcion_agente FOREIGN KEY (funcion_agente_id) REFERENCES funcion_agente (id) ON DELETE SET NULL
);

-- Tipologia de una organizacion --

DROP TABLE IF EXISTS tipologia CASCADE;
CREATE TABLE tipologia (
    id        BIGSERIAL,
    nombre    TEXT NOT NULL,
    nombre_en TEXT,
    nombre_gl TEXT,
    CONSTRAINT tipologia_pk PRIMARY KEY (id)
);

-- Organizaciones de cada edicion --

DROP TABLE IF EXISTS edicion_organizacion CASCADE;
CREATE TABLE edicion_organizacion (
    id      BIGSERIAL,
    edicion_id BIGINT,
    organizacion_id BIGINT,
    funcion_organizacion_id BIGINT,
    tipologia_id BIGINT,
    CONSTRAINT edicion_organizacion_pk PRIMARY KEY (id),
    CONSTRAINT edicion_organizacion_fk_edicion FOREIGN KEY (edicion_id) REFERENCES edicion (id) ON DELETE SET NULL,
    CONSTRAINT edicion_organizacion_fk_organizacion FOREIGN KEY (organizacion_id) REFERENCES organizacion (id) ON DELETE SET NULL,
    CONSTRAINT edicion_organizacion_fk_funcion FOREIGN KEY (funcion_organizacion_id) REFERENCES funcion_organizacion (id) ON DELETE SET NULL,
    CONSTRAINT edicion_organizacion_fk_tipologia FOREIGN KEY (tipologia_id) REFERENCES tipologia (id) ON DELETE SET NULL

);



DROP TABLE IF EXISTS periodico_organizacion CASCADE;
CREATE TABLE periodico_organizacion (
    id      BIGSERIAL,
    periodico_id BIGINT,
    organizacion_id BIGINT,
    funcion_organizacion_id BIGINT,
    tipologia_id BIGINT,
    CONSTRAINT periodico_organizacion_pk PRIMARY KEY (id),
    CONSTRAINT periodico_organizacion_fk_periodico FOREIGN KEY (periodico_id) REFERENCES periodico (id) ON DELETE SET NULL,
    CONSTRAINT periodico_organizacion_fk_organizacion FOREIGN KEY (organizacion_id) REFERENCES organizacion (id) ON DELETE SET NULL,
    CONSTRAINT periodico_organizacion_fk_funcion FOREIGN KEY (funcion_organizacion_id) REFERENCES funcion_organizacion (id) ON DELETE SET NULL,
    CONSTRAINT periodico_organizacion_fk_tipologia FOREIGN KEY (tipologia_id) REFERENCES tipologia (id) ON DELETE SET NULL

);


-- capitulo --

DROP TABLE IF EXISTS capitulo CASCADE;
CREATE TABLE capitulo (
    id      BIGSERIAL,
    titulo TEXT,
    pagina_inicio INT,
    pagina_fin INT,
    libro_id BIGINT,
    CONSTRAINT capitulo_pk PRIMARY KEY (id),
    CONSTRAINT capitulo_fk_libro FOREIGN KEY (libro_id) REFERENCES libro (id) ON DELETE SET NULL

);

-- Agentes de cada capitulo --

DROP TABLE IF EXISTS capitulo_agente CASCADE;
CREATE TABLE capitulo_agente (
    id      BIGSERIAL,
    capitulo_id BIGINT,
    agente_id BIGINT,
    funcion_agente_id BIGINT,
    CONSTRAINT capitulo_agente_pk PRIMARY KEY (id),
    CONSTRAINT capitulo_agente_fk_periodico FOREIGN KEY (capitulo_id) REFERENCES capitulo (id) ON DELETE SET NULL,
    CONSTRAINT capitulo_agente_fk_agente FOREIGN KEY (agente_id) REFERENCES agente (id) ON DELETE SET NULL,
    CONSTRAINT capitulo_agente_fk_funcion_agente FOREIGN KEY (funcion_agente_id) REFERENCES funcion_agente (id) ON DELETE SET NULL
);




-- ============================== Fin DOMINIO ==============================
