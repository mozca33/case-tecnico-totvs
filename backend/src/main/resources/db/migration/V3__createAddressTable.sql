CREATE TABLE addresses (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    public_place VARCHAR(100),
    number VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_client_address FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);