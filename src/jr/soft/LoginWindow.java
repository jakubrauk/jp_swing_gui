package jr.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;

public class LoginWindow extends JFrame {
    Color defaultColor = UIManager.getColor("Panel.background");
    JTextField usernameField;
    JPasswordField passwordField;
    HashMap<String, String> credentials = new HashMap<String, String>();

    void createCredentials() {
        credentials.put("username", "jakub");
        credentials.put("password", "rauk");
    }

    public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow window = new LoginWindow();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        });
    }

    public LoginWindow() throws HeadlessException {
        this("Swing login window");
    }

    public LoginWindow(String title) throws HeadlessException {
        super(title);
        createCredentials();
        buildFrame();
    }

    protected void buildFrame() {
        setBounds(100, 100, 450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Content Pane

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Menu initialization

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mnitLogin = new JMenuItem("Login");
        mnitLogin.addActionListener(e -> {
            login();
        });
        mnitLogin.setMnemonic(KeyEvent.VK_L);
        mnFile.add(mnitLogin);

        JMenuItem mnitClear = new JMenuItem("Clear");
        mnitClear.addActionListener(e -> {
            clear();
        });
        mnitClear.setMnemonic(KeyEvent.VK_C);
        mnFile.add(mnitClear);

        // Buttons initialization

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            login();
        });
        loginButton.setBounds(100, 150, 100, 25);
        contentPane.add(loginButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            clear();
        });
        clearButton.setBounds(250, 150, 100, 25);
        contentPane.add(clearButton);

        // username text input
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 35, 250, 10);
        contentPane.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 50, 250, 25);
        contentPane.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(100, 75, 250, 25);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 95, 250, 25);
        contentPane.add(passwordField);

    }

    void login() {
        if (usernameField.getText().equals(credentials.get("username"))) {
            if (Arrays.equals(credentials.get("password").toCharArray(), passwordField.getPassword())) {
                loginSuccessful();
                return;
            }
        }
        loginError();
    }

    void loginSuccessful() {
        setWindowColor(new Color(195, 230, 203));
    }

    void loginError() {
        setWindowColor(new Color(245, 198, 203));
    }

    void clear() {
        usernameField.setText("");
        passwordField.setText("");
        setWindowColor(defaultColor);
    }

    void setWindowColor(Color color) {
        this.getContentPane().setBackground(color);
    }
}
