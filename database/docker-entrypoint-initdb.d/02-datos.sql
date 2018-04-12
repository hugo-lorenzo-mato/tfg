
-- =============== DATOS ===============

-- Para generar contraseñas con salt SHA256:
--  Entrar en la web http://www.lorem-ipsum.co.uk/hasher.php
-- En la parte superior ("String to hash"): admin <== Contraseña
-- En la parte inferior ("String to salt"): {admin} <== Importante ponerlo entre corchetes
--
-- Roles: ROLE_ADMIN, ROLE_CATALOGADOR

-- admin admin

\c ggal1701 postgres

INSERT INTO usuario (login, role, locked, password)
VALUES ('admin', 'ROLE_ADMIN', FALSE, 'a4a88c0872bf652bb9ed803ece5fd6e82354838a9bf59ab4babb1dab322154e1');

-- catalogador catalogador
INSERT INTO usuario (login, role, locked, password)
VALUES ('catalogador', 'ROLE_CATALOGADOR', FALSE, '6d68e29a5408528d542e1bbbece2da65fc34554d32a9257bba21dbe990b31afc');
