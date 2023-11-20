package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaMenu extends JFrame {
    private JLabel labelBoasVindas;

    public TelaMenu() {
        String nomeUsuario = UsuarioLogado.getNome();
        setTitle("Biblioteca Digital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        labelBoasVindas = new JLabel("Bem-vindo(a), " + nomeUsuario);
        add(labelBoasVindas, BorderLayout.NORTH);

        if (UsuarioLogado.getAdmin()) {
            FormularioUsuario formularioPanel = new FormularioUsuario();
            add(formularioPanel, BorderLayout.CENTER);
        } else {
            JPanel painelListaLivros = new JPanel(new BorderLayout());
            ListaLivros listaLivros = new ListaLivros();
            painelListaLivros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            painelListaLivros.add(listaLivros, BorderLayout.CENTER);
            add(painelListaLivros, BorderLayout.CENTER);
        }

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarParaTelaLogin();
            }
        });

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.add(btnLogout);
        add(botoesPanel, BorderLayout.SOUTH);
    }

    private void voltarParaTelaLogin() {
        UsuarioLogado.limparInformacoes();
        new TelaLogin().setVisible(true);
        dispose();
    }

}