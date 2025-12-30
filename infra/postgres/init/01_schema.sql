CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    puntos INT NOT NULL,
    nivel INT NOT NULL,
    registro TIMESTAMP NOT NULL,
    language VARCHAR(10) NOT NULL
);

