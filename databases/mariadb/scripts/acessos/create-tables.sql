CREATE TABLE people (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE environments (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE accesses (
  person_id INT NOT NULL,
  environment_id INT NOT NULL,
  access_at DATETIME NOT NULL,
  FOREIGN KEY (person_id) REFERENCES people(id),
  FOREIGN KEY (environment_id) REFERENCES environments(id),
  PRIMARY KEY (person_id, environment_id, access_at)
);