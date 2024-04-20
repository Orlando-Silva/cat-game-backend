-- CREATE EXTENSION IF NOT EXISTS uuid-ossp;

CREATE TABLE phrase
(
    id                   serial  PRIMARY KEY,
    created_at           DATE,
    text                 VARCHAR(200) NOT NULL,
    active               BOOLEAN
);

CREATE TABLE image
(
    id                   serial  PRIMARY KEY,
    created_at           DATE,
    source               VARCHAR(200) NOT NULL,
    active               BOOLEAN
);

CREATE TABLE lobby
(
    id                   serial  PRIMARY KEY,
    created_at           DATE NOT NULL,
    status               VARCHAR(50) NOT NULL,
    room_id              UUID NOT NULL,
    host_id              INT
)

