CREATE TABLE IF NOT EXISTS CATEGORIA(
    id     int  primary key not null auto_increment,
    nome   varchar(255) null
);

CREATE TABLE IF NOT EXISTS PRODUTO(
    id              int primary key not null auto_increment,
    imagem          varchar(255) null,
    preco           double null,
    nome            varchar(255) null,
    descricao       varchar(255) null,
    categoria_id    int null,
    FOREIGN KEY (categoria_id) REFERENCES CATEGORIA(id)
);

CREATE TABLE IF NOT EXISTS USUARIO (
	id          int primary key not null auto_increment,
	login 		varchar(255)not null,
	senha 		varchar(255),
	tipo_usuario int not null
);


CREATE TABLE IF NOT EXISTS CLIENTE(
    id          int primary key not null auto_increment,
    nome        varchar(255) null,
    email       varchar(255) null,
    usuario_id  int null,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(id)
);

INSERT INTO CATEGORIA(nome) VALUES ('Fruits'),
                                  ('Vegetables'),
                                  ('Meat'),
                                  ('Fish'),
                                  ('Dairy'),
                                  ('Bakery'),
                                  ('Drinks'),
                                  ('Sweets'),
                                  ('Other');
                                  
                     
INSERT INTO PRODUTO(descricao, imagem, nome, preco, categoria_id) VALUES ('Fresh and juicy', 'http://lorempixel.com.br/500/400/?1', 'Apple', 40, 1);

INSERT INTO USUARIO(login, senha, tipo_usuario) values ('cliente', 'admin', 0), ('admin', 'admin', 1);
INSERT INTO CLIENTE(nome, usuario_id) VALUES ('nome admin', 2), ('nome cliente', 1);