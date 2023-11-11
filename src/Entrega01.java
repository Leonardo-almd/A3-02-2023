import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Entrega01 {

    private static Connection conexao;
    private static JFrame frame;

    public static void main(String[] args) {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/meubanco", "root", "*********");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        frame = new JFrame("Sistema de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentesLogin(panel);

        frame.setVisible(true);
    }

    private static void lugarComponentesLogin(JPanel panel) {
        panel.setLayout(null);

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setBounds(10, 20, 80, 25);
        panel.add(usuarioLabel);

        JTextField usuarioTextField = new JTextField(20);
        usuarioTextField.setBounds(100, 20, 165, 25);
        panel.add(usuarioTextField);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(10, 50, 80, 25);
        panel.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 50, 165, 25);
        panel.add(senhaField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                String senha = new String(senhaField.getPassword());
                fazerLogin(usuario, senha);
            }
        });
    }

    private static void fazerLogin(String usuario, String senha) {
        try {
            String query = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, senha);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String permissao = resultSet.getString("permissao");
                abrirTelaPrincipal(permissao);
            } else {
                JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos. Tente novamente.");
            }

            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void abrirTelaPrincipal(String permissao) {
        frame.getContentPane().removeAll();
        frame.repaint();

        if ("admin".equals(permissao)) {
            JButton cadastrarUsuarioButton = new JButton("Cadastrar Usuário");
            cadastrarUsuarioButton.setBounds(10, 20, 150, 25);
            frame.add(cadastrarUsuarioButton);

            cadastrarUsuarioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirTelaCadastro();
                } 
            });
        }
// Dentro do método abrirTelaPrincipal após o JLabel label:
JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
cadastrarLivroButton.setBounds(10, 80, 150, 25);
frame.add(cadastrarLivroButton);

cadastrarLivroButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        abrirTelaCadastroLivro();
    }
});

        JLabel label = new JLabel("Bem-vindo, " + permissao + "!");
        label.setBounds(10, 50, 200, 25);
        frame.add(label);

        frame.revalidate();
    }

    private static void abrirTelaCadastro() {
        JFrame frameCadastro = new JFrame("Cadastro de Usuário");
        frameCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCadastro.setSize(300, 150);

        JPanel panelCadastro = new JPanel();
        frameCadastro.add(panelCadastro);
        lugarComponentesCadastro(panelCadastro);

        frameCadastro.setVisible(true);
    }

    private static void lugarComponentesCadastro(JPanel panelCadastro) {
        panelCadastro.setLayout(null);

        JLabel novoUsuarioLabel = new JLabel("Novo Usuário:");
        novoUsuarioLabel.setBounds(10, 20, 100, 25);
        panelCadastro.add(novoUsuarioLabel);

        JTextField novoUsuarioTextField = new JTextField(20);
        novoUsuarioTextField.setBounds(120, 20, 165, 25);
        panelCadastro.add(novoUsuarioTextField);

        JLabel novaSenhaLabel = new JLabel("Nova Senha:");
        novaSenhaLabel.setBounds(10, 50, 100, 25);
        panelCadastro.add(novaSenhaLabel);

        JPasswordField novaSenhaField = new JPasswordField(20);
        novaSenhaField.setBounds(120, 50, 165, 25);
        panelCadastro.add(novaSenhaField);

        JLabel dataNascimentoLabel = new JLabel("Data de Nascimento:");
        dataNascimentoLabel.setBounds(10, 80, 150, 25);
        panelCadastro.add(dataNascimentoLabel);

        JTextField dataNascimentoTextField = new JTextField(20);
        dataNascimentoTextField.setBounds(170, 80, 150, 25);
        panelCadastro.add(dataNascimentoTextField);

        JLabel CPFLabel = new JLabel("CPF:");
        CPFLabel.setBounds(10, 110, 150, 25);
        panelCadastro.add(CPFLabel);

        JTextField CPFTextField = new JTextField(20);
        CPFTextField.setBounds(170, 110, 150, 25);
        panelCadastro.add(CPFTextField);

        JLabel endereçoLabel = new JLabel("Endereço:");
        endereçoLabel.setBounds(10, 140, 100, 25);
        panelCadastro.add(endereçoLabel);

        JTextField endereçoTextField = new JTextField(20);
        endereçoTextField.setBounds(120, 140, 165, 25);
        panelCadastro.add(endereçoTextField);
        
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 170, 100, 25);
        panelCadastro.add(emailLabel);

        JTextField emailTextField = new JTextField(20);
        emailTextField.setBounds(120, 170, 165, 25);
        panelCadastro.add(emailTextField);

        JLabel TelefoneLabel = new JLabel("Telefone:");
        TelefoneLabel.setBounds(10, 200, 100, 25);
        panelCadastro.add(TelefoneLabel);

        JTextField TelefoneTextField = new JTextField(20);
        TelefoneTextField.setBounds(120, 200, 165, 25);
        panelCadastro.add(TelefoneTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(10, 230, 100, 25);
        panelCadastro.add(cadastrarButton);


        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String novoUsuario = novoUsuarioTextField.getText();
                String novaSenha = new String(novaSenhaField.getPassword());
                String dataNascimento = dataNascimentoTextField.getText();
                int CPF = Integer.parseInt(CPFTextField.getText());
                String endereço = endereçoTextField.getText();
                String email = emailTextField.getText();
                int Telefone = Integer.parseInt(TelefoneTextField.getText());

                
                cadastrarNovoUsuario(novoUsuario, novaSenha, dataNascimento, CPF, endereço, email, Telefone);
            }
        });
    }

    private static void cadastrarNovoUsuario(String usuario, String senha, String dataNascimento, int CPF, String endereço, String email, int Telefone ) {
        try {
            // Obter o último _id
            int ultimoId = obterUltimoId();

            // Incrementar o último _id
            int novoId = ultimoId + 1;
            String query = "INSERT INTO usuarios (_id, nome, senha, data_nascimento, cpf, endereco, email, telefone, qtd_emprestismos, permissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'comum')";
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.setInt(1, novoId);
            preparedStatement.setString(2, usuario);
            preparedStatement.setString(3, senha);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dataNascimento));
            preparedStatement.setInt(5, CPF);
            preparedStatement.setString(6, endereço);
            preparedStatement.setString(7, email);
            preparedStatement.setInt(8, Telefone);
            preparedStatement.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erro ao cadastrar usuário.");
        }
    
    }
    private static int obterUltimoId() throws SQLException {
        String query = "SELECT MAX(_id) AS ultimoId FROM usuarios";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("ultimoId");
            }
        }

        // Se não houver nenhum usuário ainda, retorna 0
        return 0;
    }
    private static void abrirTelaCadastroLivro() {
        JFrame frameCadastroLivro = new JFrame("Cadastro de Livro");
        frameCadastroLivro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameCadastroLivro.setSize(400, 300);
    
        JPanel panelCadastroLivro = new JPanel();
        frameCadastroLivro.add(panelCadastroLivro);
        lugarComponentesCadastroLivro(panelCadastroLivro);
    
        frameCadastroLivro.setVisible(true);
    }
    private static void lugarComponentesCadastroLivro(JPanel panelCadastroLivro) {
        // Coloque os JLabels, JTextFields e JButtons necessários para o cadastro de livros
        // Exemplo:
        JLabel tituloLabel = new JLabel("Título:");
        tituloLabel.setBounds(10, 20, 100, 25);
        panelCadastroLivro.add(tituloLabel);
    
        JTextField tituloTextField = new JTextField(20);
        tituloTextField.setBounds(120, 20, 250, 25);
        panelCadastroLivro.add(tituloTextField);

        JLabel autorLabel = new JLabel("Autor:");
        autorLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(autorLabel);
    
        JTextField autorTextField = new JTextField(20);
        autorTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(autorTextField);

        JLabel editoraLabel = new JLabel("Editora:");
        editoraLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(editoraLabel);
    
        JTextField EditoraTextField = new JTextField(20);
        EditoraTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(EditoraTextField);

        JLabel anoLançamentoLabel = new JLabel("Ano de Lançamento:");
        anoLançamentoLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(anoLançamentoLabel);
    
        JTextField anoLançamentoTextField = new JTextField(20);
        anoLançamentoTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(anoLançamentoTextField);

        JLabel generoLabel = new JLabel("Genero:");
        generoLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(generoLabel);
    
        JTextField GeneroTextField = new JTextField(20);
        GeneroTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(GeneroTextField);

        JLabel QtdPaginasLabel = new JLabel("Quantidade de paginas:");
        QtdPaginasLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(QtdPaginasLabel);
    
        JTextField QtdPaginasTextField = new JTextField(20);
        QtdPaginasTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(QtdPaginasTextField);

        JLabel QtdEdiçãoLabel = new JLabel("Edição:");
        QtdEdiçãoLabel.setBounds(10, 50, 100, 25);
        panelCadastroLivro.add(QtdEdiçãoLabel);
    
        JTextField QtdEdiçãoTextField = new JTextField(20);
        QtdEdiçãoTextField.setBounds(120, 50, 250, 25);
        panelCadastroLivro.add(QtdEdiçãoTextField);

        

        JCheckBox statusReservaCheckBox = new JCheckBox("Status de Reserva");
        statusReservaCheckBox.setBounds(10, 200, 150, 25);
        panelCadastroLivro.add(statusReservaCheckBox);
    
        // Adicione outros campos conforme necessário
    
        JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
        cadastrarLivroButton.setBounds(10, 230, 150, 25);
        panelCadastroLivro.add(cadastrarLivroButton);
    
        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloTextField.getText();
                String autor = autorTextField.getText();
                String editora = EditoraTextField.getText();
                String anoLançamento = anoLançamentoTextField.getText();
                String Genero = GeneroTextField.getText();
                int QtdPaginas = Integer.parseInt(QtdPaginasTextField.getText());
                int QtdEdição = Integer.parseInt(QtdEdiçãoTextField.getText());
                boolean Status = statusReservaCheckBox.isSelected();
                

                
                cadastrarNovoLivro (titulo, autor, editora, anoLançamento, Genero, QtdPaginas, QtdEdição, Status );
            }

            private void cadastrarNovoLivro(String titulo, String autor, String Editora, String anoLançamento, String Genero, int QtdPaginas, int QtdEdição, boolean Status ) {
                try {
                    String query = "INSERT INTO livros (titulo, autor, editora, ano_lancamento, genero, qtd_paginas, qtd_edicao, status_reserva) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conexao.prepareStatement(query);
                    preparedStatement.setString(1, titulo);
                    preparedStatement.setString(2, autor);
                    preparedStatement.setString(3, Editora);
                    preparedStatement.setDate(4, java.sql.Date.valueOf(anoLançamento));
                    preparedStatement.setString(5, Genero);
                    preparedStatement.setInt(6, QtdPaginas);
                    preparedStatement.setInt(7, QtdEdição);
                    preparedStatement.setBoolean(8, Status);


                    // Configure os outros parâmetros
                    preparedStatement.executeUpdate();
            
                    JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
            
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Erro ao cadastrar livro.");
                }
            }
            
        });
    }
        
}




