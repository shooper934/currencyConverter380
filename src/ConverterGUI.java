import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Cursor;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.JScrollPane;


public class ConverterGUI extends APIconnection{
	
	LocalDate currentDate = LocalDate.now();
	private JFrame frame;
	private JTextField resultTextField;
	private JTextField amountTextField;
	
	//golabal boolean to prevent errors
	public static boolean saveValid = false;
	
	//instance of DBConnection
	private static DBConnection db = new DBConnection();
	//method to get the frame for other classes
	public JFrame getFrame() {
        return frame;
    }

	/**
	 * Create the application.
	 */
	public ConverterGUI() {
		
		db.connect();
		
		
			
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(400, 200, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
		
		JComboBox baseCurr = new JComboBox();
		baseCurr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		baseCurr.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		baseCurr.setBackground(new Color(255, 255, 255));
		baseCurr.setBounds(28, 89, 96, 25);
		frame.getContentPane().add(baseCurr);
		
		
		baseCurr.addItem("USD");
		baseCurr.addItem("EUR");
		baseCurr.addItem("GBP");
		baseCurr.addItem("JPY");
		baseCurr.addItem("CAD");
		baseCurr.addItem("AUD");
		baseCurr.addItem("CHF");
		baseCurr.addItem("CNY");
		baseCurr.addItem("INR");
		baseCurr.addItem("MXN");
		
		
		JLabel titleName = new JLabel("Currency Converter");
		titleName.setBorder(null);
		titleName.setFont(new Font("Times New Roman", Font.BOLD, 19));
		titleName.setHorizontalAlignment(SwingConstants.CENTER);
		titleName.setBounds(115, 22, 202, 25);
		frame.getContentPane().add(titleName);
		
		JComboBox targetCurr = new JComboBox();
		targetCurr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		targetCurr.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		targetCurr.setBounds(153, 89, 96, 25);
		frame.getContentPane().add(targetCurr);
		
		
		targetCurr.addItem("USD");
		targetCurr.addItem("EUR");
		targetCurr.addItem("GBP");
		targetCurr.addItem("JPY");
		targetCurr.addItem("CAD");
		targetCurr.addItem("AUD");
		targetCurr.addItem("CHF");
		targetCurr.addItem("CNY");
		targetCurr.addItem("INR");
		targetCurr.addItem("MXN");
		
		
		resultTextField = new JTextField();
		resultTextField.setHorizontalAlignment(SwingConstants.CENTER);
		resultTextField.setEditable(false);
		resultTextField.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resultTextField.setBounds(83, 166, 254, 59);
		frame.getContentPane().add(resultTextField);
		resultTextField.setColumns(10);
		
		JLabel lblBase = new JLabel("Base ");
		lblBase.setHorizontalAlignment(SwingConstants.CENTER);
		lblBase.setBounds(56, 75, 45, 13);
		frame.getContentPane().add(lblBase);
		
		JLabel lblTarget = new JLabel("Target");
		lblTarget.setHorizontalAlignment(SwingConstants.CENTER);
		lblTarget.setBounds(177, 75, 45, 13);
		frame.getContentPane().add(lblTarget);
		
		//save button
		JButton btnSave = new JButton("Save");
		btnSave.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(saveValid)
					btnSave.setEnabled(true);
				
				try {
					int intAmount = Integer.parseInt(amountTextField.getText());
					resultTextField.setText(convert(baseCurr.getSelectedItem().toString(), targetCurr.getSelectedItem().toString(), intAmount));

				} catch (NumberFormatException e11) {
					resultTextField.setText("Please enter a valid number into the Amount box");
				}
				
				
			}
		});
		btnConvert.setBounds(164, 235, 85, 21);
		frame.getContentPane().add(btnConvert);
		
		amountTextField = new JTextField();
		amountTextField.setBackground(new Color(255, 255, 255));
		amountTextField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		amountTextField.setBounds(286, 90, 96, 19);
		frame.getContentPane().add(amountTextField);
		amountTextField.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(309, 75, 45, 13);
		frame.getContentPane().add(lblAmount);
		
		JLabel lblNewLabel = new JLabel(" Real-time conversion");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(83, 145, 254, 13);
		frame.getContentPane().add(lblNewLabel);


		JButton btnTable = new JButton("Compare");
		btnTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==btnTable) {
					
					try {
						TableGUI tableGUI = new TableGUI(db, baseCurr.getSelectedItem().toString(), targetCurr.getSelectedItem().toString(), Integer.parseInt(amountTextField.getText()));
					}
					catch(Exception q) {
						System.out.println("Error: launching table");
						String string = "Select currencies and enter an amount!";
						resultTextField.setText(string);
					}
					
				}
			}
		});
		btnTable.setBounds(96,270,96, 21);
		frame.getContentPane().add(btnTable);
		
		
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				//this will add data into db
				if (e.getSource() == btnSave) {
		            String insert = "INSERT INTO `conversion_history`.`conversion_history` (`baseCurr`, `targetCurr`, `conAmount`, `date`) ";
		            String values = "VALUES (" +
		                            "'" + baseCurr.getSelectedItem().toString() + "', " +
		                            "'" + targetCurr.getSelectedItem().toString() + "', " +
		                            "'" + resultTextField.getText() + "', " +
		                            "'" + currentDate.toString() + "');";
		            String query = insert + values;

		            try {
		                Statement statement = db.conn.createStatement();
		                statement.executeUpdate(query);
		                System.out.println("conv added");
		            } catch (SQLException e111) {
		                System.out.println("MySQL not connected.");
		                resultTextField.setText("No MySQL connection");
		            }
				}
			  }
		});
		btnSave.setBounds(347, 185, 71, 21);
		frame.getContentPane().add(btnSave);
		
		JButton btnHistory = new JButton("History");
		btnHistory.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JTextArea popUp = new JTextArea(); // Create a JTextArea with desired text
				 //popUp.setPreferredSize(new Dimension(400, 300));
	                popUp.setEditable(false); // Make the text area read-only
	                
	                //scroll pane for growing data
	                JScrollPane scrollPane = new JScrollPane(popUp);
	                scrollPane.setPreferredSize(new Dimension(400, 200));
	                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	                
	                String query = "select * from conversion_history";

	                try {
	                    Statement statement = db.conn.createStatement();
	                    ResultSet result = statement.executeQuery(query);
	                    

	                    while (result.next()) {
	                        popUp.setText(popUp.getText() + "\n" + result.getString("baseCurr") + " " + result.getString("targetCurr") + " " +
	                                result.getString("conAmount") + " " +
	                                "(" + result.getString("date") + ")" + "\n");
	                    }
	                } catch (SQLException throwables) {
	                    throwables.printStackTrace();
	                }

	                JOptionPane.showMessageDialog(frame, scrollPane, "History", JOptionPane.INFORMATION_MESSAGE);
	                // Show the JTextArea in a dialog box with a specified title and information message type
			}
		});
		btnHistory.setBounds(221, 270, 96, 21);
		frame.getContentPane().add(btnHistory);
		
	}
}