package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*; // rs2xml.jar

public class GuestInfo extends JFrame implements ActionListener {
    
    JTable table;
    JButton back, searchButton;
    JTextField searchField;
    
    GuestInfo() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Header Image
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/guestInfobg.png"));
        JLabel header = new JLabel(icon);
        header.setBounds(0, 0, 1050, 70);
        add(header);

        // Search Label and TextField
        JLabel searchLabel = new JLabel("Search Guest ID:");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchLabel.setBounds(20, 110, 120, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(140, 110, 100, 30);
        add(searchField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(250, 110, 100, 30);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        add(searchButton);

        // Table and Scroll Pane
        table = new JTable();
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 150, 1000, 300);
        add(scrollPane);
        
        // Populate Table with All Data 
        populateTable("");

        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(440, 500, 120, 30);
        add(back);
        
        
        setBounds(300, 200, 1050, 600);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    // Method to Populate the Table
    private void populateTable(String guestID) {
        try {
            Conn c = new Conn();
            String query = "SELECT " +
                "guestID AS 'Guest ID', " +
                "name AS 'Name', " +
                "address AS 'Address', " +
                "number AS 'Phone Number', " +
                "document AS 'ID Document', " +
                "sex AS 'Gender', " +
                "country AS 'Country', " +
                "room AS 'Room Number', " +
                "check_in_date AS 'Check-In Date', " +
                "totalCost AS 'Total Cost', " +
                "deposit AS 'Deposit', " +
                "paymentMethod AS 'Payment Method', " +
                "length_of_stay AS 'Length of Stay', " +
                "check_out_date AS 'Check-Out Date', " +
                 "payment_status AS 'Payment Status' " +
                "FROM guest";

          
            if (!guestID.isEmpty()) {
                query += " WHERE guestID = '" + guestID + "'";
            }
            
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String guestID = searchField.getText().trim();
            populateTable(guestID); 
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }
    
    public static void main(String[] args) {
        new GuestInfo();
    }
}
