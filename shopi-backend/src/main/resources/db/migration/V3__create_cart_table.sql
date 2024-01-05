CREATE TABLE IF NOT EXISTS shopiUser
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(250) UNIQUE NOT NULL,
    password VARCHAR(250)        NOT NULL
);

CREATE TABLE IF NOT EXISTS cart
(
    id      SERIAL PRIMARY KEY,
    user_fk INT NOT NULL,
    CONSTRAINT user_fk
        FOREIGN KEY (user_fk) REFERENCES shopiUser (id)
);

CREATE TABLE IF NOT EXISTS cart_product
(
    count      SMALLINT NOT NULL,
    cart_fk    INT NOT NULL,
    CONSTRAINT cart_fk
        FOREIGN KEY (cart_fk) REFERENCES cart (id),
    product_fk INT NOT NULL,
    CONSTRAINT product_fk
        FOREIGN KEY (product_fk) REFERENCES product (id),
    PRIMARY KEY (cart_fk, product_fk)
);
