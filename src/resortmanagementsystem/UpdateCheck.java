package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateCheck extends JFrame implements ActionListener {

    Choice ccustomer;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending, tftotalCost, tfdeposit, tfchange;
    JButton check, update, back, generateBill, calculate;
    JTable guestTable;
    JScrollPane scrollPane;

    UpdateCheck() {
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);

            JLabel text = new JLabel("Update Status");
            text.setFont(new Font("Tamoha", Font.PLAIN, 20));
            text.setBounds(90, 20, 200, 30);
            text.setForeground(Color.BLUE);
            add(text);

            JLabel lblid = new JLabel("Guest ID");
            lblid.setBounds(30, 80, 100, 20);
            add(lblid);

            ccustomer = new Choice();
            ccustomer.setBounds(200, 80, 150, 25);
            add(ccustomer);

            // Load Guest IDs
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT guestID FROM guest");
                while (rs.next()) {
                    ccustomer.add(rs.getString("guestID"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Initialize text fields here
            JLabel lblroom = new JLabel("Room");
            lblroom.setBounds(30, 120, 100, 20);
            add(lblroom);
            tfroom = new JTextField();
            tfroom.setBounds(200, 120, 150, 25);
            add(tfroom);

            JLabel lblname = new JLabel("Name");
            lblname.setBounds(30, 160, 100, 20);
            add(lblname);
            tfname = new JTextField();
            tfname.setBounds(200, 160, 150, 25);
            add(tfname);

            
            JLabel lblCheckIn = new JLabel("Check-In Date");
            lblCheckIn.setBounds(30, 200, 100, 20);
            add(lblCheckIn);
            tfcheckin = new JTextField();
            tfcheckin.setBounds(200, 200, 150, 25);
            add(tfcheckin);

            JLabel lblPaid = new JLabel("Paid");
            lblPaid.setBounds(30, 240, 100, 20);
            add(lblPaid);
            tfpaid = new JTextField();
            tfpaid.setBounds(200, 240, 150, 25);
            add(tfpaid);

            
            JLabel lblPending = new JLabel("Pending");
            lblPending.setBounds(30, 280, 100, 20);
            add(lblPending);
            tfpending = new JTextField();
            tfpending.setBounds(200, 280, 150, 25);
            tfpending.setEditable(false);  // To make it read-only
            add(tfpending);

            
            JLabel lblTotal = new JLabel("Total Cost");
            lblTotal.setBounds(30, 320, 100, 20);
            add(lblTotal);
            tftotalCost = new JTextField();
            tftotalCost.setBounds(200, 320, 150, 25);
            add(tftotalCost);
            
            JLabel lblDeposit = new JLabel("New Deposit");
            lblDeposit.setBounds(30, 360, 100, 20);
            add(lblDeposit);
            tfdeposit = new JTextField();
            tfdeposit.setBounds(200, 360, 150, 25);
            add(tfdeposit);
            
            calculate = new JButton("Calculate");
            calculate.setBackground(Color.BLACK);
            calculate.setForeground(Color.WHITE);
            calculate.setBounds(360, 360, 100, 30);
            calculate.addActionListener(this);
            add(calculate);

            JLabel lblChange = new JLabel("Change");
            lblChange.setBounds(30, 400, 100, 20);
            add(lblChange);
            tfchange = new JTextField();
            tfchange.setBounds(200, 400, 150, 25);
            add(tfchange);

            // Check Button
            check = new JButton("Check");
            check.setBackground(Color.BLACK);
            check.setForeground(Color.WHITE);
            check.setBounds(30, 440, 100, 30);
            check.addActionListener(this);
            add(check);

            // Update Button
            update = new JButton("Update");
            update.setBackground(Color.BLACK);
            update.setForeground(Color.WHITE);
            update.setBounds(150, 440, 100, 30);
            update.addActionListener(this);
            add(update);

            // Generate Bill Button
            generateBill = new JButton("Generate Bill");
            generateBill.setBackground(Color.BLACK);
            generateBill.setForeground(Color.WHITE);
            generateBill.setBounds(270, 440, 120, 30);
            generateBill.addActionListener(this);
            add(generateBill);

            // Back Button
            back = new JButton("Back");
            back.setBackground(Color.BLACK);
            back.setForeground(Color.WHITE);
            back.setBounds(400, 440, 100, 30);
            back.addActionListener(this);
            add(back);

            // Set up the table with guest data
            String[] columnNames = {"Guest ID", "Name", "Room Number", "Check-In Time", "Check-Out Time", "Total Cost", "Deposit", "Payment Status"};
            Object[][] data = fetchGuestData();
            guestTable = new JTable(data, columnNames);
            guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            guestTable.setBounds(380, 50, 580, 250);
            scrollPane = new JScrollPane(guestTable);
            scrollPane.setBounds(380, 50, 580, 250);
            add(scrollPane);

            setBounds(300, 200, 980, 520);
            setVisible(true);
        }


        private Object[][] fetchGuestData() {
         Object[][] data = new Object[0][8];  
         try {
             Conn c = new Conn();
             Statement stmt = c.c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery("SELECT guestID, name, room, check_in_date, check_out_date, totalCost, deposit, payment_status FROM guest");

          
             rs.last();
             int rowCount = rs.getRow();
             rs.beforeFirst();  

             data = new Object[rowCount][8];  
             int i = 0;
             while (rs.next()) {
                 data[i][0] = rs.getString("guestID");
                 data[i][1] = rs.getString("name");
                 data[i][2] = rs.getString("room");
                 data[i][3] = rs.getString("check_in_date");
                 data[i][4] = rs.getString("check_out_date");
                 data[i][5] = rs.getString("totalCost");
                 data[i][6] = rs.getString("deposit");
                 data[i][7] = rs.getString("payment_status");
                 i++;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return data;
     }


            public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == check) {
        String id = ccustomer.getSelectedItem();
        String query = "SELECT * FROM guest WHERE guestID = '" + id + "'";
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                tfroom.setText(rs.getString("room"));
                tfname.setText(rs.getString("name"));
                tfcheckin.setText(rs.getString("check_in_date"));
                tfpaid.setText(rs.getString("deposit"));
                tftotalCost.setText(rs.getString("totalCost"));

                double cost = Double.parseDouble(rs.getString("totalCost"));
                double paid = Double.parseDouble(rs.getString("deposit"));
                double pending = cost - paid;

                if (pending > 0) {
                    tfpending.setText(String.format("%.2f", pending));
                    tfchange.setText("0.00");
                } else {
                    tfpending.setText("0.00");
                    tfchange.setText(String.format("%.2f", Math.abs(pending))); // Calculate change
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 else if (ae.getSource() == calculate) {
            try {
                double cost = Double.parseDouble(tftotalCost.getText());
                double newDeposit = Double.parseDouble(tfdeposit.getText());
                double paid = Double.parseDouble(tfpaid.getText());
                double totalPaid = paid + newDeposit;
                double pending = cost - totalPaid;

                if (pending > 0) {
                    tfpending.setText(String.format("%.2f", pending));
                    tfchange.setText("0.00");
                } else {
                    tfpending.setText("0.00"); // Ensure pending is 0 if fully paid
                    tfchange.setText(String.format("%.2f", Math.abs(pending))); // Calculate change
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (ae.getSource() == update) {
                String number = ccustomer.getSelectedItem();
                String room = tfroom.getText();
                String name = tfname.getText();
                String checkin = tfcheckin.getText();
                String deposit = tfdeposit.getText();

                try {
                    double cost = Double.parseDouble(tftotalCost.getText());
                    double totalPaid = Double.parseDouble(tfpaid.getText()) + Double.parseDouble(deposit);
                    String paymentStatus = (totalPaid >= cost) ? "Paid" : "Pending";

                    Conn c = new Conn();
                    c.s.executeUpdate("UPDATE guest SET room = '" + room + "', name = '" + name + "', check_in_date = '" + checkin + "', deposit = '" + totalPaid + "', payment_status = '" + paymentStatus + "' WHERE guestID = '" + number + "'");

                    JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                    setVisible(false);
                    new Reception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ae.getSource() == generateBill) {
                String guestID = ccustomer.getSelectedItem();
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM guest WHERE guestID = '" + guestID + "'");
                    if (rs.next()) {
                        String bill = "Guest ID: " + guestID + "\nName: " + rs.getString("name") + "\nRoom: " + rs.getString("room") +
                                "\nCheck-In: " + rs.getString("check_in_date") + "\nCheck-Out: " + rs.getString("check_out_date") +
                                "\nTotal Cost: " + rs.getString("totalCost") + "\nDeposit: " + rs.getString("deposit") +
                                "\nPayment Status: " + rs.getString("payment_status");
                        JOptionPane.showMessageDialog(null, bill, "Generated Bill", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ae.getSource() == back) {
                setVisible(false);
                new Reception();
            }
        }


    public static void main(String[] args) {
        new UpdateCheck();
    }
}
