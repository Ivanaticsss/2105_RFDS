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
public class Service extends JFrame implements ActionListener {
    
    JTable serviceTable;
    JButton backButton;
    JTextField serviceNameField;
    JButton submitButton;
    
    Service() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
       
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/servicesbg.png"));
        JLabel headerLabel = new JLabel(icon);
        headerLabel.setBounds(-20, -10, 1050, 80);
        add(headerLabel);

        JLabel serviceLabel = new JLabel("Service Name:");
        serviceLabel.setBounds(50, 100, 100, 20);
        add(serviceLabel);

        serviceNameField = new JTextField();
        serviceNameField.setBounds(150, 100, 150, 25);
        serviceNameField.setBackground(Color.white);
        add(serviceNameField);
        
        serviceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(serviceTable);
        scrollPane.setBounds(0, 150, 1000, 250);
        add(scrollPane);

        loadTableData("SELECT * FROM serviceMenu");
        
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
                String serviceName = serviceNameField.getText(); 
                String query;
                
                if (serviceName.isEmpty()) {
                    query = "SELECT * FROM serviceMenu";  
                } else {
                    query = "SELECT * FROM serviceMenu WHERE service_name LIKE '%" + serviceName + "%'";  // Search by service name
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
        ResultSetMetaData metaData = rs.getMetaData();
        
        String[] columnNames = {"Service ID", "Service Name", "Service Price"};
        
       
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
        
        while (rs.next()) {
            Object[] row = new Object[metaData.getColumnCount()];
            for (int i = 0; i < row.length; i++) {
                row[i] = rs.getObject(i + 1); 
            }
            model.addRow(row); 
        }
        serviceTable.setModel(model);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error executing query: " + e.getMessage());
        e.printStackTrace();
    }
      
    }

    public static void main(String[] args) {
        new Service();
    }
}
