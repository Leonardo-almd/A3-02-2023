package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroLivro extends JFrame {

    private Runnable atualizarTabela;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JComboBox<String> cmbTipo;
    private JComboBox<Integer> cmbNota;
    private JButton btnCadastrarLivro;

    public CadastroLivro(Runnable atualizarTabela) {
        this.atualizarTabela = atualizarTabela;
        setTitle("Cadastro de Livro");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents();
        layoutComponents();
        attachListeners();
    }

    private void initComponents() {
        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);

        String[] tiposLivro = { "Romance", "Técnico", "Ficção" };
        cmbTipo = new JComboBox<>(tiposLivro);

        Integer[] notas = new Integer[11];
        for (int i = 0; i <= 10; i++) {
            notas[i] = i;
        }
        cmbNota = new JComboBox<>(notas);

        btnCadastrarLivro = new JButton("Cadastrar Livro");
    }

    private void layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Título:"));
        panel.add(txtTitulo);
        panel.add(new JLabel("Autor:"));
        panel.add(txtAutor);
        panel.add(new JLabel("Tipo:"));
        panel.add(cmbTipo);
        panel.add(new JLabel("Nota:"));
        panel.add(cmbNota);
        panel.add(new JLabel());

        panel.add(btnCadastrarLivro);

        add(panel, BorderLayout.CENTER);
    }

    private void attachListeners() {
        btnCadastrarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
    }

    private void cadastrarLivro() {
    String titulo = txtTitulo.getText();
    String autor = txtAutor.getText();
    String tipo = (String) cmbTipo.getSelectedItem();
    int nota = (int) cmbNota.getSelectedItem();

    String sql = "INSERT INTO livros (titulo, autor, tipo, mediaNota) VALUES (?, ?, ?, ?)";

    try (Connection connection = ConexaoMySQL.obterConexao();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, titulo);
        statement.setString(2, autor);
        statement.setString(3, tipo);
        statement.setDouble(4, nota);

        statement.executeUpdate();

        inserirAvaliacaoNoBanco(titulo, nota);
        
        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        atualizarTabela.run();
        dispose();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro no banco de dados", "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private int obterIdDoLivro(String livro) {
        try (Connection connection = ConexaoMySQL.obterConexao()) {
            String sql = "SELECT id FROM livros WHERE titulo = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, livro);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private void inserirAvaliacaoNoBanco(String livro, int nota) {
        try (Connection connection = ConexaoMySQL.obterConexao()) {
            String sql = "INSERT INTO avaliacoes (usuario_id, livro_id, nota) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, UsuarioLogado.getId());
                statement.setInt(2, obterIdDoLivro(livro));
                statement.setInt(3, nota);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
