package com.example;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaLogin extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin() {
        // Configurações da janela
        setTitle("Biblioteca Digital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal com layout BoxLayout
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Adiciona o logo
        ImageIcon logoIcon = new ImageIcon("./src/main/assets/c_1.png");
        JLabel labelLogo = new JLabel(logoIcon);
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelPrincipal.add(labelLogo);

        // Adiciona espaço em branco
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 20)));

        // Adiciona os campos de usuário e senha
        campoUsuario = new JTextField(20);
        campoSenha = new JPasswordField(20);
        adicionarCampoComLabel("Usuário:", campoUsuario, painelPrincipal);
        adicionarCampoComLabel("Senha:", campoSenha, painelPrincipal);

        // Adiciona espaço em branco
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 10)));

        // Adiciona o botão de login
        JButton btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarLogin();
            }
        });
        painelPrincipal.add(btnLogin);

        // Adiciona o painel principal à janela
        add(painelPrincipal);

        // Define a cor de fundo
        painelPrincipal.setBackground(new Color(240, 240, 240));
    }

    private void adicionarCampoComLabel(String textoLabel, JComponent campo, JPanel painel) {
        JLabel label = new JLabel(textoLabel);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        campo.setMaximumSize(new Dimension(Short.MAX_VALUE, campo.getPreferredSize().height));
        campo.setFont(new Font("Arial", Font.PLAIN, 14));

        painel.add(label);
        painel.add(campo);
    }

    private void realizarLogin() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        try (Connection connection = ConexaoMySQL.obterConexao()) {
            // Consulta SQL para verificar as credenciais
            String sql = "SELECT * FROM usuarios WHERE nomeUsuario = ? AND senha = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, usuario);
                preparedStatement.setString(2, senha);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Credenciais válidas
                        UsuarioLogado.setNome(resultSet.getString("nome"));
                        UsuarioLogado.setAdmin(resultSet.getBoolean("admin"));                      
                        UsuarioLogado.setId(resultSet.getInt("id"));
                        String livrosPreferidosString = resultSet.getString("livrosPreferidos");
                        UsuarioLogado.setLivrosPreferidos(livrosPreferidosString);

                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
                        abrirTelaMenu();
                    } else {
                        // Credenciais inválidas
                        JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados.");
        }
    }

    public void abrirTelaMenu() {
        TelaMenu telaMenu = new TelaMenu();
        telaMenu.setVisible(true);
        dispose();
        
    }

    
}
