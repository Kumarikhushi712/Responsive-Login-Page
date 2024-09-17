import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main{

    private JFrame loginPage;
    JPanel loginPanel;
    JButton loginButton, signinButton;
    JTextField usernameField, passwordField;
    JLabel l1,l2,l3;

    public Main(){
        createLoginPage();
        try {
            File file = new File("C:\\Users\\khush\\Documents\\information.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        }
        catch(Exception e){
            System.out.println("Error in creating file");
        }
    }

    public void createLoginPage(){
        loginPage = new JFrame("Login Page");
        loginPage.setSize(800,700);
        loginPage.setLocationRelativeTo(null);
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginPanel = new JPanel(new GridLayout(10,2));
        loginPanel.setBackground(Color.pink);
        JLabel welcomeLabel = new JLabel("Welcome to Login Page",SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans",Font.BOLD,24));
        loginPanel.add(welcomeLabel);

        l1 = new JLabel("Username ");
        loginPanel.add(l1);
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        l2 = new JLabel("Password ");
        loginPanel.add(l2);
        passwordField = new JTextField();
        loginPanel.add(passwordField);

        l3 = new JLabel("");
        l3.setFont(new Font("Comic Sans",Font.BOLD,24));
        loginPanel.add(l3);

        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = login();
                usernameField.setText("");
                passwordField.setText("");

                if (check) {
                    l3.setText("Logged in Successfully ");
                } else {
                    l3.setText("Invalid Username or Password!");
                }
                try {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            l3.setText("");
                        }
                    }, 3000);
                }
                catch(Exception w){
                    System.out.println("Error!");
                }
            }

        });

        signinButton = new JButton("Sign up");
        loginPanel.add(signinButton);
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = signin();
                usernameField.setText("");
                passwordField.setText("");

                if (check) {
                    l3.setText("Successfully Signed Up");
                } else {
                    l3.setText("Account already exists!");
                }
                try {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            l3.setText("");
                        }
                    }, 3000);
                }
                catch(Exception w){
                    System.out.println("Error!");
                }
            }

        });

        loginPage.add(loginPanel);
        loginPage.setVisible(true);
    }

    public boolean login(){
        String user = usernameField.getText();
        String password = passwordField.getText();

        try{
            Path path = Paths.get("C:\\Users\\Khush\\Documents\\information.txt");
            List<String> lines = Files.readAllLines(path);
            boolean check = lines.contains(user+"  "+password);


            return check;

        }
        catch(Exception e){
            System.out.println("Error in line 90!");
            return false;
        }
    }

    public boolean signin(){
        String user = usernameField.getText();
        String password = passwordField.getText();

        try{
            Path path = Paths.get("C:\\Users\\Khush\\Documents\\information.txt");
            List<String> lines = Files.readAllLines(path);
            boolean check = lines.contains(user+"  "+password);

            if(check){
                return false;
            }
            else{
                FileWriter fileWriter = null;
                try{
                    fileWriter = new FileWriter("C:\\Users\\Khush\\Documents\\information.txt",true);
                    fileWriter.write(user+"  "+password);
                    fileWriter.write(System.lineSeparator());
                }
                catch(Exception s){
                    System.out.println("Error signing in");
                }
                finally{
                    if (fileWriter != null){
                        try{
                            fileWriter.close();
                        }
                        catch(Exception e){
                            System.out.println(e);
                        }
                    }
                }
                return true;
            }


        }
        catch(Exception e){
            System.out.println("Error in line 90!");
            return false;
        }
    }

    public static void main(String[] Args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main m1 = new Main();
            }
        });
    }
}