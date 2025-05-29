CREATE TABLE addresses (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    public_place VARCHAR(100) NOT NULL,
    street VARCHAR(100),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_client_address FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);