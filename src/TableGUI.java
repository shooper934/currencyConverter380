import javax.swing.*;
import java.awt.*;

public class TableGUI {

    JFrame frame = new JFrame();
    private JPanel jPanel;


    TableGUI(String[][] data) {

        JLabel lblCurrHist = new JLabel("Currency History");
        lblCurrHist.setBounds(180,0, 200,50);
        lblCurrHist.setFont(new Font("Times New Roman", Font.BOLD, 19));
        frame.add(lblCurrHist);

        // HARDCODED Column labels. US and EURO should dynamically change with the base currency and target currency
        String[] columnNames = {"Time", "EURO"};
        JTable historyTable = new JTable(data, columnNames);
        historyTable.setBounds(20,75,450,450);

        JScrollPane sp = new JScrollPane(historyTable);
        sp.setBounds(20,75,450,450);
        frame.add(sp);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}
