package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.*;  // rs2xml.jar

/**
 *
 * @author Danah Paris
 */
public class Pool extends JFrame implements ActionListener {
    
    JTable poolTable;
    JButton backButton;
    JTextField poolNameField;
    JButton submitButton;
    
    Pool() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        
       
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/poolheader.jpg"));
        JLabel headerLabel = new JLabel(icon);
        headerLabel.setBounds(-20, -10, 1050, 80);
        add(headerLabel);

        JLabel serviceLabel = new JLabel("Pool Type: ");
        serviceLabel.setBounds(50, 100, 100, 20);
        add(serviceLabel);

        poolNameField = new JTextField();
        poolNameField.setBounds(150, 100, 150, 25);
        poolNameField.setBackground(Color.white);
        add(poolNameField);
        
        poolTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(poolTable);
        scrollPane.setBounds(0, 150, 1000, 250);
        add(scrollPane);

        loadTableData("SELECT * FROM pool");

        submitButton = new JButton("Submit");
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.white);
        submitButton.addActionListener(this);
        submitButton.setBounds(300, 100, 120, 25); 
        add(submitButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        backButton.addActionListener(this);
        backButton.setBounds(500, 510, 120, 30);
        add(backButton);

        setBounds(300, 200, 1000, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            try {
                String poolName = poolNameField.getText(); 
                String query;
                
               if (poolName.isEmpty()) {
                query = "SELECT pool_type, price FROM pool";  
            } else {
                query = "SELECT pool_type, price FROM pool WHERE pool_type LIKE '%" + poolName + "%'";
            }

                loadTableData(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Reception();
        }
    }

    private void loadTableData(String query) {
    try {
        Conn conn = new Conn(); 
        ResultSet rs = conn.s.executeQuery(query);

        String[] columnNames = {"Pool Name", "Pool Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        
        while (rs.next()) {
            String poolName = rs.getString("pool_type");  // Adjust to your database column name
            String poolPrice = rs.getString("price"); // Adjust to your database column name

           
            model.addRow(new Object[]{poolName, poolPrice});
        }

        // Update table model
        poolTable.setModel(model);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error executing query: " + e.getMessage());
        e.printStackTrace();
    }

    }

    public static void main(String[] args) {
        new Pool();
    }
}
