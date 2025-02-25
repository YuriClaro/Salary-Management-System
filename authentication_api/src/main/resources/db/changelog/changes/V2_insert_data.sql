INSERT INTO schema_authentication.user_credentials (id, created_by, created_at, last_modified_by, last_modified_date, username, email, password, role, status, version)
VALUES
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'alice.silva', 'yuri.claro@outlook.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'ADMIN', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'bruno.oliveira', 'yuri.claro99@gmail.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'carlos.sousa', 'carlos.sousa@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'diana.rocha', 'diana.rocha@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'eduardo.santos', 'eduardo.santos@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'fernanda.lima', 'fernanda.lima@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'gabriel.costa', 'gabriel.costa@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'isabela.martins', 'isabela.martins@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'joao.pereira', 'joao.pereira@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'karla.almeida', 'karla.almeida@example.com',
     '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 'LOGGED_OUT', 0)

