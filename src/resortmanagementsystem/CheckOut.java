package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
import javax.swing.border.LineBorder;

public class CheckOut extends JFrame implements ActionListener {

    JTextField searchField, guestIDField, roomNumberField, checkInField, checkOutField, paymentField
            ;
    JButton checkOut, back, searchButton, generateBill;
    JTable guestTable;
    JScrollPane scrollPane;

    CheckOut() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        // Header Image
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/checkoutheader.png"));
        JLabel header = new JLabel(icon);
        header.setBounds(0, -5, 1050, 70);
        add(header);

        Font labelFont = new Font("Helvetica", Font.BOLD, 15);

        JLabel lblSearch = new JLabel("Search Guest ID:");
        lblSearch.setBounds(30, 100, 150, 30);
        Font font = new Font("Helvetica", Font.BOLD, 17);
        lblSearch.setFont(font);
        add(lblSearch);
        
        searchField = new JTextField();
        searchField.setBounds(170, 100, 150, 30);
        add(searchField);

        searchButton = new JButton("");
        searchButton.setBounds(330, 100, 100, 25);
        searchButton.setBackground(Color.WHITE);
        ImageIcon searchIcon = new ImageIcon(ClassLoader.getSystemResource("icons/search.png"));
        Image img = searchIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); 
        searchIcon = new ImageIcon(img);
        searchButton.setIcon(searchIcon);
        searchButton.setBounds(330, 100, searchIcon.getIconWidth(), searchIcon.getIconHeight()); // Adjust size based on icon size
        searchButton.addActionListener(this);
        add(searchButton);

        JLabel lblGuestID = new JLabel("Guest ID");
        lblGuestID.setBounds(30, 150, 120, 30);
        lblGuestID.setFont(labelFont);  
        add(lblGuestID);

        guestIDField = new JTextField();
        guestIDField.setBounds(170, 150, 150, 25);
        guestIDField.setEditable(false);
        guestIDField.setBackground(Color.WHITE);
        guestIDField.setBorder(new LineBorder(Color.GRAY)); 
        add(guestIDField);

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(30, 190, 150, 30);
        lblRoom.setFont(labelFont);  
        add(lblRoom);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(170, 190, 150, 25);
        roomNumberField.setEditable(false);
        roomNumberField.setBackground(Color.WHITE);
        roomNumberField.setBorder(new LineBorder(Color.GRAY)); 
        add(roomNumberField);

        JLabel lblCheckIn = new JLabel("Check-In Time");
        lblCheckIn.setBounds(30, 230, 150, 30);
        lblCheckIn.setFont(labelFont);  
        add(lblCheckIn);

        checkInField = new JTextField();
        checkInField.setBounds(170, 230, 150, 25);
        checkInField.setEditable(false);
        checkInField.setBackground(Color.WHITE);
        checkInField.setBorder(new LineBorder(Color.GRAY)); 
        add(checkInField);

        JLabel lblCheckOut = new JLabel("Check-Out Time");
        lblCheckOut.setBounds(30, 270, 150, 30);
        lblCheckOut.setFont(labelFont);  
        add(lblCheckOut);

        checkOutField = new JTextField();
        checkOutField.setBounds(170, 270, 150, 25);
        checkOutField.setEditable(false);
        checkOutField.setBackground(Color.WHITE);
        checkOutField.setBorder(new LineBorder(Color.GRAY)); 
        add(checkOutField);

        JLabel lblPayment = new JLabel("Payment Status");
        lblPayment.setBounds(30, 310, 150, 30);
        lblPayment.setFont(labelFont);  
        add(lblPayment);

        paymentField = new JTextField();
        paymentField.setBounds(170, 310, 150, 25);
        paymentField.setEditable(false);
        paymentField.setBackground(Color.WHITE);
        paymentField.setBorder(new LineBorder(Color.GRAY)); 
        add(paymentField);
        
        // Check Out Button
        checkOut = new JButton("Check Out");
        checkOut.setBackground(Color.decode("#2a1c13"));
        checkOut.setForeground(Color.WHITE);
        checkOut.setBounds(280,500, 120, 40);  
        checkOut.setFont(new Font("Helvetica", Font.BOLD, 14)); 
        checkOut.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  // Rounded border
        checkOut.setFocusPainted(false);  
        checkOut.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        checkOut.addActionListener(this);
        
        checkOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkOut.setBackground(Color.decode("#3e2a1f"));  
            }
            @Override
            public void mouseExited(MouseEvent e) {
                checkOut.setBackground(Color.decode("#2a1c13"));  
            }
        });

        
        add(checkOut);

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
        String[] columnNames = {"Guest ID", "Name", "Room Number", "Check-In Time", "Check-Out Time", "Payment Status"};
        Object[][] data = fetchGuestData();
        guestTable = new JTable(data, columnNames);
        guestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        guestTable.setBounds(400, 100, 590, 300);
        scrollPane = new JScrollPane(guestTable);
        scrollPane.setBounds(400, 100, 590, 300);
        add(scrollPane);

        setBounds(300, 200, 1050, 600);
        setLocationRelativeTo(null); 
        setVisible(true);
    }
    
    private String extractCottageNumber(String serviceName) {
    try {
        
        if (serviceName.contains("Cottage Number:")) {
            String[] parts = serviceName.split(","); 
            for (String part : parts) {
                if (part.trim().startsWith("Cottage Number:")) {
                    return part.split(":")[1].trim(); 
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; 
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

    if (guestID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please search for a guest first!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
        null, 
        "Are you sure you want to check out this guest?", 
        "Confirm Check-out", 
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Conn c = new Conn();

            // Update cottage availability if cottage services were availed
            String checkCottageQuery = "SELECT serviceName FROM services WHERE guestID = ? AND serviceName LIKE 'Cottage%'";
            PreparedStatement ps1 = c.c.prepareStatement(checkCottageQuery);
            ps1.setString(1, guestID);
            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {
                String serviceName = rs.getString("serviceName");

                // Extract the cottage number from serviceName
                String cottageNumber = extractCottageNumber(serviceName);

                if (cottageNumber != null) {
                    String updateCottageQuery = "UPDATE cottage SET availability = 'Available' WHERE cottagenumber = ?";
                    PreparedStatement ps2 = c.c.prepareStatement(updateCottageQuery);
                    ps2.setString(1, cottageNumber);
                    ps2.executeUpdate();
                }
            }

            // Delete records from services table
            String deleteServicesQuery = "DELETE FROM services WHERE guestID = ?";
            PreparedStatement ps3 = c.c.prepareStatement(deleteServicesQuery);
            ps3.setString(1, guestID);
            ps3.executeUpdate();

            // Delete guest record
            String deleteGuestQuery = "DELETE FROM guest WHERE guestID = ?";
            PreparedStatement ps4 = c.c.prepareStatement(deleteGuestQuery);
            ps4.setString(1, guestID);
            ps4.executeUpdate();

            // Update room availability if a room is associated
            if (!roomNumber.isEmpty()) {
                String updateRoomQuery = "UPDATE room SET availability = 'Available' WHERE roomnumber = ?";
                PreparedStatement ps5 = c.c.prepareStatement(updateRoomQuery);
                ps5.setString(1, roomNumber);
                ps5.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Check-out completed successfully!");
            refreshGuestTable();
            setVisible(false);
            new Reception();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred during check-out");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Check-out canceled");
    }
}

else if (ae.getSource() == generateBill) {
        String guestID = guestIDField.getText();

        if (guestID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please search for a guest first!");
            return;
        }

        // Show confirmation prompt before generating bill
        int confirm = JOptionPane.showConfirmDialog(null, 
                                                    "Do you want to generate the bill for this guest?", 
                                                    "Confirm Bill Generation", 
                                                    JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();

                // Query to get all necessary guest details based on guestID
                String query = "SELECT g.name, g.address, g.number, g.country, g.document, g.room, g.length_of_stay, g.check_in_date, g.check_out_date, " +
                        "r.roomType, r.facilities, r.bed_type, r.price, g.availedServices, g.totalCost, g.deposit, g.payment_status " +
                        "FROM guest g " +
                        "LEFT JOIN room r ON g.room = r.roomnumber " + // Use LEFT JOIN to handle guests who might not have a room
                        "WHERE g.guestID = ?";

                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, guestID);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Retrieve all necessary details
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String number = rs.getString("number");
                    String country = rs.getString("country");
                    String id = rs.getString("document");
                    String roomNumber = rs.getString("room");
                    String roomType = rs.getString("roomType");
                    String roomFacilities = rs.getString("facilities");
                    String bedType = rs.getString("bed_type");
                    int lengthOfStay = rs.getInt("length_of_stay");
                    Date checkInDate = rs.getDate("check_in_date");  // Date object
                    Date checkOutDate = rs.getDate("check_out_date");  // Date object
                    String servicesAvailed = rs.getString("availedServices");
                    double roomPrice = rs.getDouble("price");
                    double totalCost = rs.getDouble("totalCost");
                    double totalPayment = rs.getDouble("deposit");
                    String paymentStatus = rs.getString("payment_status");

                    // Now retrieve the services availed from the services table
                    String serviceQuery = "SELECT s.serviceName, s.price " +
                                          "FROM services s " +
                                          "WHERE s.guestID = ?";
                    PreparedStatement servicePs = c.c.prepareStatement(serviceQuery);
                    servicePs.setString(1, guestID);
                    ResultSet serviceRs = servicePs.executeQuery();

                    StringBuilder servicesDetails = new StringBuilder();
                    double servicesTotal = 0;
                    while (serviceRs.next()) {
                        String serviceName = serviceRs.getString("serviceName");
                        double servicePrice = serviceRs.getDouble("price");
                        servicesDetails.append(serviceName).append(" - ₱").append(servicePrice).append("\n");
                        servicesTotal += servicePrice;
                    }

                    // Call GenerateBill and pass all these values, with Date types for check-in and check-out dates
                    if (roomNumber != null && !roomNumber.isEmpty()) {
                        // If a room is reserved, pass room details as well
                        new GenerateBill(name, address, number, country, id, roomNumber, roomType, roomFacilities, bedType,
                                         lengthOfStay, checkInDate, checkOutDate, servicesDetails.toString(),
                                         roomPrice, servicesTotal, totalCost, totalPayment, paymentStatus);
                    } else {
                        // If no room is reserved, pass only services details
                        new GenerateBill(name, address, number, country, id, null, null, null, null,
                                         0, null, null, servicesDetails.toString(), 0, servicesTotal, totalCost, totalPayment, paymentStatus);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No guest found with this ID");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while fetching guest details");
            }
        } else {
            // If user selects "No", cancel the bill generation
            JOptionPane.showMessageDialog(null, "Bill generation canceled");
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
