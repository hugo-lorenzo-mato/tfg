-- =============== DATOS ===============

-- Para generar contraseñas con salt SHA256:
-- http://www.lorem-ipsum.co.uk/hasher.php
-- String to hash: admin.
-- String to salt: {admin}
--
-- Roles: ROLE_ADMIN, ROLE_EDITOR_CATALOGADOR

INSERT INTO comun.usuario (email, login, rol, validado, activado, fecha_registro, contrasena)
VALUES
    ('admin@catalogador.es', 'admin', 'ROLE_ADMIN', TRUE, TRUE, now(),
     'a4a88c0872bf652bb9ed803ece5fd6e82354838a9bf59ab4babb1dab322154e1');
INSERT INTO comun.usuario (email, login, rol, validado, activado, fecha_registro, contrasena)
VALUES ('catalogador@catalogador.es', 'catalogador', 'ROLE_CATALOGADOR', TRUE, TRUE, now(),
        '6d68e29a5408528d542e1bbbece2da65fc34554d32a9257bba21dbe990b31afc');
