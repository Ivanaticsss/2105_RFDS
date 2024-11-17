package resortmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//NOT SURE

/**
 *
 * @author Danah
 */

    public class EditGuest extends JFrame implements ActionListener {
        JTextField tfName, tfAddress, tfRoom, tfCheckOutDate, tfPaid, tfPending, tfTotalCost;
        JButton updateButton, cancelButton;
        String guestID;

    EditGuest(String guestID) {
        this.guestID = guestID;

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Edit Info");
        text.setFont(new Font("HELVETICA", Font.PLAIN, 20));
        text.setBounds(120, 10, 200, 30);
        text.setForeground(Color.BLACK);
        add(text);

        
        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30, 70, 100, 20);
        add(lblName);
        
        tfName = new JTextField();
        tfName.setBounds(100, 70, 150, 25);
        add(tfName);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30, 100, 100, 20);
        add(lblAddress);
        tfAddress = new JTextField();
        tfAddress.setBounds(100, 100, 150, 25);
        add(tfAddress);
        
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        add(updateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        add(cancelButton);

        // Fetch the current details for this guest
        loadGuestData();

        setBounds(300, 200, 400, 300);
        setVisible(true);
    }

    private void loadGuestData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM guest WHERE guestID = '" + guestID + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                tfName.setText(rs.getString("name"));
                tfAddress.setText(rs.getString("address"));
                tfRoom.setText(rs.getString("room"));
                tfCheckOutDate.setText(rs.getString("check_out_date"));
                tfPaid.setText(rs.getString("deposit"));
                // Calculate and show pending amount
                int totalCost = rs.getInt("totalCost");
                int paidAmount = rs.getInt("deposit");
                tfPending.setText(String.valueOf(totalCost - paidAmount));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            // Save the updated guest information to the database
            try {
                String name = tfName.getText();
                String address = tfAddress.getText();
                String room = tfRoom.getText();
                String checkOutDate = tfCheckOutDate.getText();
                String paid = tfPaid.getText();

                Conn c = new Conn();
                c.s.executeUpdate("UPDATE guest SET name = '" + name + "', address = '" + address + "', room = '" + room + "', check_out_date = '" + checkOutDate + "', deposit = '" + paid + "' WHERE guestID = '" + guestID + "'");

                JOptionPane.showMessageDialog(null, "Guest Info Updated!");
                setVisible(false); // Close the edit window

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false); // Close the edit window without making any changes
        }
    }

    public static void main(String[] args) {
        new EditGuest("G123"); // Replace with the actual guestID
    }
}

    

