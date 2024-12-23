package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class UpdateCheck extends JFrame implements ActionListener {

    Choice ccustomer;
    JComboBox<String> searchOption; 
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending, tftotalCost, tfdeposit, tfchange, tfSearch;
    JButton check, update, back, generateBill, calculate, searchButton;
    JTable guestTable;
    JScrollPane scrollPane;

    UpdateCheck() {
        setTitle("Payment Update");
            getContentPane().setBackground(Color.WHITE);
            setLayout(null);
            
           

            ImageIcon icon = new ImageIcon(getClass().getResource("/icons/updatestatusheader.png"));
            JLabel header = new JLabel(icon);
            header.setBounds(0, -5, 1050, 70);
            add(header);
            
            Font labelFont = new Font("Tahoma", Font.BOLD, 13); 
            
            JLabel lblSearchOption = new JLabel("Search by");
            lblSearchOption.setBounds(30, 80, 100, 20);
            lblSearchOption.setFont(new Font("Tahoma", Font.BOLD, 13)); 
            add(lblSearchOption);

            String[] options = {"Guest ID", "Guest Name"};
            searchOption = new JComboBox<>(options);
            searchOption.setBounds(150, 80, 150, 25);
            searchOption.addActionListener(this); 
            add(searchOption);

      
            ccustomer = new Choice();
            ccustomer.setBounds(150, 120, 150, 25);
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
            
           // TextField for Guest Name search
        tfSearch = new JTextField();
        tfSearch.setBounds(150, 120, 150, 25);
        add(tfSearch);
        
   
        tfSearch.setVisible(false);

        JLabel lblSearch = new JLabel("Search");
        lblSearch.setBounds(30, 120, 100, 20);
        lblSearch.setFont(labelFont); 
        add(lblSearch);


            
            JLabel lblroom = new JLabel("Room");
            lblroom.setBounds(30, 160, 100, 20);
            lblroom.setFont(labelFont); 
            add(lblroom);
            tfroom = new JTextField();
            tfroom.setBounds(150, 160, 150, 25);
            add(tfroom);

            JLabel lblname = new JLabel("Name");
            lblname.setBounds(30, 200, 100, 20);
            lblname.setFont(labelFont); 
            add(lblname);
            tfname = new JTextField();
            tfname.setBounds(150, 200, 150, 25);
            add(tfname);

            
            JLabel lblCheckIn = new JLabel("Check-In Date");
            lblCheckIn.setBounds(30, 240, 100, 20);
            lblCheckIn.setFont(labelFont); 
            add(lblCheckIn);
            tfcheckin = new JTextField();
            tfcheckin.setBounds(150, 240, 150, 25);
            add(tfcheckin);

            JLabel lblPaid = new JLabel("Paid");
            lblPaid.setBounds(30, 280, 100, 20);
            lblPaid.setFont(labelFont); 
            add(lblPaid);
            tfpaid = new JTextField();
            tfpaid.setBounds(150, 280, 150, 25);
            add(tfpaid);

            
            JLabel lblPending = new JLabel("Balance");
            lblPending.setBounds(30, 320, 100, 20);
            lblPending.setFont(labelFont); 
            add(lblPending);
            tfpending = new JTextField();
            tfpending.setBounds(150, 320, 150, 25);
            tfpending.setEditable(false);  // To make it read-only
            add(tfpending);

            
            JLabel lblTotal = new JLabel("Total Cost");
            lblTotal.setBounds(30, 360, 100, 20);
            lblTotal.setFont(labelFont); 
            add(lblTotal);
            tftotalCost = new JTextField();
            tftotalCost.setBounds(150, 360, 150, 25);
            add(tftotalCost);
            
            JLabel lblDeposit = new JLabel("New Deposit");
            lblDeposit.setBounds(30, 400, 100, 20);
            lblDeposit.setFont(labelFont);
            add(lblDeposit);
            tfdeposit = new JTextField();
            tfdeposit.setBounds(150, 400, 150, 25);
            add(tfdeposit);
            
            calculate = new JButton("Calculate");
            
            calculate.setBounds(300, 400, 100, 25);
            calculate.addActionListener(this);
            add(calculate);

            JLabel lblChange = new JLabel("Change");
            lblChange.setBounds(30, 440, 100, 20);
            lblChange.setFont(labelFont);
            add(lblChange);
            tfchange = new JTextField();
            tfchange.setBounds(150, 440, 150, 25);
            add(tfchange);

           
            searchButton = new JButton("");
            searchButton.setBounds(310, 120, 100, 25); 
            searchButton.setBackground(Color.WHITE);
            ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("icons/search.png"));
            Image img = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
            searchIcon = new ImageIcon(img);
            searchButton.setIcon(searchIcon);
            searchButton.setBounds(310, 120, searchIcon.getIconWidth(), searchIcon.getIconHeight()); // Adjust size based on icon size
            searchButton.addActionListener(this);
            add(searchButton);

            
                // Check Out Button
             update = new JButton("Update");
             update.setBackground(Color.decode("#2a1c13"));
             update.setForeground(Color.WHITE);
             update.setBounds(280,500, 120, 40);  
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
             back.setBounds(420, 500, 120, 40); 
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


             generateBill = new JButton("Generate Bill");
             generateBill.setBackground(Color.decode("#9c6644"));
             generateBill.setForeground(Color.WHITE);
             generateBill.setBounds(560, 500, 120, 40);  
             generateBill.setFont(new Font("Helvetica", Font.BOLD, 14));  
             generateBill.setBorder(new LineBorder(Color.decode("#9c6644"), 2, true));  
             generateBill.setFocusPainted(false); 
             generateBill.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
             generateBill.addActionListener(this);

             generateBill.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseEntered(MouseEvent e) {
                     generateBill.setBackground(Color.decode("#a17d58")); 
                 }
                 @Override
                 public void mouseExited(MouseEvent e) {
                     generateBill.setBackground(Color.decode("#9c6644")); 
                 }
             });

             add(generateBill);



            // Set up the table with guest data
            String[] columnNames = {"Guest ID", "Name", "Room Number", "Check-In Date", "Check-Out Date", "Total Cost", "Deposit", "Payment Status"};
            Object[][] data = fetchGuestData();
            guestTable = new JTable(data, columnNames);
            guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            guestTable.setBounds(420, 100, 590, 300);
            scrollPane = new JScrollPane(guestTable);
            scrollPane.setBounds(420, 100, 590, 300);
            add(scrollPane);

            setBounds(300, 200, 1050, 600);
            setLocationRelativeTo(null); 
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
      
        if (ae.getSource() == searchButton) {
            String searchCriteria = searchOption.getSelectedItem().toString();
            String query = "";
            
            if (searchCriteria.equals("Guest ID")) {
                // Search by Guest ID
                String id = ccustomer.getSelectedItem();
                query = "SELECT * FROM guest WHERE guestID = '" + id + "'";
            } else if (searchCriteria.equals("Guest Name")) {
                // Search by Guest Name
                String name = tfSearch.getText().trim();
                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a name to search.");
                    return;
                }
                query = "SELECT * FROM guest WHERE name LIKE '%" + name + "%'";
            }

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
        } else if (ae.getSource() == searchOption) {
            
            String selectedOption = (String) searchOption.getSelectedItem();
            if ("Guest ID".equals(selectedOption)) {
                ccustomer.setVisible(true);
                tfSearch.setVisible(false);
            } else if ("Guest Name".equals(selectedOption)) {
                ccustomer.setVisible(false);
                tfSearch.setVisible(true);
            }
        
        } else if (ae.getSource() == calculate) {
            
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
