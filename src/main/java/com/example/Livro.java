package com.example;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String tipo;
    private double avaliacaoMedia;
    private int qtdAvaliacao;

    public Livro() {
    }

    public Livro(int id, String titulo, String autor, String tipo, double avaliacaoMedia, int qtdAvaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.avaliacaoMedia = avaliacaoMedia;
        this.qtdAvaliacao = qtdAvaliacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public int getQtdAvaliacao() {
        return qtdAvaliacao;
    }

    public void setQtdAvaliacao(int qtdAvaliacao) {
        this.qtdAvaliacao = qtdAvaliacao;
    }
}
