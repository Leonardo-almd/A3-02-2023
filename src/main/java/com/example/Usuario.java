package com.example;

public class Usuario {
    private int id;
    private String nome;    
    private String nomeUsuario;
    private String idade;
    private String sexo;
    private String[] livrosPreferidos;
    private String senha;
    private boolean admin;

    public Usuario() {
    }

    public Usuario(int id, String nome, String nomeUsuario, String idade, String sexo, String[] livrosPreferidos, String senha, boolean admin) {
        this.id = id;
        this.nome = nome;        
        this.nomeUsuario = nomeUsuario;
        this.idade = idade;
        this.sexo = sexo;
        this.livrosPreferidos = livrosPreferidos;
        this.senha = senha;
        this.admin = admin;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String[] getLivrosPreferidos() {
        return livrosPreferidos;
    }

    public void setLivrosPreferidos(String[] livrosPreferidos) {
        this.livrosPreferidos = livrosPreferidos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

}
