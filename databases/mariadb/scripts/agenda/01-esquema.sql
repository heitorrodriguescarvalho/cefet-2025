-- Criação do banco de dados `agenda`.
-- Caso já exista, o banco de dados será apagado.
-- DROP DATABASE IF EXISTS agenda_v2;
-- CREATE DATABASE IF NOT EXISTS agenda_v2;


-- Seleção do banco de dados `agenda`.
-- USE agenda_v2;


-- Criação da tabela `contato`.
CREATE TABLE contato (
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(64) NOT NULL,
  email VARCHAR(128),
  endereco_logradouro VARCHAR(64),
  endereco_numero char(6),
  endereco_complemento VARCHAR(32),
  endereco_cidade VARCHAR(64),
  endereco_cep CHAR(8)
);

-- Criação da tabela `contato_telefone`.
CREATE TABLE contato_telefone (
  contato_id INT NOT NULL,
  telefone CHAR(11) NOT NULL,
  CONSTRAINT PRIMARY KEY (contato_id, telefone),
  CONSTRAINT FOREIGN KEY (contato_id) REFERENCES contato (id)
);
