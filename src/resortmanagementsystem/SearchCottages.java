package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils; // Ensure rs2xml.jar is included in your classpath

public class SearchCottages extends JFrame implements ActionListener {

    JTable table;
    JButton back, submit;
    JComboBox<String> cottageType;
    JCheckBox available;

    public SearchCottages() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/searchCottagebg.png"));
        JLabel header = new JLabel(icon);
        header.setBounds(-20, -10, 1050, 80);
        add(header);;

        JLabel lblbed = new JLabel("Cottage Type");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);

        cottageType = new JComboBox<>(new String[]{"Standard", "Deluxe", "VIP"});
        cottageType.setBounds(150, 100, 150, 25);
        cottageType.setBackground(Color.white);
        add(cottageType);

        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 200, 25);
        available.setBackground(Color.white);
        add(available);
        
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 200, 1000, 300);
        add(scrollPane);

        loadTableData("select * from cottage");

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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String cottageTypeSelection = cottageType.getSelectedItem().toString();
                String query;
                if (available.isSelected()) {
                    query = "SELECT * FROM cottage WHERE availability = 'Available' AND cottage_type = '" + cottageTypeSelection + "'";
                } else {
                    query = "SELECT * FROM cottage WHERE cottage_type = '" + cottageTypeSelection + "'";
                }
                loadTableData(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception(); // Ensure Reception class exists
        }
    }

    private void loadTableData(String query) {
        try {
            Conn conn = new Conn(); // Ensure Conn is properly implemented
            ResultSet rs = conn.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchCottages();
    }
}
