-- Create product table
CREATE TABLE IF NOT EXISTS product (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

-- Create join table between orders and products
CREATE TABLE IF NOT EXISTS order_product (
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk_op_order FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
    CONSTRAINT fk_op_product FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE
);

-- Performance indexes
CREATE INDEX IF NOT EXISTS idx_customer_name ON customer (name);
CREATE INDEX IF NOT EXISTS idx_order_customer_id ON "order" (customer_id);
CREATE INDEX IF NOT EXISTS idx_order_product_order_id ON order_product (order_id);
CREATE INDEX IF NOT EXISTS idx_order_product_product_id ON order_product (product_id);
CREATE INDEX IF NOT EXISTS idx_product_description ON product (description);
