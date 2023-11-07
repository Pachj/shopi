CREATE TABLE IF NOT EXISTS product
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(250) UNIQUE NOT NULL,
    description TEXT,
    price       VARCHAR(50)         NOT NULL,
    stock       INT                 NOT NULL,
    image       TEXT
);

CREATE TABLE IF NOT EXISTS specification
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(250),
    value      VARCHAR(250),
    product_fk INT NOT NULL ,
    CONSTRAINT product_fk
        FOREIGN KEY (product_fk) REFERENCES product (id)
);
