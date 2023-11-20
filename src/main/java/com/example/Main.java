package com.example;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Configuração inicial, se necessário
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Inicia a tela de login
                new TelaLogin().setVisible(true);
            }
        });
    }
}
