import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Entrega01 {

    private static Connection conexao;

    public static void main(String[] args) {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/meubanco", "root", "*********");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        JFrame frame = new JFrame("Sistema de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private static void lugarComponentes(JPanel panel) {
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
            Statement statement = conexao.createStatement();
            String query = "SELECT * FROM usuarios WHERE username = '" + usuario + "' AND password = '" + senha + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String tipoUsuario = resultSet.getString("username");
                abrirNovaTela(tipoUsuario);
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos. Tente novamente.");
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void abrirNovaTela(String tipoUsuario) {
        JFrame novaTela = new JFrame("Nova Tela");
        novaTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        novaTela.setSize(300, 150);

        JPanel panel = new JPanel();
        novaTela.add(panel);

        JLabel label = new JLabel("Bem-vindo, " + tipoUsuario + "!");
        label.setBounds(10, 20, 200, 25);
        panel.add(label);

        novaTela.setVisible(true);
    }
}
