CREATE DATABASE meubanco;
USE meubanco;
CREATE TABLE usuarios (
    _id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    senha VARCHAR(100) CHECK (senha REGEXP '^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$')
    data_nascimento DATE NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    qtd_emprestismos INT NOT NULL 
    permissao VARCHAR(10) CHECK (permissao IN ('admin', 'comum')) 
);

CREATE TABLE livros (
    _id INT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editora VARCHAR(255) NOT NULL,
    ano_lancamento INT NOT NULL,
    genero VARCHAR(255) NOT NULL,
    qtd_paginas INT NOT NULL,
    qtd_edicao INT NOT NULL,
    status_reserva BOOLEAN NOT NULL
)

CREATE TABLE funcionarios (
    _id INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255) NOT NULL,
    data_contratacao DATE NOT NULL,
    cargo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(255)
)

CREATE TABLE reservas (
    _id INT PRIMARY KEY,
    livro INT NOT NULL,
    data_reserva DATE NOT NULL,
    data_expiracao DATE NOT NULL,
    usuario INT NOT NULL
)
INSERT INTO usuarios (_id, nome, senha, data_nascimento, cpf, endereco, email, telefone, qtd_emprestismos, permissao) VALUES (1,'admin_u', 'admin1', 01/01/2001, '12345678900', 'rua random', '123@gmail.com', '912345679', 10, 'admin');
INSERT INTO usuarios (_id, nome, senha, data_nascimento, cpf, endereco, email, telefone, qtd_emprestismos, permissao) VALUES (2,'comum_u', 'comum1', 01/01/2001, '12345678900', 'rua random', '123@gmail.com', '912345679', 10, 'comum');
