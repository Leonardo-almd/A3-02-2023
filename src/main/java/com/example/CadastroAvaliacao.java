package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastroAvaliacao extends JFrame {

    private Runnable atualizarTabela;

    public CadastroAvaliacao(Runnable atualizarTabela) {
        this.atualizarTabela = atualizarTabela;
        JFrame frame = new JFrame("Avaliar Livro");

        JComboBox<String> livroComboBox = new JComboBox<>();
        carregarNomesLivros(livroComboBox);

        JComboBox<Integer> notaComboBox = new JComboBox<>();
        for (int i = 0; i <= 10; i++) {
            notaComboBox.addItem(i);
        }

        JButton salvarAvaliacaoButton = new JButton("Salvar Avaliação");
        salvarAvaliacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = ConexaoMySQL.obterConexao()) {
                    String sql = "SELECT * FROM avaliacoes WHERE usuario_id = ? AND livro_id = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, UsuarioLogado.getId());
                        String livroSelecionado = (String) livroComboBox.getSelectedItem();
                        int notaSelecionada = (int) notaComboBox.getSelectedItem();
                        statement.setInt(2, obterIdDoLivro(livroSelecionado));
                        try (ResultSet resultSet = statement.executeQuery()) {
                            if (!resultSet.next()) {
                                inserirAvaliacaoNoBanco(livroSelecionado, notaSelecionada);
                                atualizarLivroNoBanco(livroSelecionado, notaSelecionada);

                                JOptionPane.showMessageDialog(null, "Avaliação realizada com sucesso!");
                                atualizarTabela.run();
                                frame.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "Usuário já avaliou este livro!");
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        frame.setLayout(new FlowLayout());

        frame.add(new JLabel("Livro:"));
        frame.add(livroComboBox);
        frame.add(new JLabel("Nota:"));
        frame.add(notaComboBox);
        frame.add(salvarAvaliacaoButton);

        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }

    private void carregarNomesLivros(JComboBox<String> livroComboBox) {
        try (Connection connection = ConexaoMySQL.obterConexao()) {
            String sql = "SELECT livros.titulo FROM livros " +
                    "LEFT JOIN avaliacoes ON livros.id = avaliacoes.livro_id " +
                    "AND avaliacoes.usuario_id = ? " +
                    "WHERE avaliacoes.livro_id IS NULL";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, UsuarioLogado.getId());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        livroComboBox.addItem(resultSet.getString("titulo"));
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar livros do banco de dados");
        }
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

    private void atualizarLivroNoBanco(String livro, int novaNota) {
        try (Connection connection = ConexaoMySQL.obterConexao()) {
            String sqlConsulta = "SELECT qtdNota, mediaNota FROM livros WHERE id = ?";
            try (PreparedStatement statementConsulta = connection.prepareStatement(sqlConsulta)) {
                statementConsulta.setInt(1, obterIdDoLivro(livro));
                try (ResultSet resultSet = statementConsulta.executeQuery()) {
                    if (resultSet.next()) {
                        int qtdNotaAtual = resultSet.getInt("qtdNota");
                        double mediaNotaAtual = resultSet.getDouble("mediaNota");

                        double novaMediaNota = ((mediaNotaAtual * qtdNotaAtual) + novaNota) / (qtdNotaAtual + 1);
                        novaMediaNota = Math.round(novaMediaNota * 10.0) / 10.0;

                        String sqlAtualizacao = "UPDATE livros SET qtdNota = ?, mediaNota = ? WHERE id = ?";
                        try (PreparedStatement statementAtualizacao = connection.prepareStatement(sqlAtualizacao)) {
                            statementAtualizacao.setInt(1, qtdNotaAtual + 1);
                            statementAtualizacao.setDouble(2, novaMediaNota);
                            statementAtualizacao.setInt(3, obterIdDoLivro(livro));
                            statementAtualizacao.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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

}
