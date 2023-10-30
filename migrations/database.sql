CREATE TABLE usuarios (
    _id INT PRIMARY KEY,
    nome VARCHAR(255),
    data_nascimento DATE,
    cpf VARCHAR(255),
    endereco VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255),
    qtd_emprestismos INT    
);

CREATE TABLE livros (
    _id INT PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    editora VARCHAR(255),
    ano_lancamento INT,
    genero VARCHAR(255),
    qtd_paginas INT,
    qtd_edicao INT,
    status_reserva BOOLEAN
)

CREATE TABLE funcionarios (
    _id INT PRIMARY KEY,
    nome VARCHAR(255),
    cpf VARCHAR(255),
    data_contratacao DATE,
    cargo VARCHAR(255),
    email VARCHAR(255),
    telefone VARCHAR(255)
)

CREATE TABLE reservas (
    _id INT PRIMARY KEY,
    livro INT,
    data_reserva DATE,
    data_expiracao DATE,
    usuario INT
)

INSERT INTO usuarios (username, password) VALUES ('admin', 'admin');
INSERT INTO usuarios (username, password) VALUES ('comum', 'comum');
