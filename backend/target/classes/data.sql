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

CREATE TABLE IF NOT EXISTS PEDIDO(
    id     			int primary key not null auto_increment,
    data_pedido   	date null,
    cliente_id		int not null,
    FOREIGN KEY (cliente_id) REFERENCES CLIENTE(id)
);

CREATE TABLE IF NOT EXISTS ITEM_PEDIDO(
    id          		int primary key not null auto_increment,
    preco_total        	double not null default 0,
    quantidade       	int not null default 0,
    produto_id  		int not null,
    pedido_id  			int not null,
    FOREIGN KEY (produto_id) REFERENCES PRODUTO(id),
    FOREIGN KEY (pedido_id) REFERENCES PEDIDO(id)
);

INSERT INTO CATEGORIA(nome) VALUES
('Eletrodomésticos'), ('Eletrônicos'), ('Alimentação');
                                  
                     
INSERT INTO PRODUTO(descricao, imagem, nome, preco, categoria_id) VALUES 
('A Geladeira Brastemp Frost Free Duplex 375 litros tem design sofisticado e grande capacidade para armazenar os alimentos.', 'http://lorempixel.com.br/500/400/?1', 'Geladeira', 40, 1), 
('A nova plataforma de games dentro da TV garante acesso a mais de 1000 jogos nas plataformas Xbox Game Pass e Nvidia GeForce Now, sem necessidade de downloads.', 'http://lorempixel.com.br/500/400/?2', 'Televisão', 50, 2),
('A nova plataforma de games dentro da TV garante acesso a mais de 1000 jogos nas plataformas Xbox Game Pass e Nvidia GeForce Now, sem necessidade de downloads.', 'http://lorempixel.com.br/500/400/?3', 'Televisão', 50, 2),
('A nova plataforma de games dentro da TV garante acesso a mais de 1000 jogos nas plataformas Xbox Game Pass e Nvidia GeForce Now, sem necessidade de downloads.', 'http://lorempixel.com.br/500/400/?4', 'Televisão', 50, 2),
('A nova plataforma de games dentro da TV garante acesso a mais de 1000 jogos nas plataformas Xbox Game Pass e Nvidia GeForce Now, sem necessidade de downloads.', 'http://lorempixel.com.br/500/400/?5', 'Televisão', 50, 2),
('A nova plataforma de games dentro da TV garante acesso a mais de 1000 jogos nas plataformas Xbox Game Pass e Nvidia GeForce Now, sem necessidade de downloads.', 'http://lorempixel.com.br/500/400/?6', 'Televisão', 50, 2),
('O arroz cultivado é uma planta herbácea incluída na classe Liliopsida (Monocotiledônea), ordem Poales, família Poaceae, gênero Oryza. É uma planta da família.', 'http://lorempixel.com.br/500/400/?7', 'Arroz', 60, 3);


INSERT INTO USUARIO(login, senha, tipo_usuario) VALUES 
('cliente', 'admin', 0), 
('admin', 'admin', 1);

INSERT INTO CLIENTE(nome, usuario_id) VALUES 
('nome admin', 2), 
('nome cliente', 1);