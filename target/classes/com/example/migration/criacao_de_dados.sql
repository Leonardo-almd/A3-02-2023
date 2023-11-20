CREATE TABLE IF NOT EXISTS usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    nomeUsuario VARCHAR(255) UNIQUE NOT NULL,
    idade VARCHAR(255),
    sexo VARCHAR(255),
    livrosPreferidos VARCHAR(255),
    senha VARCHAR(255) NOT NULL,
    admin BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS livros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    autor VARCHAR(255) NOT NULL,
    tipo VARCHAR(255) NOT NULL,
    mediaNota VARCHAR(255) NOT NULL,
    qtdNota INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS avaliacoes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    usuario_id INT,
    livro_id INT,
    nota INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id),
    UNIQUE (usuario_id, livro_id) -- Impede que um usuário avalie o mesmo livro mais de uma vez
);

INSERT INTO usuarios (nome, nomeUsuario, idade, sexo, livrosPreferidos, senha, admin)
VALUES
    ("NomeUsuario1", "usuario1", "25", "Masculino", "Romance", "senha123", false),
    ("NomeUsuario2", "usuario2", "30", "Feminino", "Romance,Técnico", "senha456", true);

-- Inserir livro 1
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ("Aventuras Fantásticas", "João da Silva", "Romance", "4.5", 10);

-- Inserir livro 2
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ("Ciência em Foco", "Maria Oliveira", "Ficção", "3.8", 8);
-- Inserir livro 3
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Viagem ao Desconhecido', 'Ana Rodrigues', 'Aventura', '4.2', 12);

-- Inserir livro 4
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Histórias do Além', 'Pedro Santos', 'Fantasia', '4.0', 15);

-- Inserir livro 5
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Enigma do Tempo', 'Camila Oliveira', 'Ficção Científica', '4.8', 20);

-- Inserir livro 6
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Caminhos da Natureza', 'Roberto Silva', 'Documentário', '3.5', 18);

-- Inserir livro 7
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Amores Proibidos', 'Lúcia Pereira', 'Romance', '4.7', 22);

-- Inserir livro 8
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Mistérios da Lua', 'Gustavo Mendes', 'Mistério', '4.3', 14);

-- Inserir livro 9
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Segredos do Universo', 'Clara Oliveira', 'Ficção Científica', '4.6', 16);

-- Inserir livro 10
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Enigmas do Passado', 'Ricardo Santos', 'Suspense', '3.9', 19);

-- Inserir livro 11
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Destinos Cruzados', 'Fernanda Silva', 'Romance', '4.2', 12);

-- Inserir livro 12
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Aventuras na Floresta', 'José Oliveira', 'Aventura', '3.8', 8);

-- Inserir livro 13
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Além das Estrelas', 'Isabela Pereira', 'Ficção Científica', '4.5', 10);

-- Inserir livro 14
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Pistas do Passado', 'Luiz Mendes', 'Mistério', '4.0', 15);

-- Inserir livro 15
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Poder da Imaginação', 'Amanda Rodrigues', 'Infantil', '4.8', 20);

-- Inserir livro 16
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Viagem ao Centro da Terra', 'Carlos Silva', 'Aventura', '3.5', 18);

-- Inserir livro 17
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Mistério da Ilha', 'Mariana Oliveira', 'Suspense', '4.7', 22);

-- Inserir livro 18
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Mundo Encantado', 'Rafael Santos', 'Fantasia', '4.3', 14);

-- Inserir livro 19
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('A Jornada do Herói', 'Beatriz Mendes', 'Aventura', '4.6', 16);

-- Inserir livro 20
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Desafios do Conhecimento', 'Gabriel Oliveira', 'Educativo', '3.9', 19);

-- Inserir livro 21
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Revolução Tecnológica', 'Fernando Pereira', 'Técnico', '4.2', 12);

-- Inserir livro 22
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Poemas do Coração', 'Juliana Lima', 'Poesia', '4.8', 20);

-- Inserir livro 23
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Código Secreto', 'Gustavo Oliveira', 'Suspense', '4.0', 15);

-- Inserir livro 24
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('A Arte da Guerra', 'Lucas Silva', 'Filosofia', '4.5', 10);

-- Inserir livro 25
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Descobrindo o Universo', 'Carolina Santos', 'Ciências', '4.3', 14);

-- Inserir livro 26
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Viagem pelo Tempo', 'Rodrigo Oliveira', 'Ficção Científica', '3.7', 16);

-- Inserir livro 27
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Enigma da Esfinge', 'Vitória Lima', 'Mistério', '4.6', 22);

-- Inserir livro 28
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('Reflexões do Amor', 'Fernanda Rodrigues', 'Romance', '4.9', 18);

-- Inserir livro 29
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Poder da Mente', 'Ricardo Oliveira', 'Autoajuda', '4.1', 19);

-- Inserir livro 30
INSERT INTO livros (titulo, autor, tipo, mediaNota, qtdNota)
VALUES ('O Canto dos Pássaros', 'Bianca Lima', 'Natureza', '4.7', 16);




