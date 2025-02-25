INSERT INTO schema_salary.collaborator (id, created_by, last_modified_by, name, email, position, version)
VALUES
    ('216bca7a-d610-4a4f-9a6b-089f7601fc90', 'system', 'system', 'Alice Silva', 'yuri.claro@outlook.com', 'ADMINISTRATOR', 0),
    ('a1c109b4-a76b-43dc-ac97-9df45370682a', 'system', 'system', 'Bruno Oliveira', 'yuri.claro99@gmail.com', 'OPERATIONAL', 0),
    ('4b62a08b-29b6-4f63-bd68-9b5fc9e5a0b1', 'system', 'system', 'Carlos Sousa', 'carlos.sousa@example.com', 'MANAGER', 0),
    ('b1e35761-8b75-4640-9b5f-590df9bfb35f', 'system', 'system', 'Diana Rocha', 'diana.rocha@example.com', 'INTERN', 0),
    ('9e4fcb94-9240-48f1-8ad5-389abf5d3d8f', 'system', 'system', 'Eduardo Santos', 'eduardo.santos@example.com', 'ADMINISTRATOR', 0),
    ('d1f21b85-1a56-4870-81db-bdc35e364f69', 'system', 'system', 'Fernanda Lima', 'fernanda.lima@example.com', 'ANALYST', 0),
    ('34f2b417-46b1-44ae-9445-4667e17a5d4e', 'system', 'system', 'Gabriel Costa', 'gabriel.costa@example.com', 'OPERATIONAL', 0),
    ('a57df274-4d13-4024-8494-b8a8c0402d42', 'system', 'system', 'Isabela Martins', 'isabela.martins@example.com', 'MANAGER', 0),
    ('eb2b3a57-baf7-4ad7-a5b5-5eb9fc2454b1', 'system', 'system', 'João Pereira', 'joao.pereira@example.com', 'ANALYST', 0),
    ('56cb2d9d-24f5-46e7-98de-f1dbefcc060e', 'system', 'system', 'Karla Almeida', 'karla.almeida@example.com', 'ADMINISTRATOR', 0);

INSERT INTO schema_salary.salary (id, created_by, last_modified_by, collaborator_id, status, presentation_date, acceptance_date, effective_date, version)
VALUES
    ('987c3796-8e96-49bc-bb48-480b91c4d98f','system', 'system', '216bca7a-d610-4a4f-9a6b-089f7601fc90', 'CURRENT', '2024-07-12', '2024-08-01', '2024-09-01', 0),
    ('2132a2db-9d5d-4237-bd79-0b9df592f8e6','system', 'system', 'a1c109b4-a76b-43dc-ac97-9df45370682a', 'CURRENT', '2024-05-25', '2024-06-01', '2024-07-01', 0),
    ('f3fcb04e-4cdb-470a-84c3-12293c58de71','system', 'system', '4b62a08b-29b6-4f63-bd68-9b5fc9e5a0b1', 'CURRENT', '2024-04-22', '2024-05-01', '2024-06-01', 0),
    ('cda47d82-dc59-464d-b5b1-7ed76314cbb9','system', 'system', 'd1f21b85-1a56-4870-81db-bdc35e364f69', 'CURRENT', '2024-03-12', '2024-04-01', '2024-05-01', 0),
    ('332132ec-0151-4443-b5e9-5315487b80b1','system', 'system', '9e4fcb94-9240-48f1-8ad5-389abf5d3d8f', 'CURRENT', '2024-08-15', '2024-09-01', '2024-10-01', 0),
    ('aa49169f-e2bb-44fc-8a7c-75f9dbe0c229','system', 'system', 'd1f21b85-1a56-4870-81db-bdc35e364f69', 'CURRENT', '2024-04-16', '2024-05-01', '2024-06-01', 0),
    ('501b30ef-0ddd-4f3c-a133-32cc78ebfb62','system', 'system', '34f2b417-46b1-44ae-9445-4667e17a5d4e', 'CURRENT', '2024-05-14', '2024-06-01', '2024-07-01', 0),
    ('d29d6bbf-1f65-4483-8b27-4f123b0c9a1a','system', 'system', 'a57df274-4d13-4024-8494-b8a8c0402d42', 'CURRENT', '2024-02-19', '2024-03-01', '2024-04-01', 0),
    ('af2f572b-1455-4f47-893f-3c7b9c12a6df','system', 'system', 'eb2b3a57-baf7-4ad7-a5b5-5eb9fc2454b1', 'CURRENT', '2024-08-09', '2024-09-01', '2024-10-01', 0),
    ('f14e73ab-2768-4c6b-b4d2-ae123c5d8dcd','system', 'system', '56cb2d9d-24f5-46e7-98de-f1dbefcc060e', 'CURRENT', '2024-05-06', '2024-06-01', '2024-07-01', 0),
    ('a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7','system', 'system', '216bca7a-d610-4a4f-9a6b-089f7601fc90', 'PENDING', '2024-11-21', '2024-11-21', '2024-11-22', 0);

INSERT INTO schema_salary.salary_component (id, created_by, last_modified_by, salary_id, type, cover_flex, value, version)
VALUES
    ('67479be8-cf37-4210-9563-883fd49dca4d', 'system', 'system', '987c3796-8e96-49bc-bb48-480b91c4d98f', 'SALARY_BASE', NULL, 3000.00, 0),
    ('847826d5-ba1b-4a7d-bf40-c43c36f426a1', 'system', 'system', '987c3796-8e96-49bc-bb48-480b91c4d98f', 'EXEMPTION', NULL, 500.00, 0),
    ('421f4383-4013-459e-acaf-4947db1bae56', 'system', 'system', '987c3796-8e96-49bc-bb48-480b91c4d98f', 'SUBSISTENCE_ALLOWANCE', NULL, 200.00, 0),
    ('fba09239-99bf-4e59-b25e-ae486270b9d8', 'system', 'system', '987c3796-8e96-49bc-bb48-480b91c4d98f', 'COVERFLEX', 'HEALTH_INSURANCE', 150.00, 0),
    ('f3a0a6da-0ed4-41e0-9522-3f6dacb093f5', 'system', 'system', '987c3796-8e96-49bc-bb48-480b91c4d98f', 'COVERFLEX', 'MEAL_VOUCHER', 100.00, 0),

    ('e934d4c4-350b-4754-bd97-3ff1ed53f3cd', 'system', 'system', '2132a2db-9d5d-4237-bd79-0b9df592f8e6', 'SALARY_BASE', NULL, 3200.00, 0),
    ('d6dcd7d9-d036-4eaf-bc26-b0c3e16d7b39', 'system', 'system', '2132a2db-9d5d-4237-bd79-0b9df592f8e6', 'EXEMPTION', NULL, 600.00, 0),
    ('69a72be0-03e3-450b-b123-c3f320cbdaaf', 'system', 'system', '2132a2db-9d5d-4237-bd79-0b9df592f8e6', 'SUBSISTENCE_ALLOWANCE', NULL, 250.00, 0),
    ('fe71a7b3-c2f4-4959-8b5a-37450c7307a6', 'system', 'system', '2132a2db-9d5d-4237-bd79-0b9df592f8e6', 'COVERFLEX', 'HEALTH_INSURANCE', 180.00, 0),
    ('3b48a372-bf94-46f3-99ea-97ad2640db29', 'system', 'system', '2132a2db-9d5d-4237-bd79-0b9df592f8e6', 'COVERFLEX', 'MEAL_VOUCHER', 120.00, 0),

    ('279218cf-9e18-4f73-b02b-4b52c2ff14e7', 'system', 'system', 'f3fcb04e-4cdb-470a-84c3-12293c58de71', 'SALARY_BASE', NULL, 3400.00, 0),
    ('4c413b0e-bb8d-44a3-9676-1b7089e54129', 'system', 'system', 'f3fcb04e-4cdb-470a-84c3-12293c58de71', 'EXEMPTION', NULL, 650.00, 0),
    ('9a50b61e-7d1d-43db-8722-e99234ec8bdf', 'system', 'system', 'f3fcb04e-4cdb-470a-84c3-12293c58de71', 'SUBSISTENCE_ALLOWANCE', NULL, 280.00, 0),
    ('0e28318d-0625-4a49-b0a7-49fd2eaf03f6', 'system', 'system', 'f3fcb04e-4cdb-470a-84c3-12293c58de71', 'COVERFLEX', 'TRANSPORT_VOUCHER', 90.00, 0),
    ('ba0b877f-76f3-43f2-9310-86fa742fa4d4', 'system', 'system', 'f3fcb04e-4cdb-470a-84c3-12293c58de71', 'COVERFLEX', 'GYM_BENEFIT', 70.00, 0),

    ('cfb7ed47-e415-47bc-bfbd-779d4a47b364', 'system', 'system', 'cda47d82-dc59-464d-b5b1-7ed76314cbb9', 'SALARY_BASE', NULL, 3100.00, 0),
    ('13b8e0c4-7c89-4890-b6e7-dbd84a1adf5a', 'system', 'system', 'cda47d82-dc59-464d-b5b1-7ed76314cbb9', 'EXEMPTION', NULL, 550.00, 0),
    ('eaa62050-bd7b-4d85-9c12-34de2cb6631a', 'system', 'system', 'cda47d82-dc59-464d-b5b1-7ed76314cbb9', 'SUBSISTENCE_ALLOWANCE', NULL, 230.00, 0),
    ('def03fd9-b2f8-429e-bffb-f8978d3a22c6', 'system', 'system', 'cda47d82-dc59-464d-b5b1-7ed76314cbb9', 'COVERFLEX', 'HEALTH_INSURANCE', 160.00, 0),
    ('5c2c4bb4-4d3b-4b38-8cbb-bdeaf567cfc0', 'system', 'system', 'cda47d82-dc59-464d-b5b1-7ed76314cbb9', 'COVERFLEX', 'MEAL_VOUCHER', 110.00, 0),

    ('5f16f4be-9fe7-47f5-a52b-31249d3035a4', 'system', 'system', '332132ec-0151-4443-b5e9-5315487b80b1', 'SALARY_BASE', NULL, 3300.00, 0),
    ('c1efed1e-1f6d-478b-8a36-49c0be824fb1', 'system', 'system', '332132ec-0151-4443-b5e9-5315487b80b1', 'EXEMPTION', NULL, 620.00, 0),
    ('beaa7b8e-65f2-4e8d-b458-5b94e28e5f45', 'system', 'system', '332132ec-0151-4443-b5e9-5315487b80b1', 'SUBSISTENCE_ALLOWANCE', NULL, 270.00, 0),
    ('5c05c54d-5a33-442b-91d5-07f9a93706ad', 'system', 'system', '332132ec-0151-4443-b5e9-5315487b80b1', 'COVERFLEX', 'TRANSPORT_VOUCHER', 80.00, 0),
    ('420f7f3f-5cb7-4d7f-b702-8d29832b9d9b', 'system', 'system', '332132ec-0151-4443-b5e9-5315487b80b1', 'COVERFLEX', 'GYM_BENEFIT', 65.00, 0),

    ('ba88b74e-4884-4bc5-b493-12e7687429c9', 'system', 'system', 'aa49169f-e2bb-44fc-8a7c-75f9dbe0c229', 'SALARY_BASE', NULL, 3250.00, 0),
    ('d0040341-22a5-42a3-8c7e-053d9b5f6882', 'system', 'system', 'aa49169f-e2bb-44fc-8a7c-75f9dbe0c229', 'EXEMPTION', NULL, 630.00, 0),
    ('5bb5e010-b226-44b3-8c56-6c0a8fc7c95e', 'system', 'system', 'aa49169f-e2bb-44fc-8a7c-75f9dbe0c229', 'SUBSISTENCE_ALLOWANCE', NULL, 260.00, 0),
    ('1e2a0e98-130f-4235-89d7-0e6b77e4c2f2', 'system', 'system', 'aa49169f-e2bb-44fc-8a7c-75f9dbe0c229', 'COVERFLEX', 'HEALTH_INSURANCE', 170.00, 0),
    ('a941ebf3-17b1-4f56-93bc-f0ea8f3405d6', 'system', 'system', 'aa49169f-e2bb-44fc-8a7c-75f9dbe0c229', 'COVERFLEX', 'MEAL_VOUCHER', 105.00, 0),

    ('56e0acef-a23e-4426-afc0-ae06e01f04e0', 'system', 'system', '501b30ef-0ddd-4f3c-a133-32cc78ebfb62', 'SALARY_BASE', NULL, 3500.00, 0),
    ('3447bee3-a877-4c1f-b100-6dd2061a86f0', 'system', 'system', '501b30ef-0ddd-4f3c-a133-32cc78ebfb62', 'EXEMPTION', NULL, 600.00, 0),
    ('4fb55090-c819-4498-8716-733cf8ddfaaf', 'system', 'system', '501b30ef-0ddd-4f3c-a133-32cc78ebfb62', 'SUBSISTENCE_ALLOWANCE', NULL, 250.00, 0),
    ('30f22147-1554-400b-a022-a9f3a6fd9bba', 'system', 'system', '501b30ef-0ddd-4f3c-a133-32cc78ebfb62', 'COVERFLEX', 'TRANSPORT_VOUCHER', 80.00, 0),
    ('8607b9e7-a4fd-4fb5-b006-ef93c8591a6e', 'system', 'system', '501b30ef-0ddd-4f3c-a133-32cc78ebfb62', 'COVERFLEX', 'GYM_BENEFIT', 120.00, 0),

    ('9cfe33b4-0a65-46b2-9217-9e2ebad42ab7', 'system', 'system', 'd29d6bbf-1f65-4483-8b27-4f123b0c9a1a', 'SALARY_BASE', NULL, 3100.00, 0),
    ('e9b23c3c-8b44-452f-bf29-9e2e3ba865d9', 'system', 'system', 'd29d6bbf-1f65-4483-8b27-4f123b0c9a1a', 'EXEMPTION', NULL, 610.00, 0),
    ('7a9e86b4-3cb3-4919-b3c2-3f2c5ef0a9ba', 'system', 'system', 'd29d6bbf-1f65-4483-8b27-4f123b0c9a1a', 'COVERFLEX', 'HEALTH_INSURANCE', 150.00, 0),

    ('ecfa1b44-5147-4854-a4c5-6f09ab76a9bc', 'system', 'system', 'af2f572b-1455-4f47-893f-3c7b9c12a6df', 'SALARY_BASE', NULL, 3400.00, 0),
    ('3f89bb6b-3d1d-4a96-b0d5-b75e9a91459c', 'system', 'system', 'af2f572b-1455-4f47-893f-3c7b9c12a6df', 'SUBSISTENCE_ALLOWANCE', NULL, 280.00, 0),
    ('08b6d734-8c9f-4957-bf43-84d67237a6ec', 'system', 'system', 'af2f572b-1455-4f47-893f-3c7b9c12a6df', 'COVERFLEX', 'MEAL_VOUCHER', 95.00, 0),

    ('1d8cfa97-45b4-4e2e-bd35-7e9c64e3a8d5', 'system', 'system', 'f14e73ab-2768-4c6b-b4d2-ae123c5d8dcd', 'SALARY_BASE', NULL, 3600.00, 0),
    ('7e67eb4f-3ec4-4cbb-bdd1-4a8f7b95e6a3', 'system', 'system', 'f14e73ab-2768-4c6b-b4d2-ae123c5d8dcd', 'EXEMPTION', NULL, 640.00, 0),
    ('bc4a63ab-ef37-4b6f-8ec2-9c8b173a6ab5', 'system', 'system', 'f14e73ab-2768-4c6b-b4d2-ae123c5d8dcd', 'COVERFLEX', 'GYM_BENEFIT', 110.00, 0),

    ('8f5b2e1a-6d48-411a-a88f-9c5b12a6d123', 'system', 'system', 'a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7', 'SALARY_BASE', NULL, 4500.00, 0),
    ('a32b1c67-8e45-4cbb-bdd2-5a7d3b84c9f3', 'system', 'system', 'a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7', 'EXEMPTION', NULL, 800.00, 0),
    ('7c3b89e2-5f67-48a8-9b2d-6e8b5c94b3e9', 'system', 'system', 'a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7', 'SUBSISTENCE_ALLOWANCE', NULL, 300.00, 0),
    ('2b8e9f6a-5d7b-41a8-bd6c-9f5c4b83e7a3', 'system', 'system', 'a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7', 'COVERFLEX', 'HEALTH_INSURANCE', 200.00, 0),
    ('9f7b6e1c-4a67-4c8a-bd2d-5a3c7b84e9f5', 'system', 'system', 'a27f84c3-3c77-4a9b-bdd2-9f5e6c83a8f7', 'COVERFLEX', 'TRANSPORT_VOUCHER', 120.00, 0);


