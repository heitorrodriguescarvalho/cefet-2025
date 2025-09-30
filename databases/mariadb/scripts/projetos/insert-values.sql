INSERT INTO
  enginner (name)
VALUES
  ('João Silva'),
  ('Maria Santos'),
  ('Pedro Oliveira'),
  ('Ana Costa'),
  ('Carlos Ferreira'),
  ('Lucia Mendes');

INSERT INTO
  project (nome)
VALUES
  ('Sistema de Gestão'),
  ('Aplicativo Mobile'),
  ('Site Corporativo'),
  ('Sistema de Vendas'),
  ('Portal do Cliente');

INSERT INTO
  project_enginner (project_id, enginner_id)
VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (2, 4),
  (3, 1),
  (3, 5),
  (4, 2),
  (4, 6),
  (5, 4),
  (5, 5);