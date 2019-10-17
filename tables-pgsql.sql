create table usuarios (
	usuarioid serial,
	nome varchar(70),
	usuario varchar(25) not null,
	senha char(60) not null,
	is_admin boolean default false,
	is_caixa boolean default true,
	is_system boolean default false,
	is_financeiro boolean default false,
	unique (usuario)
);

insert into usuarios (nome, usuario, senha, is_admin) values ('Sistema', 'admin', crypt('admin', gen_salt('bf')), true);