
package resortmanagementsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Danah Paris
 */
public class SearchRoom extends JFrame implements ActionListener {

    JTable table;
    JButton back, submit;
    JComboBox<String> bedType;
    JCheckBox available;

    SearchRoom() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/searchRoombg.png"));
        JLabel header = new JLabel(icon);
        header.setBounds(-20, -10, 1050, 80);
        add(header);

        JLabel lblbed = new JLabel("Room Type");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);

        bedType = new JComboBox<>(new String[]{"Standard", "VIP", "VVIP"});
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.white);
        add(bedType);

        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.white);
        add(available);

        // Create JTable with a custom model
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 130, 1000, 150);
        add(scrollPane);

        // Load initial data
        loadData("select * from room"); 

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        submit.setBounds(300, 510, 120, 30);
        add(submit);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setBounds(500, 510, 120, 30);
        add(back);

        setBounds(300, 200, 1000, 600);
        setVisible(true);
    }

    // Method to load data and set custom headers
    private void loadData(String query) {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();

            model.addColumn("Room Number");
            model.addColumn("Availability");
            model.addColumn("Price");
            model.addColumn("Room Type");
            model.addColumn("Facilities");

            // Populate rows
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("roomnumber"),
                        rs.getString("availability"),
                        rs.getString("price"),
                        rs.getString("roomType"),
                        rs.getString("facilities")
                });
            }

            // Set the model to the table
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String roomType = (String) bedType.getSelectedItem();
            boolean isAvailable = available.isSelected();
            String query = isAvailable
                    ? "select * from room where availability = 'Available' AND roomType = '" + roomType + "'"
                    : "select * from room where roomType = '" + roomType + "'";
            loadData(query);
        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
