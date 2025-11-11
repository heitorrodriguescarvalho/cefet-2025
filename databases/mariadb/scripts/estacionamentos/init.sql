-- Active: 1755368362547@@localhost@3306@db
-- Criação obrigatória do banco
DROP DATABASE IF EXISTS estacionamento;

CREATE DATABASE estacionamento DEFAULT CHARACTER
SET
  utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

USE estacionamento;

-- =========================
-- Dicionários
-- =========================
CREATE TABLE Modelo (
  codMod INT AUTO_INCREMENT PRIMARY KEY,
  desc_2 VARCHAR(40) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE Cliente (
  -- CPF como CHAR(11) para preservar zeros à esquerda
  cpf CHAR(11) PRIMARY KEY,
  nome VARCHAR(60) NOT NULL,
  dtNasc DATE
) ENGINE = InnoDB;

CREATE TABLE Patio (
  num INT PRIMARY KEY,
  ender VARCHAR(40) NOT NULL,
  capacidade INT NOT NULL,
  CHECK (capacidade >= 0)
) ENGINE = InnoDB;

-- =========================
-- Veículo
-- =========================
CREATE TABLE Veiculo (
  placa VARCHAR(8) PRIMARY KEY,
  modelo_codMod INT NOT NULL,
  cliente_cpf CHAR(11) NOT NULL,
  cor VARCHAR(20),
  ano INT NOT NULL,
  CONSTRAINT fk_veic_modelo FOREIGN KEY (modelo_codMod) REFERENCES Modelo (codMod) ON
  UPDATE
    RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_veic_cliente FOREIGN KEY (cliente_cpf) REFERENCES Cliente (cpf) ON
  UPDATE
    RESTRICT ON DELETE RESTRICT
) ENGINE = InnoDB;

-- =========================
-- Estaciona (histórico)
-- =========================
CREATE TABLE Estaciona (
  cod INT AUTO_INCREMENT PRIMARY KEY,
  patio_num INT NOT NULL,
  veiculo_placa VARCHAR(8) NOT NULL,
  dtEntrada DATE NOT NULL,
  hsEntrada TIME NOT NULL,
  dtSaida DATE,
  hsSaida TIME,
  CONSTRAINT fk_est_patio FOREIGN KEY (patio_num) REFERENCES Patio (num) ON
  UPDATE
    RESTRICT ON DELETE RESTRICT,
    CONSTRAINT fk_est_veiculo FOREIGN KEY (veiculo_placa) REFERENCES Veiculo (placa) ON
  UPDATE
    RESTRICT ON DELETE RESTRICT,
    -- Saída deve estar totalmente nula ou totalmente preenchida
    CHECK (
      (
        dtSaida IS NULL
        AND hsSaida IS NULL
      )
      OR (
        dtSaida IS NOT NULL
        AND hsSaida IS NOT NULL
      )
    )
) ENGINE = InnoDB;

-- POPULATE
-- ===== MODELO =====
INSERT INTO
  Modelo (codMod, desc_2)
VALUES
  (1, 'Hatch'),
  (2, 'Sedan'),
  (3, 'SUV');

-- ===== CLIENTE =====
INSERT INTO
  Cliente (cpf, nome, dtNasc)
VALUES
  ('11122233344', 'Bruno Souza', '1990-10-02'),
  ('12345678901', 'Ana Lima', '1985-05-20'),
  ('55566677788', 'Carla Dias', '1978-03-15'),
  ('99988877766', 'Diego Santos', '1989-12-01');

-- ===== PÁTIO =====
INSERT INTO
  Patio (num, ender, capacidade)
VALUES
  (1, 'Rua Central, 100', 150),
  (2, 'Av. Shopping, 200', 200);

-- ===== VEÍCULO (com ANO) =====
-- verde: AAA-0001 (Ana), BBB-0002 (Carla)
INSERT INTO
  Veiculo (placa, modelo_codMod, cliente_cpf, cor, ano)
VALUES
  ('JJJ-2020', 2, '11122233344', 'preto', 2012),
  ('JEG-1010', 3, '12345678901', 'prata', 2003),
  ('AAA-0001', 1, '12345678901', 'verde', 2005),
  ('BBB-0002', 1, '55566677788', 'verde', 1999);

-- ===== ESTACIONA =====
-- cod=1 → AAA-0001 ; cod=2 → JJJ-2020 ; cod=3 → JEG-1010
INSERT INTO
  Estaciona (
    cod,
    patio_num,
    veiculo_placa,
    dtEntrada,
    hsEntrada,
    dtSaida,
    hsSaida
  )
VALUES
  (
    1,
    1,
    'AAA-0001',
    '2025-10-01',
    '08:00:00',
    '2025-10-01',
    '11:30:00'
  ),
  (
    2,
    2,
    'JJJ-2020',
    '2025-10-02',
    '09:00:00',
    '2025-10-02',
    '12:00:00'
  ),
  (
    3,
    1,
    'JEG-1010',
    '2025-10-02',
    '10:30:00',
    '2025-10-02',
    '12:45:00'
  ),
  (
    4,
    2,
    'JJJ-2020',
    '2025-10-05',
    '14:00:00',
    '2025-10-05',
    '17:15:00'
  ),
  (
    5,
    1,
    'BBB-0002',
    '2025-10-03',
    '07:30:00',
    '2025-10-03',
    '09:10:00'
  ),
  (
    6,
    1,
    'AAA-0001',
    '2025-10-04',
    '13:00:00',
    '2025-10-04',
    '16:00:00'
  );