import DB.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage extends JFrame implements ActionListener {
    protected JButton loginButton;
    protected JButton resetButton;
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JLabel registerNow;
    public LoginPage(){
        //Login Label
        JLabel login = new JLabel("Login System");
        login.setBounds(130,50,260,45);
        login.setFont(new Font(null,Font.BOLD,40));
        login.setForeground(Color.green);

        //Username Label
        JLabel username = new JLabel("Username:");
        username.setBounds(75,150,100,25);
        username.setFont(new Font(null,Font.PLAIN,18));
        username.setForeground(Color.green);

        //Register now Label
        registerNow = new JLabel("Not a user? / Register here!");
        registerNow.setHorizontalAlignment(SwingConstants.CENTER);
        registerNow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerNow.setBounds(150,400,180,25);
        //functionality for switching gui
        registerNow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose this gui
                LoginPage.this.dispose();
                //Launch Create a new user page
                new CreateNewUserPage().setVisible(true);
            }
        });

        //Password Label
        JLabel password = new JLabel("Password:");
        password.setBounds(75,220,100,25);
        password.setFont(new Font(null,Font.PLAIN,18));
        password.setForeground(Color.green);

        //Username Field
        usernameField = new JTextField();
        usernameField.setBounds(170,150,170,25);
        usernameField.setFont(new Font(null,Font.PLAIN,18));


        //Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(170,220,170,25);
        passwordField.setFont(new Font(null,Font.PLAIN,18));

        //Login Button
        loginButton=new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBounds(130,300,100,25);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(this);

        //Reset Button
        resetButton=new JButton("Reset");
        resetButton.setFocusable(false);
        resetButton.setBounds(240,300,100,25);
        resetButton.addActionListener(this);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


        //Add components
        add(registerNow);
        add(resetButton);
        add(loginButton);
        add(passwordField);
        add(usernameField);
        add(password);
        add(username);
        add(login);

        //Frame settings
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==resetButton){
            usernameField.setText("");
            passwordField.setText("");
            usernameField.requestFocus();
        }

        if(e.getSource()==loginButton){
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            //Check in database if the username and password combo is valid
            if (DatabaseConnection.validateUserPass(username,password)){
                //Login successful
                WelcomePage welcomePage = new WelcomePage(usernameField.getText());
                LoginPage.this.dispose();
            }else {
                //Login failed
                JOptionPane.showMessageDialog(LoginPage.this,"Wrong combination!");
            }
        }

    }
}
