
-- Cambios efectuados por orden cronológico

\c ggal1701 postgres

/*Para contemplar el número de ediciones y capítulos en libros y poder ordenar en función de ellos*/

ALTER TABLE libro ADD COLUMN numero_ediciones INTEGER DEFAULT 0;
ALTER TABLE libro ADD COLUMN numero_capitulos INTEGER DEFAULT 0;

/*Hasta que nos digan lo que significa la categoría en esta entidad, la omitimos*/

ALTER TABLE libro ALTER COLUMN tipo DROP NOT NULL;


/* Para poner como enumerado la sección de soporte del prototexto*/


ALTER TABLE prototexto ADD COLUMN sampliado VARCHAR(3);
ALTER TABLE prototexto ADD COLUMN sreducido VARCHAR(3);


/*Hasta que sepamos si quieren internacionalizar, la omitimos*/

ALTER TABLE ciudad ALTER COLUMN nombre_en DROP NOT NULL;
ALTER TABLE ciudad ALTER COLUMN nombre_gl DROP NOT NULL;


/* Cambiamos el tipo de relación entre organización y tipología. Una org siempre será de un tipo */

ALTER TABLE organizacion ADD COLUMN tipologia_id BIGINT;
ALTER TABLE organizacion ADD FOREIGN KEY (tipologia_id) REFERENCES tipologia(id);

/*Eliminamos las anteriores relaciones de tipologia*/


ALTER TABLE edicion_organizacion DROP CONSTRAINT edicion_organizacion_fk_tipologia;
ALTER TABLE periodico_organizacion DROP CONSTRAINT periodico_organizacion_fk_tipologia;

ALTER TABLE edicion_organizacion DROP COLUMN tipologia_id;
ALTER TABLE periodico_organizacion DROP COLUMN tipologia_id;



/* Para poder meter las fechas dentro de la categoría común producción activa: refactorizo y cambio nombres tb BD*/

ALTER TABLE periodico RENAME COLUMN periodico_dia TO publicacion_dia;
ALTER TABLE periodico RENAME COLUMN periodico_mes TO publicacion_mes;
ALTER TABLE periodico RENAME COLUMN periodico_ano TO publicacion_ano;

/* En prototexto ya se llamaban así */

ALTER TABLE libro RENAME COLUMN primera_ed_dia TO publicacion_dia;
ALTER TABLE libro RENAME COLUMN primera_ed_mes TO publicacion_mes;
ALTER TABLE libro RENAME COLUMN primera_ed_ano TO publicacion_ano;





-- Cambio efectuado 26/09/2017

--Modifico estructura de la BD para aplicar buscador avanzado sobre la clase base

-- 1.1 prototexto --

DROP TABLE IF EXISTS produccion_activa CASCADE;
CREATE TABLE produccion_activa (
    id     BIGSERIAL UNIQUE,
    titulo TEXT NOT NULL,
    numeracion_automatica VARCHAR(15) DEFAULT NULL,
    publicacion_dia INTEGER,
    publicacion_mes INTEGER,
    publicacion_ano INTEGER,
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
    completo_path TEXT,
    imagen_path TEXT,
    transcripcion_path TEXT,
    genetica_path TEXT,
    CONSTRAINT produccion_activa_pk PRIMARY KEY (id)
);

-- 1.1 prototexto --

DROP TABLE IF EXISTS prototexto CASCADE;
CREATE TABLE prototexto (
    id BIGSERIAL REFERENCES produccion_activa ON DELETE CASCADE,
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

-- 1.2 libro --

DROP TABLE IF EXISTS libro CASCADE;
CREATE TABLE libro (
    id BIGSERIAL REFERENCES produccion_activa ON DELETE CASCADE,
    autoria TEXT,
    cdu    VARCHAR(30),
    repositorio TEXT,
    tipo   VARCHAR(30) NOT NULL,
    CONSTRAINT libro_pk PRIMARY KEY (id)
);

-- 1.3 periodico --

DROP TABLE IF EXISTS periodico CASCADE;
CREATE TABLE periodico (
    id BIGSERIAL REFERENCES produccion_activa ON DELETE CASCADE,
    pseudonimo BOOLEAN DEFAULT FALSE,
    lide TEXT,
    numero_volume BIGINT,
    pais_id BIGINT,
    ciudad_id BIGINT,
    numero_paginas BIGINT,
    pagina_inicial BIGINT,
    pagina_final BIGINT,
    repositorio TEXT,
    descrisao TEXT,
    localisasao TEXT,
    tipo   VARCHAR(30) NOT NULL,
    CONSTRAINT periodico_pk PRIMARY KEY (id),
    CONSTRAINT periodico_fk_pais FOREIGN KEY (pais_id) REFERENCES pais (id) ON DELETE SET NULL,
    CONSTRAINT periodico_fk_cidade FOREIGN KEY (ciudad_id) REFERENCES ciudad (id) ON DELETE SET NULL
);

/*Para contemplar el número de ediciones y capítulos en libros y poder ordenar en función de ellos*/

ALTER TABLE libro ADD COLUMN numero_ediciones INTEGER DEFAULT 0;
ALTER TABLE libro ADD COLUMN numero_capitulos INTEGER DEFAULT 0;

/*Hasta que nos digan lo que significa la categoría en esta entidad, la omitimos*/

ALTER TABLE libro ALTER COLUMN tipo DROP NOT NULL;

/* Para poner como enumerado la sección de soporte del prototexto*/


ALTER TABLE prototexto ADD COLUMN sampliado VARCHAR(3);
ALTER TABLE prototexto ADD COLUMN sreducido VARCHAR(3);


ALTER TABLE produccion_activa ADD COLUMN type VARCHAR(20);


/* Corrijo los elementos que pueden ser nulos, porque realmente del formulario no tienen por qué saber todo*/


ALTER TABLE periodico ALTER COLUMN tipo DROP NOT NULL;
