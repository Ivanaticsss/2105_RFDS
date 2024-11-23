
package resortmanagementsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.LineBorder;

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
        setTitle("Search Room");

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
        scrollPane.setBounds(0, 130, 1000, 300);
        add(scrollPane);

        // Load initial data
        loadData("select * from room"); 

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
