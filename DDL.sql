DROP TABLE IF EXISTS odontologos;

CREATE TABLE odontologos (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  apellido VARCHAR(100) NOT NULL,
  matricula VARCHAR(100) NOT NULL
);