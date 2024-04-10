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