DROP TABLE IF EXISTS environments;

DROP TABLE IF EXISTS access_controllers;

CREATE TABLE environments (
  id INT PRIMARY KEY,
  nome VARCHAR(1270) NOT NULL
);

CREATE TABLE access_controllers (
  id INT PRIMARY KEY,
  environment_id INT NOT NULL,
  ip VARCHAR(15) NOT NULL,
  FOREIGN KEY (environment_id) REFERENCES environments(id)
)