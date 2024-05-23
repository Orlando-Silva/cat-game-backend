CREATE TABLE lobby
(
    id                   serial  PRIMARY KEY,
    created_at           DATE NOT NULL,
    status               VARCHAR(50) NOT NULL,
    room_id              UUID NOT NULL
);