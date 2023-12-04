--добавление музыкантов
INSERT INTO musician(name, members) VALUES ('Black Sabbath', 'Ози, Дио, Тони Айоми');
INSERT INTO musician(name, members) VALUES ('Death Grips', 'МС Ехать');
INSERT INTO musician(name, members) VALUES ('Conrad Schnitzler', 'Conrad Schnitzler');
INSERT INTO musician(name, members) VALUES ('Midori Takada', 'Midori Takada');

--добавление продуктов
INSERT INTO product(name, type, genre, year, price, description, musician_id) VALUES ('Black Sabbath', 'Альбом', 'Рок', 1970, 2000, 'Деблютная пластинка родоначальников hard rock', 1);
INSERT INTO product(name, type, genre, year, price, description, musician_id) VALUES ('Year Of The Snitch', 'Альбом', 'Электроника', 2018, 999, 'Последний альбом группы Death Grips', 2);
INSERT INTO product(name, type, genre, year, price, description, musician_id) VALUES ('Rot', 'Альбом', 'Электроника', 1973, 8432, 'Одна из первых пластинок родоначальника электроники', 3);

--добавление аккаунтов
INSERT INTO person(login, password, role) VALUES ('tegor2003', 'qw123e', 'ROLE_ADMIN');
INSERT INTO person(login, password, role) VALUES ('qr14', '123', 'ROLE_USER');
INSERT INTO person(login, password, role) VALUES ('zzas', '345', 'ROLE_USER');

--добавление комментариев
INSERT INTO comment(person_id, text) VALUES (2, 'Прям хорошо');
INSERT INTO comment(person_id, text) VALUES (3, 'Прям плохо');

--добавление заказов
INSERT INTO orders(completed, price, person_id) VALUES (true, 1000, 2);
INSERT INTO orders(price, person_id) VALUES (13890, 3);

--добавление продукт-заказ
INSERT INTO product_order(product_id, order_id) VALUES (1,1);
INSERT INTO product_order(product_id, order_id) VALUES (3,1);
INSERT INTO product_order(product_id, order_id) VALUES (2,2);

