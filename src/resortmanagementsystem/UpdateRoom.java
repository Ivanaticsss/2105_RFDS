package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import net.proteanit.sql.DbUtils;

public class UpdateRoom extends JFrame implements ActionListener {

    JTextField tfroom, tfPrice, searchField;
    JComboBox<String> availablecombo;
    JButton check, update, back;
    JTable table;

    UpdateRoom() {
        setTitle("Room Status");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Room Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 25));
        text.setBounds(30, 20, 250, 30);
        text.setForeground(Color.BLACK);
        add(text);

        JLabel searchLabel = new JLabel("Search Room Number:");
        searchLabel.setBounds(30, 80, 200, 20);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(200, 80, 150, 30);
        add(searchField);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(400, 50, 500, 300);
        add(scrollPane);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 25);
        add(tfroom);

        JLabel lblavailability = new JLabel("Availability");
        lblavailability.setBounds(30, 180, 100, 20);
        add(lblavailability);

        String availableOption[] = { "Available", "Occupied" };
        availablecombo = new JComboBox<>(availableOption);
        availablecombo.setBounds(200, 180, 150, 25);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);

        JLabel lblPrice = new JLabel("Room Price");
        lblPrice.setBounds(30, 230, 100, 20);
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(200, 230, 150, 25);
        add(tfPrice);

        
        
        
             check = new JButton("Search");
             check.setBackground(Color.decode("#2a1c13"));
             check.setForeground(Color.WHITE);
             check.setBounds(30, 300, 100, 30);
             check.setFont(new Font("Helvetica", Font.BOLD, 14)); 
             check.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  // Rounded border
             check.setFocusPainted(false);  
             check.setCursor(new Cursor(Cursor.HAND_CURSOR));  
             check.addActionListener(this);

             check.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseEntered(MouseEvent e) {
                     check.setBackground(Color.decode("#3e2a1f"));  
                 }
                 @Override
                 public void mouseExited(MouseEvent e) {
                     check.setBackground(Color.decode("#2a1c13"));  
                 }
             });


             add(check);


        
             update = new JButton("Update");
             update.setBackground(Color.decode("#2a1c13"));
             update.setForeground(Color.WHITE);
             update.setBounds(150, 300, 100, 30);
             update.setFont(new Font("Helvetica", Font.BOLD, 14)); 
             update.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  // Rounded border
             update.setFocusPainted(false);  
             update.setCursor(new Cursor(Cursor.HAND_CURSOR));  
             update.addActionListener(this);

             update.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseEntered(MouseEvent e) {
                     update.setBackground(Color.decode("#3e2a1f"));  
                 }
                 @Override
                 public void mouseExited(MouseEvent e) {
                     update.setBackground(Color.decode("#2a1c13"));  
                 }
             });


             add(update);

             back = new JButton("Back");
             back.setBackground(Color.decode("#2a1c13"));
             back.setForeground(Color.WHITE);
             back.setBounds(270, 300, 100, 30);
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



        setBounds(300, 200, 980, 450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            String roomNumber = searchField.getText();
            if (roomNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a room number.");
                return;
            }
            String query = "select * from room where roomnumber = ?";
            try (Conn c = new Conn(); PreparedStatement ps = c.c.prepareStatement(query)) {
                ps.setString(1, roomNumber);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tfroom.setText(rs.getString("roomnumber"));
                    availablecombo.setSelectedItem(rs.getString("availability"));
                    tfPrice.setText(rs.getString("price"));
                } else {
                    JOptionPane.showMessageDialog(null, "Room not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String roomNumber = tfroom.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String price = tfPrice.getText();

            if (roomNumber.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            String query = "update room set availability = ?, price = ? where roomnumber = ?";
            try (Conn c = new Conn();PreparedStatement ps = c.c.prepareStatement(query)) {
                ps.setString(1, availability);
                ps.setString(2, price);
                ps.setString(3, roomNumber);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Room details updated successfully.");
                // Refresh table data
                ResultSet rs = c.s.executeQuery("select * from room");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateRoom();
    }
}
