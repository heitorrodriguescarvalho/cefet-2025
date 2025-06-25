CREATE TABLE contatos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(256) NOT NULL,
    email VARCHAR(128),
    endereco_logradouro VARCHAR(32),
    endereco_numero VARCHAR(5),
    endereco_cidade VARCHAR(32)
);

CREATE TABLE telefones (
  contato_id INT,
  telefone CHAR(11),

  PRIMARY KEY (contato_id, telefone),
  FOREIGN KEY (contato_id) REFERENCES contatos (id)
);