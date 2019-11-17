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

create table produtos (
	produtoid bigserial primary key,
	nomeid uuid not null,
	tipoid uuid not null,
	marcaid uuid not null,
	barcode varchar(25),
	custo numeric(20,2),
	venda numeric(20,2),
	medidaid uuid not null,
	estoque bigint default 1,
	unique (barcode),
	unique (nomeid, marcaid)
);

create table produtos_nome (
	nomeid uuid default uuid_generate_v4(),
	nome varchar(35) not null,
	unique (nome)
);

create table produtos_tipo (
	tipoid uuid default uuid_generate_v4(),
	tipo varchar(25) not null,
	unique (tipo)
);

create table produtos_marca (
	marcaid uuid default uuid_generate_v4(),
	marca varchar(25) not null,
	unique (marca)
);

create table produtos_medida (
	medidaid uuid default uuid_generate_v4(),
	medida varchar(25) not null,
	medida_abr char(2) not null,
	unique (medida)
);