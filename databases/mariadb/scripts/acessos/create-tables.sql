CREATE TABLE people (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE enviroments (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL
);

CREATE TABLE accesses (
  person_id INT NOT NULL,
  enviroment_id INT NOT NULL,
  access_at DATETIME NOT NULL,
  FOREIGN KEY (person_id) REFERENCES people(id),
  FOREIGN KEY (enviroment_id) REFERENCES enviroments(id),
  PRIMARY KEY (person_id, enviroment_id, access_at)
);