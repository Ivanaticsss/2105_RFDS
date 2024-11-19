package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;
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
        submitButton.setBackground(Color.decode("#9c6644"));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBounds(300, 100, 120, 25);  
        submitButton.setFont(new Font("Helvetica", Font.BOLD, 14));  
        submitButton.setBorder(new LineBorder(Color.decode("#9c6644"), 2, true));  
        submitButton.setFocusPainted(false); 
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        submitButton.addActionListener(this);
       
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setBackground(Color.decode("#a17d58")); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setBackground(Color.decode("#9c6644")); 
            }
        });

        add(submitButton);
        
        backButton = new JButton("Back");
        backButton.setBackground(Color.decode("#2a1c13"));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(400, 510, 120, 30);
        backButton.setFont(new Font("Helvetica", Font.BOLD, 14));  
        backButton.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  
        backButton.setFocusPainted(false); 
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        backButton.addActionListener(this);

        // Hover effect for the "Back" button
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.decode("#3e2a1f"));  
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.decode("#2a1c13")); 
            }
        });

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
