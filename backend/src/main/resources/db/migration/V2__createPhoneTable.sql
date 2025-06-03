CREATE TABLE phones (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    ddd VARCHAR(2) NOT NULL,
    main_number VARCHAR(9) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_client_phone FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);