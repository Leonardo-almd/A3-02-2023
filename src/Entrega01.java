import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Entrega01 {

    private static JFrame frame;
    private static JTextField usuarioTextField;
    private static JPasswordField senhaField;

    public static void main(String[] args) {
        frame = new JFrame("Sistema de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);
        lugarComponentes(panel);

        frame.setVisible(true);
    }

    private static void lugarComponentes(JPanel panel) {
        panel.setLayout(null);

        JLabel usuarioLabel = new JLabel("USÚARIO:");
        usuarioLabel.setBounds(10, 20, 80, 25);
        panel.add(usuarioLabel);

        usuarioTextField = new JTextField(20);
        usuarioTextField.setBounds(100, 20, 165, 25);
        panel.add(usuarioTextField);

        JLabel senhaLabel = new JLabel("SENHA:");
        senhaLabel.setBounds(10, 50, 80, 25);
        panel.add(senhaLabel);

        senhaField = new JPasswordField(20);
        senhaField.setBounds(100, 50, 165, 25);
        panel.add(senhaField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fazerLogin();
            }
        });
    }

    private static void fazerLogin() {
        String usuario = usuarioTextField.getText();
        String senha = new String(senhaField.getPassword());

        if (usuario.equals("admin") && senha.equals("admin")) {
            abrirTelaAdmin();
        } else if (usuario.equals("comum") && senha.equals("comum")) {
            abrirTelaComum();
        } else {
            JOptionPane.showMessageDialog(frame, "Usuário ou senha inválidos. Tente novamente.");
        }
    }

    private static void abrirTelaAdmin() {
      
        JOptionPane.showMessageDialog(frame, "Bem-vindo, administrador! Faça o cadastro dos clientes:");
       
    }

    private static void abrirTelaComum() {
        
        JOptionPane.showMessageDialog(frame, "Bem-vindo, usuário comum! Faça o cadastro dos clientes:");
        
    }
}
