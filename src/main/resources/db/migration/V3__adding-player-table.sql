CREATE TABLE player
(
    id                   serial  PRIMARY KEY,
    created_at           DATE NOT NULL,
    username             VARCHAR(50) NOT NULL,
    lobby_id             bigint NOT NULL
);