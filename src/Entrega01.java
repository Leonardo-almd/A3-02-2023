import javax.swing.*;
import java.awt.*;

public class Entrega01 extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Entrega01() {
        setTitle("Login Biblioteca Digital");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JPanel panel = new JPanel(new GridLayout(3, 2));
        add(panel);

        JLabel usernameLabel = new JLabel("Usuário:");
        JLabel passwordLabel = new JLabel("Senha:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(loginButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Entrega01().setVisible(true);
            }
        });
    }
}
