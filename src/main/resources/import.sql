INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES (1, 'Alimentos', 'Alimentos para comer', current_timestamp, current_timestamp);
INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES (2, 'Bebidas', 'Bebidas para beber', current_timestamp, current_timestamp);
INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES (3, 'Remedios', 'Remedios', current_timestamp, current_timestamp);

INSERT INTO tb_drugstore (id, city, neighborhood, number, postal_code, state, street, cnpj, created_at, email, name, updated_at) VALUES (1, 'Santos', 'Centro', '40', '30350280', 'SP', 'Rua 15', '98298733000159', current_timestamp, 'araujo@email.com', 'Drogaria Araujo', current_timestamp);
INSERT INTO drugstore_phones (drugstore_id, phones) VALUES (1, '(31) 99954-2554');
INSERT INTO drugstore_phones (drugstore_id, phones) VALUES (1, '(31) 3333-3333');

insert into tb_product (category_id, created_at, description, inventory_quantity, name, price, sku, status, updated_at) values (1, current_timestamp, 'Remedio para dor de cabeça', 60, 'Neosaldina', 10.00, 'NE200115', 'ACTIVE', current_timestamp);
insert into tb_product (category_id, created_at, description, inventory_quantity, name, price, sku, status, updated_at) values (2, current_timestamp, 'Coca cola 200ml', 100, 'Coca cola', 5.30, 'CE200150', 'ACTIVE', current_timestamp);

insert into tb_user (city, neighborhood, number, postal_code, state, street, birthday, cpf, created_at, email, name, password, updated_at, user_type) values ('Belo Horizonte', 'Centro', '50', '30350280', 'MG', 'Rua 20', '1990-10-30', '11779016689', current_timestamp, 'danieldutra@email.com', 'Daniel', '$2a$12$/aD9ryl25y4aXCkkkEwZ2.KYuS5ZOGwW1BNDcXbt0Dn5Lw2G1Mmea', current_timestamp, 'ADMIN');
insert into tb_user (city, neighborhood, number, postal_code, state, street, birthday, cpf, created_at, email, name, password, updated_at, user_type) values ('São Paulo', 'Sé', '10', '205465540', 'Sp', 'Rua 1050', '1994-06-25', '12838562045', current_timestamp, 'airton@email.com', 'Airton', '$2a$12$/aD9ryl25y4aXCkkkEwZ2.KYuS5ZOGwW1BNDcXbt0Dn5Lw2G1Mmea', current_timestamp, 'CLIENT');