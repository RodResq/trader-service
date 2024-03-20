drop table if exists clube;
drop table if exists classificacao cascade;
drop table if exists campeonato cascade;
drop table if exists clube_goole;
drop table if exists jogo cascade;
drop table if exists odd cascade;
drop table if exists mercado;

create table clube (
    id_clube bigint auto_increment primary key,
    nome varchar(255) not null,
    link_imagem varchar(255) not null,
    constraint uk_clube unique (nome)
);

create table campeonato (
    id_campeonato  bigint auto_increment primary key,
    nome  varchar(255) not null,
    ativo smallint not null,
    constraint uk_campeonato unique (nome)
);

create table classificacao
(
    id_classificacao bigint auto_increment primary key,
    id_campeonato    bigint       not null,
    clube            varchar(255) not null,
    ponto            int          not null,
    partida_jogada   int          not null,
    vitoria          int          not null,
    empate           int          not null,
    derrota          int          not null,
    gols_pro         int          not null,
    gols_contra      int          not null,
    gols_saldo       int          not null,
    data             date         not null,
    constraint fk_campeonato foreign key (id_campeonato) references campeonato (id_campeonato)
);

create table clube_goole
(
    id_clube_google bigint auto_increment primary key,
    id_campeonato bigint not null,
    nome  varchar(255) not null
);

create table jogo
(
    id_jogo bigint auto_increment primary key,
    id_campeonato bigint not null,
    id_clube_casa bigint not null,
    id_clube_visitante bigint not null,
    dia varchar(20) not null,
    hora varchar(20) not null,
    constraint unique_index unique (id_campeonato, id_clube_casa, id_clube_visitante, dia, hora),
    constraint fk_jgcampeonato foreign key (id_campeonato) references campeonato (id_campeonato),
    constraint fk_clubecasa foreign key (id_clube_casa) references clube (id_clube),
    constraint fk_clubevisitante foreign key (id_clube_visitante) references clube (id_clube)
);

create table odd
(
    id_odd bigint auto_increment primary key,
    id_campeonato bigint not null,
    id_jogo bigint not null,
    pontuacao decimal(3, 2) null,
    tipo smallint not null,
    constraint fk_oddcampeonato foreign key (id_campeonato) references campeonato (id_campeonato),
    constraint fk_jogo foreign key (id_jogo) references jogo (id_jogo) on delete cascade
);

create table mercado
(
    id_mercado int auto_increment primary key,
    nome varchar(255) null,
    jogo    varchar(255) null,
    horario varchar(255) null
);

