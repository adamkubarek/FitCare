INSERT INTO categories(id, name, audit_cd) VALUES (1, 'Napoje', NOW());
INSERT INTO categories(id, name, audit_cd) VALUES (2, 'Owoce', NOW());
INSERT INTO categories(id, name, audit_cd) VALUES (3, 'Warzywa', NOW());
INSERT INTO categories(id, name, audit_cd) VALUES (4, 'Mięsa', NOW());
INSERT INTO categories(id, name, audit_cd) VALUES (5, 'Ryby', NOW());

INSERT INTO products(id, calories, carbohydrates, fat, name, protein, category_id, audit_cd) VALUES (1, 100, 10, 10, 'Coca cola', 10, 1, NOW());
INSERT INTO products(id, calories, carbohydrates, fat, name, protein, category_id, audit_cd) VALUES (2, 10, 1, 1, 'Kalafior', 1, 3, NOW());
INSERT INTO products(id, calories, carbohydrates, fat, name, protein, category_id, audit_cd) VALUES (3, 200, 10, 10, 'Banan', 10, 2, NOW());
INSERT INTO products(id, calories, carbohydrates, fat, name, protein, category_id, audit_cd) VALUES (4, 400, 60, 40, 'Ryż Basmati', 10, 1, NOW());
