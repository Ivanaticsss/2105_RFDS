package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckOut extends JFrame implements ActionListener {

    JTextField searchField, guestIDField, roomNumberField, checkInField, checkOutField, paymentField;
    JButton checkOut, back, searchButton;
    JTable guestTable;
    JScrollPane scrollPane;

    CheckOut() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Check Out");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tamoha", Font.PLAIN, 20));
        add(text);

        JLabel lblSearch = new JLabel("Search Guest ID:");
        lblSearch.setBounds(30, 80, 120, 30);
        add(lblSearch);

        searchField = new JTextField();
        searchField.setBounds(150, 80, 150, 25);
        add(searchField);

        
        
        searchButton = new JButton("");
        searchButton.setBounds(310, 80, 100, 25);
        searchButton.setBackground(Color.WHITE);
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("icons/search.png"));
        Image img = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
        searchIcon = new ImageIcon(img);
        searchButton.setIcon(searchIcon);
        searchButton.setBounds(310, 80, searchIcon.getIconWidth(), searchIcon.getIconHeight()); // Adjust size based on icon size
        searchButton.addActionListener(this);
        add(searchButton);

        JLabel lblGuestID = new JLabel("Guest ID");
        lblGuestID.setBounds(30, 130, 100, 30);
        add(lblGuestID);

        guestIDField = new JTextField();
        guestIDField.setBounds(150, 130, 150, 25);
        guestIDField.setEditable(false);
        guestIDField.setBackground(Color.WHITE);
        add(guestIDField);

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(30, 180, 100, 30);
        add(lblRoom);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(150, 180, 150, 25);
        roomNumberField.setEditable(false);
        roomNumberField.setBackground(Color.WHITE);
        add(roomNumberField);

        JLabel lblCheckIn = new JLabel("Check-In Time");
        lblCheckIn.setBounds(30, 230, 100, 30);
        add(lblCheckIn);

        checkInField = new JTextField();
        checkInField.setBounds(150, 230, 150, 25);
        checkInField.setEditable(false);
        checkInField.setBackground(Color.WHITE);
        add(checkInField);

        JLabel lblCheckOut = new JLabel("Check-Out Time");
        lblCheckOut.setBounds(30, 280, 100, 30);
        add(lblCheckOut);

        checkOutField = new JTextField();
        checkOutField.setBounds(150, 280, 150, 25);
        checkOutField.setEditable(false);
        checkOutField.setBackground(Color.WHITE);
        add(checkOutField);

        JLabel lblPayment = new JLabel("Payment Status");
        lblPayment.setBounds(30, 330, 100, 30);
        add(lblPayment);

        paymentField = new JTextField();
        paymentField.setBounds(150, 330, 150, 25);
        paymentField.setEditable(false);
        paymentField.setBackground(Color.WHITE);
        add(paymentField);

        checkOut = new JButton("Check Out");
        checkOut.setBackground(Color.BLACK);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBounds(30, 380, 120, 30);
        checkOut.addActionListener(this);
        add(checkOut);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 380, 120, 30);
        back.addActionListener(this);
        add(back);

        // Set up the table with guest data
        String[] columnNames = {"Guest ID", "Name", "Room Number", "Check-In Time", "Check-Out Time", "Payment Status"};
        Object[][] data = fetchGuestData();
        guestTable = new JTable(data, columnNames);
        guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guestTable.setBounds(350, 50, 450, 250);
        scrollPane = new JScrollPane(guestTable);
        scrollPane.setBounds(350, 50, 450, 250);
        add(scrollPane);

        setBounds(300, 200, 850, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String searchID = searchField.getText().trim();
            if (!searchID.isEmpty()) {
                searchGuestByID(searchID);
            }
        }

        if (ae.getSource() == checkOut) {
            String guestID = guestIDField.getText();
            String roomNumber = roomNumberField.getText();

            if (guestID.isEmpty() || roomNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please search for a guest first!");
                return;
            }

            String deleteGuestQuery = "DELETE FROM guest WHERE guestID = ?";
            String updateRoomAvailabilityQuery = "UPDATE room SET availability = 'Available' WHERE roomnumber = ?";
            String updatePaymentStatusQuery = "UPDATE guest SET payment_status = 'Paid' WHERE guestID = ?";
            
            try {
                Conn c = new Conn();
                
                //delete guest
                PreparedStatement ps1 = c.c.prepareStatement(deleteGuestQuery);
                ps1.setString(1, guestID);
                ps1.executeUpdate();

                //update room availability
                PreparedStatement ps2 = c.c.prepareStatement(updateRoomAvailabilityQuery);
                ps2.setString(1, roomNumber);
                ps2.executeUpdate();
                
                // Update payment status to 'Paid'
                PreparedStatement ps3 = c.c.prepareStatement(updatePaymentStatusQuery);
                ps3.setString(1, guestID);
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(null, "Check-out done");
                
                refreshGuestTable();
                setVisible(false);
                new Reception();  
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred during check-out");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }
    private Object[][] fetchGuestData() {
    Object[][] data = new Object[0][];  // Initialize data array to store fetched results
    try {
        Conn c = new Conn();
        ResultSet rs = c.s.executeQuery("SELECT * FROM guest");

       
        ResultSet rsScrollable = c.c.prepareStatement("SELECT * FROM guest", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery();

        
        rsScrollable.last();
        int rowCount = rsScrollable.getRow();  
        rsScrollable.beforeFirst();  

        data = new Object[rowCount][6];  
        int i = 0;
        while (rsScrollable.next()) {  
            data[i][0] = rsScrollable.getString("guestID");
            data[i][1] = rsScrollable.getString("name");
            data[i][2] = rsScrollable.getString("room");
            data[i][3] = rsScrollable.getString("check_in_date");
            data[i][4] = rsScrollable.getString("check_out_date");
            data[i][5] = rsScrollable.getString("payment_status");
            i++;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return data;  // Return the populated data
}

    private void searchGuestByID(String guestID) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM guest WHERE guestID = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, guestID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                guestIDField.setText(rs.getString("guestID"));
                roomNumberField.setText(rs.getString("room"));
                checkInField.setText(rs.getString("check_in_date"));
                checkOutField.setText(rs.getString("check_out_date"));
                paymentField.setText(rs.getString("payment_status"));
            } else {
                JOptionPane.showMessageDialog(null, "No guest found with this ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshGuestTable() {
        Object[][] data = fetchGuestData();
        guestTable.setModel(new javax.swing.table.DefaultTableModel(data, new String[]{
                "Guest ID", "Name", "Room Number", "Check-In Time", "Check-Out Time", "Payment Status"
        }));
    }

    public static void main(String[] args) {
        new CheckOut();
    }
}
