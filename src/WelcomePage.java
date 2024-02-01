import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame {

    public WelcomePage(String username){
        //Welcome label
        JLabel welcomeLabel = new JLabel("WELCOME "+username);
        welcomeLabel.setBounds(10,50,500,100);
        welcomeLabel.setFont(new Font(null,Font.BOLD,30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setForeground(Color.green);

        //add components
        add(welcomeLabel);

        //frame settings
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500,600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
