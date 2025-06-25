SELECT nome FROM contatos, telefones WHERE id = contato_id AND telefone LIKE "%999";

SELECT telefone FROM contatos, telefones WHERE id = contato_id AND nome = "Ana";