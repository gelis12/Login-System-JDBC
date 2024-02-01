import DB.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateNewUserPage extends JFrame implements ActionListener {
    protected JButton registerButton;
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JPasswordField rePasswordField;
    protected JLabel alreadyUser;
    public CreateNewUserPage(){
        //Login Label
        JLabel register = new JLabel("Register Now");
        register.setBounds(130,50,260,45);
        register.setFont(new Font(null,Font.BOLD,40));
        register.setForeground(Color.green);

        //Username Label
        JLabel username = new JLabel("Username:");
        username.setBounds(75,150,100,25);
        username.setFont(new Font(null,Font.PLAIN,16));
        username.setForeground(Color.green);

        //Password Label
        JLabel password = new JLabel("Password:");
        password.setBounds(75,220,100,25);
        password.setFont(new Font(null,Font.PLAIN,16));
        password.setForeground(Color.green);

        //Retype Password Label
        JLabel rePassword = new JLabel("Retype Password:");
        rePassword.setBounds(45,290,150,25);
        rePassword.setFont(new Font(null,Font.PLAIN,16));
        rePassword.setForeground(Color.green);

        //Register now Label
        alreadyUser = new JLabel("Already an user? / Login here!");
        alreadyUser.setHorizontalAlignment(SwingConstants.CENTER);
        alreadyUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        alreadyUser.setBounds(130,450,200,25);
        //functionality for switching gui
        alreadyUser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //dispose this gui
                CreateNewUserPage.this.dispose();
                //Launch Login page
                new LoginPage().setVisible(true);
            }
        });

        //Username Field
        usernameField = new JTextField();
        usernameField.setBounds(220,150,170,25);
        usernameField.setFont(new Font(null,Font.PLAIN,18));


        //Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(220,220,170,25);
        passwordField.setFont(new Font(null,Font.PLAIN,18));

        //Retype Password Field
        rePasswordField = new JPasswordField();
        rePasswordField.setBounds(220,290,170,25);
        rePasswordField.setFont(new Font(null,Font.PLAIN,18));

        //Register Button
        registerButton=new JButton("Register Now");
        registerButton.setFocusable(false);
        registerButton.setBounds(130,360,200,25);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String rePass = new String(rePasswordField.getPassword());

                //Validate user input
                if (validateUserInput(username,password,rePass)){
                    //register user to db
                    if(DatabaseConnection.register(username,password)){
                        //dispose this gui
                        CreateNewUserPage.this.dispose();

                        //take the user to login page
                        LoginPage loginPage = new LoginPage();
                        loginPage.setVisible(true);

                        //result dialog
                        JOptionPane.showMessageDialog(loginPage,"Registered Account Successfully!");
                    }else {
                        //register failed
                        JOptionPane.showMessageDialog(CreateNewUserPage.this,
                                "Username already taken!");
                    }
                }else {
                    JOptionPane.showMessageDialog(CreateNewUserPage.this,
                            "Username must be at least 6 characters \n"+"and/or password must match!");

                }
            }
        });

        //Add components
        add(alreadyUser);
        add(registerButton);
        add(passwordField);
        add(usernameField);
        add(rePassword);
        add(rePasswordField);
        add(password);
        add(username);
        add(register);

        //Frame settings
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private boolean validateUserInput(String username,String password, String rePass){
        if(username.length()==0 || password.length()==0 || rePass.length()==0){
            return false;
        }

        if(username.length()<6)return false;

        if(!password.equals(rePass))return false;

        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
