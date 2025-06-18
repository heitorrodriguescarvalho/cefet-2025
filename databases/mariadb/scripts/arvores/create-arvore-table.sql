CREATE TABLE arvore (
  id INT PRIMARY KEY AUTO_INCREMENT,
  especie VARCHAR(32) NOT NULL,
  localizacao_latitude FLOAT NOT NULL,
  localizacao_longitude FLOAT NOT NULL
);

CREATE TABLE arvore_altura (
  arvore_id INT,
  data CHAR(8),
  valor FLOAT,

  PRIMARY KEY (arvore_id, data, valor),
  FOREIGN KEY (arvore_id) REFERENCES arvore (id)
);