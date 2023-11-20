package com.example;

public class UsuarioLogado {
    private static int id;
    private static String nome;
    private static boolean admin;
    private static String livrosPreferidos;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        UsuarioLogado.nome = nome;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UsuarioLogado.id = id;
    }

    public static String getLivrosPreferidos() {
        return livrosPreferidos;
    }

    public static void setLivrosPreferidos(String livrosPreferidos) {
        UsuarioLogado.livrosPreferidos = livrosPreferidos;
    }

    public static boolean getAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        UsuarioLogado.admin = admin;
    }

    public static void limparInformacoes() {
        id = 0;
        nome = null;
        livrosPreferidos = null;
        admin = false;
    }
}
