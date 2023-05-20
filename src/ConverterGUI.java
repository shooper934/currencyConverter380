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
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Cursor;

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
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(400, 200, 450, 300);
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
		titleName.setBounds(115, 10, 202, 25);
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
		resultTextField.setEditable(false);
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
		
	}
}
