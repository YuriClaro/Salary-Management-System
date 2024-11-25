CREATE SCHEMA IF NOT EXISTS schema_salary;
SET search_path TO schema_salary;

CREATE TABLE IF NOT EXISTS collaborator (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_by VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    version BIGINT,
    position VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS salary (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_by VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    collaborator_id UUID NOT NULL,
    status VARCHAR(50) NOT NULL,
    presentation_date DATE NOT NULL,
    acceptance_date DATE NOT NULL,
    effective_date DATE NOT NULL,
    version BIGINT,
    CONSTRAINT fk_collaborator FOREIGN KEY (collaborator_id) REFERENCES collaborator (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS salary_component (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_by VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255) NOT NULL,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    salary_id UUID NOT NULL,
    type VARCHAR(50),
    cover_flex VARCHAR(50),
    value DECIMAL(19,2),
    version BIGINT,
    CONSTRAINT fk_salary FOREIGN KEY (salary_id) REFERENCES salary (id) ON DELETE CASCADE
);
