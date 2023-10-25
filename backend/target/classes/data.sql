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
('O design do cabo, no formato ergonômico triangular, proporciona melhor empunhadura para o artista.', 'https://www.armarinhosaojose.com.br/octopus/design/images/94/products/b/kit-pincel-art-erg-red-condor-ref403-3u.jpg', 'Kit Pincel Artístico Ergonômico Redondo Condor Ref.403 com 03 Und', 37.09, 1), 
('Ideal para Metais, Reparações Automotivas e Eletrodomésticos.', 'https://www.armarinhosaojose.com.br/octopus/design/images/94/products/b/lixa-dagua-tigre-r600-grao-p220.jpg', 'Lixa DÁgua Tigre Ref.600 Grão P220', 3.19, 2),
('Utensílio de plástico, prático para limpeza de pincéis. Dimensões 165 x 165 x 90 mm no formato quadrado.', 'https://www.armarinhosaojose.com.br/octopus/design/images/94/products/b/bandeja-pinceis-condor-585.jpg', 'Bandeja Para Lavar Pincéis Condor Ref.585 - Cores Sortidas', 37.09, 2),
('Moisés de crochê para bebê dormir nos primeiros meses de vida perto dos pais. Leves e práticos, feitos à mão em fio de malha e mdf.', 'https://img.elo7.com.br/product/zoom/447BF07/berco-cesto-moises-de-croche-bebe.jpg', 'Berço Cesto Moisés de Crochê para Bebê/ Kit Enxoval', 1299, 2),
('Espelho Retangular Luminoso. Espelho de 4mm com acabamento lapidado e iluminação interna.', 'https://img.elo7.com.br/product/zoom/2A63C20/espelho-retangular-com-iluminacao-de-led-espelho-luminoso.jpg', 'Espelho Retangular com Iluminação de Led - 2,00m x 1,20m', 3688.8, 2),
('Manta para poltrona ou sofá feita com toda a trama do tricô Irlandês. Pode ser feita em outras cores. Medida da Manta 2.00x 80', 'https://img.elo7.com.br/product/zoom/127CA41/manta-para-poltrona-ou-sofa-manta-de-trico.jpg', 'Manta para Poltrona ou Sofá', 699.9, 2),
('O Kit Oficce Fit , este Kit possui itens que vão deixar seu espaço mais completo, organizado e sofisticado, seja no trabalho ou em casa.', 'https://img.elo7.com.br/product/zoom/40993E2/kit-office-fit-case-de-notebook.jpg', 'Kit Office Fit', 194.9, 3);


INSERT INTO USUARIO(login, senha, tipo_usuario) VALUES 
('cliente', 'admin', 0), 
('admin', 'admin', 1);

INSERT INTO CLIENTE(nome, usuario_id) VALUES 
('nome admin', 2), 
('nome cliente', 1);