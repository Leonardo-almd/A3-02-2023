package com.example;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FormularioUsuario extends JPanel {
    private JTextField campoNome;
    private JTextField campoUsuario;
    private JTextField campoIdade;
    private JComboBox<String> campoSexo;
    private JList<String> campoLivrosPreferidos;
    private JPasswordField campoSenha;
    private JCheckBox campoAdmin;

    public FormularioUsuario() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2; 

        JLabel labelTitulo = new JLabel("Adicionar Usuário");
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        add(labelTitulo, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridwidth = 1;

        campoNome = adicionarCampoComMargens("Nome:", new JTextField(15), gbc);
        campoUsuario = adicionarCampoComMargens("Usuário:", new JTextField(15), gbc);
        campoIdade = adicionarCampoComMargens("Idade:", new JTextField(15), gbc);
        campoSexo = adicionarCampoComMargens("Sexo:", criarComboBoxSexo(), gbc);
        campoLivrosPreferidos = adicionarCampoComMargens("Livros Preferidos:", criarListaLivros(), gbc);
        campoSenha = adicionarCampoComMargens("Senha:", new JPasswordField(15), gbc);
        campoAdmin = adicionarCampoComMargens("Admin:", new JCheckBox(), gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDadosNoBanco();
            }
        });
        add(btnSalvar, gbc);
    }

    private JComboBox<String> criarComboBoxSexo() {
        String[] opcoesSexo = {"Masculino", "Feminino", "Outro", "Prefiro não informar"};
        JComboBox<String> comboBox = new JComboBox<>(opcoesSexo);
        comboBox.setPreferredSize(new Dimension(170, comboBox.getPreferredSize().height));

        return comboBox;
    }

    private JList<String> criarListaLivros() {
        String[] opcoesLivros = {"Romance", "Ficção", "Técnico"};
        JList<String> lista = new JList<>(opcoesLivros);
        lista.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lista.setPreferredSize(new Dimension(170, lista.getPreferredSize().height));

        return lista;
    }

    private <T extends JComponent> T adicionarCampoComMargens(String label, T component, GridBagConstraints gbc) {
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);

        if ("Idade:".equals(label)) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;

                ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                        if (text.matches("\\d+")) {
                            super.insertString(fb, offset, text, attr);
                        }
                    }

                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                        if (text.matches("\\d+")) {
                            super.replace(fb, offset, length, text, attrs);
                        }
                    }
                });
            }
        }

        add(new JLabel(label), gbc);
        gbc.gridy++;
        add(component, gbc);
        return component;
    }
    private void salvarDadosNoBanco() {
        String nome = campoNome.getText();
        String usuario = campoUsuario.getText();
        String idade = campoIdade.getText();
        String sexo = (String) campoSexo.getSelectedItem();
        String senha = String.valueOf(campoSenha.getPassword());
        boolean admin = campoAdmin.isSelected();
        List<String> livrosPreferidos = campoLivrosPreferidos.getSelectedValuesList();

        if (nome.length() < 3) {
            JOptionPane.showMessageDialog(null, "O nome deve ter no mínimo 3 caracteres");
        } else if (usuario.length() < 3) {
            JOptionPane.showMessageDialog(null, "O usuário deve ter no mínimo 3 caracteres");
        } else if (senha.length() < 5) {
            JOptionPane.showMessageDialog(null, "A senha deve ter no mínimo 5 caracteres");
        } else if (livrosPreferidos.size() > 2) {
            JOptionPane.showMessageDialog(null, "Escolha até dois tipos de livros");
        } else {
            List<String> livrosList = livrosPreferidos;
            String livrosFormatados = String.join(",", livrosList);

            try (Connection connection = ConexaoMySQL.obterConexao()) {
                String sql = "INSERT INTO usuarios (nome, nomeUsuario, idade, sexo, livrosPreferidos, senha, admin) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, nome);
                    statement.setString(2, usuario);
                    statement.setString(3, idade);
                    statement.setString(4, sexo);
                    statement.setString(5, livrosFormatados);
                    statement.setString(6, senha);
                    statement.setBoolean(7, admin);

                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso");
                    limparCampos();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void limparCampos() {
        campoNome.setText("");
        campoUsuario.setText("");
        campoIdade.setText("0");
        campoSexo.setSelectedIndex(-1);
        campoLivrosPreferidos.clearSelection();
        campoSenha.setText("");
        campoAdmin.setSelected(false);
    }

}
