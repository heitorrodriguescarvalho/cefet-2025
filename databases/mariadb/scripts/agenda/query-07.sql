SELECT
id,
nome,
email,
(SELECT GROUP_CONCAT(telefone SEPARATOR ", ") FROM contato_telefone WHERE contato_id = id) AS telefones,
CONCAT(endereco_logradouro, ", ", endereco_numero, ", ", COALESCE(CONCAT(endereco_complemento, " - "), ""), endereco_cep, " - ", endereco_cidade) AS endereco
FROM contato
ORDER BY nome
LIMIT 10
OFFSET 20;