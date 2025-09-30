CREATE TABLE enginner(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(63)
);

CREATE TABLE project(
  id INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(63)
);

CREATE TABLE project_enginner(
  project_id INT,
  enginner_id INT,
  PRIMARY KEY (project_id, enginner_id),
  FOREIGN KEY (project_id) REFERENCES project(id),
  FOREIGN KEY (enginner_id) REFERENCES enginner(id)
);