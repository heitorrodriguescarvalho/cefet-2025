-- Active: 1755368362547@@localhost@3306@estacionamento
USE estacionamento;

-- =========================
-- CONSULTAS
-- =========================
-- 1. Exiba a placa e o nome dos donos de todos os veículos.
SELECT
  v.placa,
  c.nome
FROM
  Veiculo v
  INNER JOIN Cliente c ON v.cliente_cpf = c.cpf;

-- 2. Exiba o CPF e o nome do cliente que possui o veiculo de placa "JJJ-2020".
SELECT
  c.cpf,
  c.nome
FROM
  Cliente c
  INNER JOIN Veiculo v ON c.cpf = v.cliente_cpf
WHERE
  v.placa = 'JJJ-2020';

-- 3. Exiba a placa e a cor do veículo que possui o código de estacionamento 1.
SELECT
  v.placa,
  v.cor
FROM
  Veiculo v
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  e.cod = 1;

-- 4. Exiba a placa e o ano do veículo que possui o código de estacionamento 1.
SELECT
  v.placa,
  v.ano
FROM
  Veiculo v
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  e.cod = 1;

-- 5. Exiba a placa, o ano do veículo e a descrição de seu modelo, se ele possuir ano a partir de 2000.
SELECT
  v.placa,
  v.ano,
  m.desc_2
FROM
  Veiculo v
  INNER JOIN Modelo m ON v.modelo_codMod = m.codMod
WHERE
  v.ano >= 2000;

-- 6. Exiba o endereço, a data de entrada e de saída dos estacionamentos do veículo de placa "JEG-1010".
SELECT
  p.ender,
  e.dtEntrada,
  e.dtSaida
FROM
  Estaciona e
  INNER JOIN Patio p ON e.patio_num = p.num
WHERE
  e.veiculo_placa = 'JEG-1010';

-- 7. Exiba a quantidade de vezes que os veículos de cor verde estacionaram.
SELECT
  COUNT(*) AS quantidade_estacionamentos
FROM
  Estaciona e
  INNER JOIN Veiculo v ON e.veiculo_placa = v.placa
WHERE
  v.cor = 'verde';

-- 8. Liste todos os clientes que possuem carro de modelo 1.
SELECT
  c.*
FROM
  Cliente c
  INNER JOIN Veiculo v ON c.cpf = v.cliente_cpf
WHERE
  v.modelo_codMod = 1;

-- 9. Liste as placas, os horários de entrada e saída dos veículos de cor verde.
SELECT
  v.placa,
  e.hsEntrada,
  e.hsSaida
FROM
  Veiculo v
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  v.cor = 'verde';

-- 10. Liste todos os estacionamentos do veículo de placa "JJJ-2020".
SELECT
  e.*
FROM
  Estaciona e
WHERE
  e.veiculo_placa = 'JJJ-2020';

-- 11. Exiba o nome do cliente que possui o veículo cujo código do estacionamento é 2.
SELECT
  c.nome
FROM
  Cliente c
  INNER JOIN Veiculo v ON c.cpf = v.cliente_cpf
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  e.cod = 2;

-- 12. Exiba o CPF do cliente que possui o veículo cujo código do estacionamento é 3.
SELECT
  c.cpf
FROM
  Cliente c
  INNER JOIN Veiculo v ON c.cpf = v.cliente_cpf
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  e.cod = 3;

-- 13. Exiba a descrição do modelo do veículo cujo código do estacionamento é 2.
SELECT
  m.desc_2
FROM
  Modelo m
  INNER JOIN Veiculo v ON m.codMod = v.modelo_codMod
  INNER JOIN Estaciona e ON v.placa = e.veiculo_placa
WHERE
  e.cod = 2;

-- 14. Exiba a placa, o nome dos donos e a descrição dos os modelos de todos os veículos.
SELECT
  v.placa,
  c.nome,
  m.desc_2
FROM
  Veiculo v
  INNER JOIN Cliente c ON v.cliente_cpf = c.cpf
  INNER JOIN Modelo m ON v.modelo_codMod = m.codMod;