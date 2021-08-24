--O spring boot reconhece o arquivo com o nome data.sql como um script de criação do banco de dados
--O nome padrao desse arquivo é data.sql para que o proprio spring gere as tabelas.
--Com a inclusao do mapeamento JPA, esse arquivo foi renomeado para transferir para o JPA a responsabilidade de criaçao das tabelas

CREATE TABLE CLIENTE (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NOME VARCHAR (100),
    CPF VARCHAR (11)
);

create TABLE PRODUTO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRICAO VARCHAR (100),
    PRECO_UNITARIO NUMERIC (20,2)
);

create TABLE PEDIDO (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENTE_FK INTEGER REFERENCES CLIENTE (ID),
    DATA_PEDIDO TIMESTAMP,
    TOTAL NUMERIC (20,2),
    STATUS VARCHAR(20)
);

CREATE  TABLE ITEM_PEDIDO(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    PEDIDO_FK INTEGER REFERENCES PEDIDO (ID),
    PRODUTO_FK INTEGER REFERENCES PRODUTO (ID),
    QUANTIDADE INTEGER
);