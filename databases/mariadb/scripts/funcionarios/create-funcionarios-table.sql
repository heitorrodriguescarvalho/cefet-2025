CREATE TABLE funcionarios (
  cpf VARCHAR(11) PRIMARY KEY,
  nome VARCHAR(127) NOT NULL,
  endereco_logradouro VARCHAR(127) NOT NULL,
  endereco_numero INTEGER NOT NULL,
  endereco_complemento VARCHAR(127)
);

CREATE TABLE dependentes (
  funcionario_cpf VARCHAR(11) NOT NULL,
  cpf VARCHAR(11) NOT NULL,
  nome VARCHAR(127) NOT NULL,
  FOREIGN KEY (funcionario_cpf) REFERENCES funcionarios(cpf),
  PRIMARY KEY (funcionario_cpf, cpf)
);