CREATE TABLE estudiantes (
    ID     BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NOMBRE   VARCHAR(255) NOT NULL,
    APELLIDOS VARCHAR(255) NOT NULL,
    CORREO VARCHAR(255) NOT NULL,
    DNI VARCHAR(255) NOT NULL
);