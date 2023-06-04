import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;

public class TableGUI extends APIconnection {

    JFrame frame = new JFrame();
    private JPanel jPanel;


    TableGUI(DBConnection db, String baseCurr, String targetCurr, int amount) {

        try {
            Statement statement = db.conn.createStatement();
            statement.executeUpdate("DELETE FROM `conversion_history`.`historical_data`");
            statement.executeUpdate("ALTER TABLE `conversion_history`.`historical_data` AUTO_INCREMENT = 1");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        JLabel lblCurrHist = new JLabel("Currency History");
        lblCurrHist.setBounds(180,0, 200,50);
        lblCurrHist.setFont(new Font("Times New Roman", Font.BOLD, 19));
        frame.add(lblCurrHist);

        String[] dates = new String[]{"2023-06-01","2023-05-01","2023-04-01","2023-03-01","2023-02-01","2023-01-01",
                                        "2022-12-01","2022-11-01","2022-10-01","2022-09-01","2022-08-01","2022-07-01",
                                        "2022-06-01","2022-05-01","2022-04-01","2022-03-01","2022-02-01","2022-01-01",
                                        "2021-12-01","2021-11-01","2021-10-01","2021-09-01","2021-08-01","2021-07-01"};

        for (int i = 0; i < dates.length; i++) {
            String insert = "INSERT INTO `conversion_history`.`historical_data` (`conAmount`, `date`) ";
            String values = "VALUES (" +
                            "'" + historical(baseCurr, targetCurr, amount, dates[i]) + "', " +
                            "'" + dates[i] + "');";
            String query = insert + values;

            try {
                Statement statement = db.conn.createStatement();
                statement.executeUpdate(query);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        String[] columnNames = {"Conversion", "Date"};
        String[][] data = new String[24][2];
        String query = "SELECT * FROM conversion_history.historical_data WHERE conAmount;";
        try {
            Statement statement = db.conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            int i = 0;
            while (result.next()) {
                data[i][0] = result.getString(2);
                data[i][1] = result.getString(3);
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        JTable historyTable = new JTable(data, columnNames);
        historyTable.setBounds(20,75,450,450);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        historyTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(300);

        JScrollPane sp = new JScrollPane(historyTable);
        sp.setBounds(20,75,450,450);
        frame.add(sp);


        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,550);
        frame.setLayout(null);
        frame.setVisible(true);
    }

}