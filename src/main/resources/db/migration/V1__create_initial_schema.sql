CREATE SEQUENCE seq_id
INCREMENT 1
MINVALUE 1
MAXVALUE 99999999
START 1
CACHE 1;

CREATE SEQUENCE seq_id_produto
INCREMENT 1
MINVALUE 1
MAXVALUE 99999999
START 1
CACHE 1;

CREATE TABLE Produto (id bigint DEFAULT nextval ('seq_id_produto'), descricao varchar(255), valor numeric(19,2), estoque_id bigint DEFAULT nextval ('seq_id'), primary key(id));
CREATE TABLE Estoque (id bigint DEFAULT nextval ('seq_id'), quantidade bigint, primary key(id));