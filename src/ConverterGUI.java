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
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ConverterGUI {

	private JFrame frame;
	private JTextField resultTextField;
	//instance of APIConnection
	APIconnection a = new APIconnection();
	private JTextField amountTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConverterGUI window = new ConverterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConverterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
		
		JComboBox baseCurr = new JComboBox();
		baseCurr.setBounds(28, 89, 96, 21);
		frame.getContentPane().add(baseCurr);
		
		
		baseCurr.addItem("USD");
		baseCurr.addItem("EUR");
		baseCurr.addItem("GPB");
		baseCurr.addItem("JPY");
		baseCurr.addItem("CAD");
		baseCurr.addItem("AUD");
		baseCurr.addItem("CHF");
		baseCurr.addItem("CNY");
		baseCurr.addItem("INR");
		baseCurr.addItem("MXN");
		
		
		JLabel titleName = new JLabel("Currency Converter");
		titleName.setFont(new Font("Times New Roman", Font.BOLD, 19));
		titleName.setHorizontalAlignment(SwingConstants.CENTER);
		titleName.setBounds(10, 10, 405, 25);
		frame.getContentPane().add(titleName);
		
		JComboBox targetCurr = new JComboBox();
		targetCurr.setBounds(153, 89, 96, 21);
		frame.getContentPane().add(targetCurr);
		
		
		targetCurr.addItem("USD");
		targetCurr.addItem("EUR");
		targetCurr.addItem("GPB");
		targetCurr.addItem("JPY");
		targetCurr.addItem("CAD");
		targetCurr.addItem("AUD");
		targetCurr.addItem("CHF");
		targetCurr.addItem("CNY");
		targetCurr.addItem("INR");
		targetCurr.addItem("MXN");
		
		
		resultTextField = new JTextField();
		resultTextField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		
		JButton btnConvert = new JButton("Convert");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int intAmount = Integer.parseInt(amountTextField.getText());
					resultTextField.setText(a.convert(baseCurr.getSelectedItem().toString(), targetCurr.getSelectedItem().toString(), intAmount));

				} catch (NumberFormatException e11) {
					resultTextField.setText("Please enter a valid number into the Amount box");
				}								
			}
		});
		btnConvert.setBounds(164, 235, 85, 21);
		frame.getContentPane().add(btnConvert);
		
		amountTextField = new JTextField();
		amountTextField.setBorder(new LineBorder(new Color(171, 173, 179)));
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
		
	}
}
