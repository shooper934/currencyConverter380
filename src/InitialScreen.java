import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;


public class InitialScreen {
	
	//username and password for the dbconnection
	private static String username;
	private static String password;
	
    private JFrame frame;
    private JTextField usernameField;
    private JTextField passwordField;
    //ConverterGUI c = new ConverterGUI()

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InitialScreen window = new InitialScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public InitialScreen() {
        initialize();
    }

    private void initialize() {
    	
        frame = new JFrame();
        frame.setAlwaysOnTop(true);
        frame.setBounds(400, 200, 300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBackground(new Color(234, 255, 255));
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton btnOpenConverter = new JButton("Enter");
        btnOpenConverter.setBackground(new Color(255, 255, 255));
        btnOpenConverter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        btnOpenConverter.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnOpenConverter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	try {	
            		username = usernameField.getText();
            		password = passwordField.getText();
            	
            		frame.dispose(); // Close the initial screen
            		openConverterGUI(); // Open the ConverterGUI
            	}
            	catch(Exception e1){
            		System.out.println("Incorrect username or password, MySQL not connected.");
            	}
            }
        });
        btnOpenConverter.setBounds(72, 128, 140, 25);
        panel.add(btnOpenConverter);
        
        JLabel lblHeader = new JLabel("SQL Connection");
        lblHeader.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(10, 10, 266, 23);
        panel.add(lblHeader);
        
        usernameField = new JTextField();
        usernameField.setBorder(new LineBorder(new Color(171, 173, 179)));
        usernameField.setBackground(new Color(255, 255, 255));
        usernameField.setText("root");
        usernameField.setBounds(94, 43, 106, 23);
        panel.add(usernameField);
        usernameField.setColumns(10);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(25, 46, 62, 13);
        panel.add(lblUsername);
        
        passwordField = new JTextField();
        passwordField.setBorder(new LineBorder(new Color(171, 173, 179)));
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setColumns(10);
        passwordField.setBounds(94, 80, 106, 23);
        panel.add(passwordField);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(25, 83, 62, 13);
        panel.add(lblPassword);
    }

    private void openConverterGUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConverterGUI window = new ConverterGUI();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    //getters
    public static String getUsername() {
    	return username;
    }
    
    public static String getPassword() {
    	return password;
    }
}
