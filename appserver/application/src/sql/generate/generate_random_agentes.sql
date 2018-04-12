TRUNCATE TABLE agente CASCADE;

-- Generar X agentes de manera aleatoria
INSERT INTO agente (nombre, pseudonimo)
    SELECT
        'Agente apellidos ' || f.n,
        'Pseudónimo del agente ' || f.n
    FROM generate_series(1, 40) AS f(n);

