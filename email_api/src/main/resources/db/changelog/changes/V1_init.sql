CREATE SCHEMA IF NOT EXISTS schema_email;
SET search_path TO schema_email;

CREATE TABLE IF NOT EXISTS email (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_by VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    owner VARCHAR(255) NOT NULL,
    sender VARCHAR(255) NOT NULL,
    receiver VARCHAR(255) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    send_date_email TIMESTAMP NOT NULL,
    status VARCHAR(255),
    version BIGINT
);