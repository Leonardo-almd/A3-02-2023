package com.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ListaLivros extends JPanel {
    private JTable tabelaLivros;
    private DefaultTableModel modeloTabela;
    private JButton btnCadastrarLivro;
    private JButton btnCadastrarAvaliacao;

    public ListaLivros() {
        setLayout(new BorderLayout());

        modeloTabela = new DefaultTableModel();
        tabelaLivros = new JTable(modeloTabela);

        modeloTabela.addColumn("Título");
        modeloTabela.addColumn("Autor");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Nota");
        modeloTabela.addColumn("Avaliações");

        tabelaLivros.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        add(scrollPane, BorderLayout.CENTER);

        btnCadastrarLivro = new JButton("Cadastrar Novo Livro");
        btnCadastrarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroLivro();
            }
        });
        btnCadastrarAvaliacao = new JButton("Avaliar um livro");
        btnCadastrarAvaliacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirTelaCadastroAvaliacao();
            }
        });

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoesPanel.add(btnCadastrarLivro);
        botoesPanel.add(btnCadastrarAvaliacao);
        add(botoesPanel, BorderLayout.SOUTH);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tabelaLivros.getColumnCount(); i++) {
            tabelaLivros.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        carregarLivrosDoBanco();
        definirTamanhoColunas();
    }

    private void definirTamanhoColunas() {
        TableColumn column;
        for (int i = 0; i < tabelaLivros.getColumnCount(); i++) {
            column = tabelaLivros.getColumnModel().getColumn(i);
            if (i == 0 || i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            } else if (i == 3) {
                column.setPreferredWidth(20);
            } else {
                column.setPreferredWidth(50);
            }
        }
    }

    private void carregarLivrosDoBanco() {
        modeloTabela.setRowCount(0);

        try (Connection connection = ConexaoMySQL.obterConexao()) {
            String sql = "SELECT * FROM livros ORDER BY CAST(mediaNota AS DECIMAL(3, 1)) DESC";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vector<Object> linha = new Vector<>();
                    linha.add(resultSet.getString("titulo"));
                    linha.add(resultSet.getString("autor"));
                    linha.add(resultSet.getString("tipo"));
                    linha.add(resultSet.getString("mediaNota"));
                    linha.add(resultSet.getInt("qtdNota"));

                    modeloTabela.addRow(linha);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar livros do banco de dados");
        }
    }

    private void abrirTelaCadastroLivro() {
            CadastroLivro cadastroLivroFrame = new CadastroLivro(() -> {
            modeloTabela.setRowCount(0);
            carregarLivrosDoBanco();
            modeloTabela.fireTableDataChanged();
        });
        
            cadastroLivroFrame.setVisible(true);
    }

    private void abrirTelaCadastroAvaliacao() {
        CadastroAvaliacao cadastroAvaliacao = new CadastroAvaliacao(() -> {
            modeloTabela.setRowCount(0);
            carregarLivrosDoBanco();
            modeloTabela.fireTableDataChanged();
        });

        cadastroAvaliacao.setVisible(true);
    }

}
