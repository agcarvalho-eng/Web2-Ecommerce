-- Realizando os inserts nas tabelas do banco de dados.

-- Insert na tabela produto.
insert into produto (descricao, valor) values ('Água sem gás', 5.00);
insert into produto (descricao, valor) values ('Guaraná Kuat - 2L', 8.50);
insert into produto (descricao, valor) values ('Fanta', 7.00);
insert into produto (descricao, valor) values ('Suco uva', 6.50);
insert into produto (descricao, valor) values ('Vodka Smirnoff', 9.00);
insert into produto (descricao, valor) values ('Coxinha de frango', 8.50);
insert into produto (descricao, valor) values ('Pizza de calabresa', 7.35);

--Insert na tabela Venda.
insert into Venda (dataehorario, pessoa_id) values (current_timestamp+1, 1);
insert into Venda (dataehorario, pessoa_id) values (current_timestamp, 2);
-- insert into Venda (dataehorario, pessoa_id) values (current_timestamp, 1);
-- insert into Venda (dataehorario, pessoa_id) values (current_timestamp, 3);
-- insert into Venda (dataehorario, pessoa_id) values (current_timestamp, 4);

-- Insert na tabela item_Venda.
insert into item_Venda (produto_id, quantidade, venda_id) values (1, 2, 1);
insert into item_Venda (produto_id, quantidade, venda_id) values (2, 1, 2);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (3, 1, 2);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (4, 1, 2);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (5, 1, 2);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (5, 1, 3);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (4, 2, 3);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (2, 4, 4);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (3, 4, 5);
-- insert into item_Venda (produto_id, quantidade, venda_id) values (5, 3, 5);

-- Insert na tabela pessoa_fisica
insert into pessoa_fisica (id, nome, email, cpf, senha, telefone) values (1, 'Joaquim', 'jaoquim@gmail.com', '11111111111', '1234', '1111-1111');
insert into pessoa_fisica (id, nome, email, cpf, senha, telefone) values (2, 'Pedro', 'pedro@gmail.com', '22222222222', '1234', '2222-2222');

-- Insert na tabela pessoa_juridica
insert into pessoa_juridica (id, nome, email, cnpj, senha, telefone) values (3, 'Restaurante Bom de Prato', 'bomdeprato@gmail.com', '33333333000333', '1234', '3333-3333');
insert into pessoa_juridica (id, nome, email, cnpj, senha, telefone) values (4, 'Restaurante Tudo de Bom', 'tudodebom@gmail.com', '44444444000444', '1234', '4444-4444');