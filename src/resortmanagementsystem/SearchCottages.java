package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;
import net.proteanit.sql.DbUtils; // Ensure rs2xml.jar is included in your classpath

public class SearchCottages extends JFrame implements ActionListener {

    JTable table;
    JButton back, submit;
    JComboBox<String> cottageType;
    JCheckBox available;

    public SearchCottages() {
        setTitle("Search Cottage");
        
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
        submit.setBackground(Color.decode("#9c6644"));
        submit.setForeground(Color.WHITE);
        submit.setBounds(300, 100, 120, 25);  
        submit.setFont(new Font("Helvetica", Font.BOLD, 14));  
        submit.setBorder(new LineBorder(Color.decode("#9c6644"), 2, true));  
        submit.setFocusPainted(false); 
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        submit.addActionListener(this);
       
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submit.setBackground(Color.decode("#a17d58")); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                submit.setBackground(Color.decode("#9c6644")); 
            }
        });

        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.decode("#2a1c13"));
        back.setForeground(Color.WHITE);
        back.setBounds(400, 510, 120, 30);
        back.setFont(new Font("Helvetica", Font.BOLD, 14));  
        back.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  
        back.setFocusPainted(false); 
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        back.addActionListener(this);

        // Hover effect for the "Back" button
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(Color.decode("#3e2a1f"));  
            }
            @Override
            public void mouseExited(MouseEvent e) {
                back.setBackground(Color.decode("#2a1c13")); 
            }
        });

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
