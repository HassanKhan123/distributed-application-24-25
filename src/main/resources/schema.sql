CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255),
    product_price VARCHAR(255),
    product_size VARCHAR(255),
    product_color VARCHAR(255),
);

CREATE TABLE IF NOT EXISTS inventory (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);


