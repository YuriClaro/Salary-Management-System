INSERT INTO schema_authentication.user_credentials (id, created_by, created_at, last_modified_by, last_modified_date, username, email, password, role, version)
VALUES
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'admin', 'yuri.claro@outlook.com', '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'ADMIN', 0),
    (gen_random_uuid(), 'system', CURRENT_TIMESTAMP, 'system', CURRENT_TIMESTAMP, 'user', 'yuri.claro99@gmail.com', '$2a$12$.sZwQ0G4zVvoCtYwL3kwSOYZxnzZPVciAAFilyv8.cf.2eowKaZ0W', 'USER', 0);
