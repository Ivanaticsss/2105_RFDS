package resortmanagementsystem;

/**
 *
 * @author Paris and Valiente
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Calendar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class CheckIn extends JFrame implements ActionListener {
        
        private JComboBox<String> comboid, comboPayment;
        private JTextField  tfname, tfdeposit, tfaddress, tfnumber,tfFacilities, tfBedType, tfPrice, tflength,
                tfcountry, tfTotalCost, tfServicesCost, tfchange;
         
        private JCheckBox checkboxPoolSize;
        private JRadioButton rmale, rfemale, rStandard, rVIP, rVVIP;
        private Choice croom;
        private JLabel checkintime, lblGuestID, lblDepositRange;
        private JButton add, back, chooseCottage, availServices;
        private JTextArea textArea;
        double roomPrice; 
        int serviceCost; 
        private int guestID;
        
        private void fetchNextGuestID() {
    try {
        Conn conn = new Conn();
        String query = "SELECT MAX(guestID) FROM guest";
        ResultSet rs = conn.s.executeQuery(query);

        if (rs.next()) {
            int nextGuestID = rs.getInt(1) + 1;  // Get the current max ID and add 1
            lblGuestID.setText("Guest ID: " + nextGuestID);
        } else {
            lblGuestID.setText("Guest ID: 1");  // Default to 1 if no IDs found
        }
    } catch (Exception e) {
        e.printStackTrace();
        lblGuestID.setText("Guest ID not available");
    }
    
    // Fetch the integer part from the label text
    String guestIDText = lblGuestID.getText();
    
    guestID = Integer.parseInt(guestIDText.replaceAll("[^0-9]", ""));
    System.out.println("Next Guest ID: " + guestID);
}
        
        CheckIn() {
       
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(getClass().getResource("/icons/logobg.png"));
        Image i2 = i1.getImage().getScaledInstance(400, 150, Image.SCALE_SMOOTH); // Use smoother scaling
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,10,400,150);
        add(image);
        
        
        lblGuestID = new JLabel("Generating...");
        lblGuestID.setBounds(200, 120, 150, 25);
        lblGuestID.setFont(new Font("Helvetica", Font.BOLD, 18));
        add(lblGuestID);
        
        fetchNextGuestID();
        
        //Name Input
        JLabel lblname = new JLabel("Full Name     :");
        lblname.setBounds(45, 160, 300, 30);
        lblname.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblname);
        
        tfname = new JTextField("Enter Name");
        tfname.setBounds(200, 165, 200, 25);
        tfname.setForeground(Color.GRAY);
        tfname.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tfname.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        
        tfname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfname.getText().equals("Enter Name")) {
                    tfname.setText("");
                    tfname.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfname.getText().isEmpty()) {
                    tfname.setText("Enter Name");
                    tfname.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfname);
        
        //Address input
        JLabel lbladress = new JLabel("Full Adress   :");
        lbladress.setBounds(45, 190, 300, 30);
        lbladress.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lbladress);
        
        tfaddress = new JTextField("Enter Address");
        tfaddress.setBounds(200, 195, 200, 25);
        tfaddress.setForeground(Color.GRAY);
        tfaddress.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tfaddress.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        
        tfaddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfaddress.getText().equals("Enter Address")) {
                    tfaddress.setText("");
                    tfaddress.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfaddress.getText().isEmpty()) {
                    tfaddress.setText("Enter Address");
                    tfaddress.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfaddress);
        
        //Phone Number input
        JLabel lblnumber = new JLabel("Mobile Number:");
        lblnumber.setBounds(45, 220, 300, 30);
        lblnumber.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblnumber);
        
        tfnumber = new JTextField("Number");
        tfnumber.setBounds(200, 225, 200, 25);
        tfnumber.setForeground(Color.GRAY);
        tfnumber.setFont(new Font("Helvetica", Font.PLAIN, 15));
        tfnumber.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        
        tfnumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfnumber.getText().equals("Number")) {
                    tfnumber.setText("");
                    tfnumber.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfnumber.getText().isEmpty()) {
                    tfnumber.setText("Number");
                    tfnumber.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfnumber);
        
        
        //Choose Room
        
        //Show Room details
        
        JLabel lblroomtype = new JLabel("Room Type:");
        lblroomtype.setBounds(45, 280, 100, 30);
        lblroomtype.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblroomtype);
        
        rStandard = new JRadioButton("Standard");
        rStandard.setBackground(Color.decode("#FFE0B2")); 
        rStandard.setForeground(Color.decode("#3E2522")); 
        rStandard.setBounds(200, 285, 80, 25);
        
        rVIP = new JRadioButton("VIP");
        rVIP.setBackground(Color.decode("#FFE0B2")); 
        rVIP.setForeground(Color.decode("#3E2522")); 
        rVIP.setBounds(290, 285, 50, 25);
        
        rVVIP = new JRadioButton("VVIP");
        rVVIP.setBackground(Color.decode("#FFE0B2")); 
        rVVIP.setForeground(Color.decode("#3E2522")); 
        rVVIP.setBounds(350, 285, 60, 25);
        
        rStandard.addItemListener(e -> updateRoomNumbers("Standard"));
        rVIP.addItemListener(e -> updateRoomNumbers("VIP"));
        rVVIP.addItemListener(e -> updateRoomNumbers("VVIP"));

        ButtonGroup roomType = new ButtonGroup();
        roomType.add(rStandard);
        roomType.add(rVIP);
        roomType.add(rVVIP);
        
        add(rStandard);
        add(rVIP);
        add(rVVIP);
        
        JLabel lblroom = new JLabel("Room No.");
        lblroom.setBounds(45, 315, 100, 30);
        lblroom.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblroom);
        
        JLabel lblBedType = new JLabel("Bed Type");
        lblBedType.setBounds(45, 345, 100, 30);
        lblBedType.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblBedType);

        tfBedType = new JTextField();
        tfBedType.setBounds(200, 350, 200, 25);
        tfBedType.setEditable(false); //read-only
        tfBedType.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfBedType.setBackground(Color.WHITE); 
        tfBedType.setForeground(Color.BLACK); 
        tfBedType.setBorder(new LineBorder(Color.decode("#D3A376"), 1)); 
        add(tfBedType);
        
        JLabel lblFacilities = new JLabel("Facilities");
        lblFacilities.setBounds(45, 375, 100, 30);
        lblFacilities.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblFacilities);

        tfFacilities = new JTextField();
        tfFacilities.setBounds(200, 380, 200, 25);
        tfFacilities.setEditable(false); // read-only
        tfFacilities.setFont(new Font("Helvetica", Font.PLAIN, 10)); 
        tfFacilities.setBackground(Color.WHITE); 
        tfFacilities.setForeground(Color.BLACK); 
        tfFacilities.setBorder(new LineBorder(Color.decode("#D3A376"), 1)); 
        add(tfFacilities);
        
        JLabel lblPrice = new JLabel("Price per day:");
        lblPrice.setBounds(45, 405, 200, 30);
        lblPrice.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(200, 410, 200, 25);
        tfPrice.setEditable(false); // read-only
        tfPrice.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfPrice.setBackground(Color.WHITE); 
        tfPrice.setForeground(Color.BLACK); 
        tfPrice.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tfPrice);
        tfPrice.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                updateTotalCost();
            }
        });

        
        croom = new Choice();
        croom.setFont(new Font("Helvetica", Font.BOLD, 14));
        croom.setBounds(200, 320, 150, 25);
        add(croom);

            croom.addItemListener(e -> {
                String selectedRoom = (String) croom.getSelectedItem();
                if (selectedRoom != null && !selectedRoom.isEmpty()) {
                    try {
                        updateRoomDetails(selectedRoom); 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

        // Populate Room Number Choice
        try {
            Conn conn = new Conn();
            String query = "select roomnumber from room";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        

       
        JLabel lblLength = new JLabel("Length of Stay:");
        lblLength.setBounds(45, 435, 200, 30);
        lblLength.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblLength);

        tflength = new JTextField();
        tflength.setBounds(200, 440, 40, 25);
        tflength.setEditable(true); 
        tflength.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tflength.setBackground(Color.WHITE); 
        tflength.setForeground(Color.BLACK); 
        tflength.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tflength);
        tflength.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    updateTotalCost();
                }
            });
        
        
        JLabel lblDays = new JLabel("day/s");
        lblDays.setBounds(250, 440, 200, 30);
        lblDays.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblDays);
        
        
        JLabel lblTotalCost = new JLabel("Total Cost:");
        lblTotalCost.setBounds(45, 490, 200, 40);
        lblTotalCost.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(lblTotalCost);
        
        JLabel lblPesoTotalCost = new JLabel("₱"); 
        lblPesoTotalCost.setBounds(200, 485, 20, 40);
        lblPesoTotalCost.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblPesoTotalCost);
        
        tfTotalCost = new JTextField("0");
        tfTotalCost.setBounds(220, 495, 150, 25);
        tfTotalCost.setFont(new Font("Helvetica", Font.PLAIN, 17));
        tfTotalCost.setBackground(Color.WHITE);
        tfTotalCost.setForeground(Color.GRAY);
        tfTotalCost.setEditable(false); 
        tfTotalCost.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tfTotalCost);
        
        
        JLabel lbldeposit = new JLabel("Deposit:");
        lbldeposit.setBounds(45, 540, 200, 40);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lbldeposit);
        
        
        JLabel lblPesoDeposit = new JLabel("₱"); // Separate JLabel for Deposit peso sign
        lblPesoDeposit.setBounds(200, 535, 20, 40);
        lblPesoDeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblPesoDeposit);
        
        tfdeposit = new JTextField("0.0");
        tfdeposit.setBounds(220, 545, 150, 25);
        tfdeposit.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfdeposit.setBackground(Color.WHITE); 
        tfdeposit.setForeground(Color.GRAY);
        tfdeposit.setEditable(true);
        tfdeposit.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        
   
        
        add(tfdeposit);
        tfdeposit.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                updateTotalCost(); // Trigger total cost and change calculation
            }
        });

        
        // Label to display the deposit range
        lblDepositRange = new JLabel("Range: ₱0.00 - ₱0.00");
        lblDepositRange.setBounds(200, 570, 250, 25); // Adjust position as needed
        lblDepositRange.setFont(new Font("Helvetica", Font.PLAIN, 14));
        lblDepositRange.setForeground(Color.DARK_GRAY);
        add(lblDepositRange);
        
        JLabel lblchange = new JLabel("Change:");
        lblchange.setBounds(45, 585, 200, 40);
        lblchange.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblchange);
        
        
        JLabel lblPesoChange = new JLabel("₱"); // Separate JLabel for Deposit peso sign
        lblPesoChange.setBounds(200, 585, 20, 40);
        lblPesoChange.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblPesoChange);
        
        tfchange = new JTextField("Enter Amount");
        tfchange.setBounds(220, 595, 150, 25);
        tfchange.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfchange.setBackground(Color.WHITE); 
        tfchange.setForeground(Color.GRAY);
        tfchange.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tfchange);
        
        //===Second Column===//
        //ID input
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(450, 165, 150, 25);
        lblid.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        add(lblid);
        
        String options[] = {"Driver's License", "SSS ID", "Pag-ibig ID", "Voter's ID", "National ID", "Company ID", "Student's ID", "Passport/Visa"};
        comboid = new JComboBox<>(options);
        comboid.setBounds(520, 165, 150, 25);
        comboid.setBackground(Color.WHITE);
        comboid.setFont(new Font("HELVETICA", Font.PLAIN, 15));
        Border border = BorderFactory.createLineBorder(Color.decode("#D3A376"), 2);  // Change 'Color.BLUE' to your desired color
        comboid.setBorder(border);
        add(comboid);
        
        //SEX input
        JLabel lblsex = new JLabel("Sex");
        lblsex.setBounds(450, 190, 300, 30);
        lblsex.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(lblsex);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(520, 195, 70, 25);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(590, 195, 80, 25);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rmale);
        sexGroup.add(rfemale);
        
        add(rmale);
        add(rfemale);
        
        JLabel lblCountry = new JLabel("Country: ");
        lblCountry.setBounds(450, 225, 300, 30);
        lblCountry.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblCountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(520, 225, 150, 25);
        tfcountry.setEditable(true); 
        tfcountry.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfcountry.setBackground(Color.WHITE); 
        tfcountry.setForeground(Color.BLACK); 
        tfcountry.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tfcountry);
        
        
        //CHECK IN TIME 
        JLabel lbltime = new JLabel("Check-in Time: ");
        lbltime.setBounds(500, 265, 150, 60);
        lbltime.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(lbltime);
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
        String formattedDate = formatter.format(date);

        checkintime = new JLabel(formattedDate);
        checkintime.setBounds(650, 280, 300, 30);
        checkintime.setFont(new Font("Helvetica", Font.BOLD, 17));
        add(checkintime);
        

        
        availServices = new JButton ("Avail Services");
        availServices.setBackground(Color.decode("#D3A376"));
        availServices.setForeground(Color.WHITE);
        availServices.setFont(new Font("Helvetica", Font.BOLD, 18));
        availServices.setBounds (520, 320, 200, 40);
        availServices.addActionListener(this);
        add (availServices);
            String services = null;
        
        textArea = new JTextArea();
        textArea.setBounds(520, 360, 200, 100);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 15));
        textArea.setForeground(Color.BLACK);
        textArea.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(520,360, 200, 100);
        add(scrollPane);
        
        JLabel lblpaymentMethod = new JLabel("Payment Method");
        lblpaymentMethod.setBounds(400, 545, 150, 25);
        lblpaymentMethod.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        add(lblpaymentMethod);
        
        String paymentOptions[] = {"Cash", "Credit Card (VISA, Mastercard, etc.)", "Debit Card", "E-Wallet"};
        comboPayment = new JComboBox<>(paymentOptions);
        comboPayment.setBounds(530, 545, 200, 25);
        comboPayment.setBackground(Color.WHITE);
        comboPayment.setFont(new Font("HELVETICA", Font.PLAIN, 15));
        Border paymentBorder = BorderFactory.createLineBorder(Color.decode("#D3A376"), 2);  // Change 'Color.BLUE' to your desired color
        comboPayment.setBorder(paymentBorder);
        add(comboPayment);
        
        
        
        
        add = new JButton ("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds (250, 700, 120, 25);
        add.addActionListener(this);
        add (add);
        
        back = new JButton ("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds (400, 700, 120, 25);
        back.addActionListener(this);
        add (back);
        
        setBounds(300, 100, 1200, 800);
        setVisible(true);
        setLocationRelativeTo(null); 
    }
        
        public void updateTotalCost() {
    try {
        // Fetch the room price
        String roomNumber = croom.getSelectedItem();
        Conn conn = new Conn();
        String query = "SELECT price FROM room WHERE roomnumber = ?";
        PreparedStatement stmt = conn.c.prepareStatement(query);
        stmt.setString(1, roomNumber);
        ResultSet rs = stmt.executeQuery();

        double roomPrice = 0;
        if (rs.next()) {
            roomPrice = rs.getDouble("price"); // Fetch the room price
        }
        // Get the service price
        double servicePrice = 0;
        String servicesText = textArea.getText(); 
        String[] parts = servicesText.split("Total Cost: ");
        if (parts.length > 1) {
            try {
                
                servicePrice = Double.parseDouble(parts[1].trim());
                System.out.println("Parsed Service Cost: " + servicePrice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in Total Cost.");
            }
        }
        System.out.println("Service:" + servicePrice);
        int lengthOfStay = Integer.parseInt(tflength.getText());
        
        System.out.println(lengthOfStay);
        // Calculate the total cost
        double totalCost = (roomPrice + servicePrice) * lengthOfStay;
        tfTotalCost.setText(String.format("%.2f", totalCost));

        double minDeposit = totalCost * 0.1; // 10% minimum deposit
        double maxDeposit = totalCost;  //maximum deposit
        // Update deposit range label
        lblDepositRange.setText(String.format("Range: ₱%.2f - ₱%.2f", minDeposit, maxDeposit));
        double depositAmount = 0;
        try {
            depositAmount = Double.parseDouble(tfdeposit.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid deposit amount.");
        }

        double change = 0;
        if (depositAmount > totalCost) {
            change = depositAmount - totalCost;
        }
        tfchange.setText(String.format("%.2f", change));
        tfchange.setForeground(change > 0 ? Color.BLACK : Color.GRAY);


    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

        public int getLengthOfStay() {
        return Integer.parseInt(tflength.getText());
    }
         public void displayServices(String services) {
        textArea.setText(services);  
         }
        
           private void updateRoomDetails(String roomNumber) {
        System.out.println("Updating details for room number: " + roomNumber); // Debugging: Check if the method is called with the correct room number
        try {
            Conn conn = new Conn();
            String query = "SELECT bed_type, facilities, price FROM room WHERE roomnumber = ?";
            PreparedStatement stmt = conn.c.prepareStatement(query);
            stmt.setString(1, roomNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String bedType = rs.getString("bed_type");
                String facilities = rs.getString("facilities");
                String price = rs.getString("price");

                // Debugging: Check values fetched from the database
                System.out.println("Bed Type: " + bedType);
                System.out.println("Facilities: " + facilities);
                System.out.println("Price: " + price);

                // Update the fields with room details
                tfBedType.setText(bedType);
                tfFacilities.setText(facilities);
                tfPrice.setText(price);
            } else {
                // Clear the fields if no room is found
                 System.out.println("Room not found, updating text fields...");
                tfBedType.setText("Not Found");
                System.out.println("Bed Type: " + tfBedType.getText());
                tfFacilities.setText("Not Found");
                tfPrice.setText("Not Found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

             private void updateRoomNumbers(String roomType) {
            croom.removeAll();  // Clear existing room numbers

            try {
                Conn conn = new Conn();
                String query = "SELECT roomnumber FROM room WHERE roomtype = ? AND availability = 'Available'";
                PreparedStatement stmt = conn.c.prepareStatement(query);
                stmt.setString(1, roomType);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    croom.add(rs.getString("roomnumber"));
                }

                // If there are rooms, set the first one as selected to update the room details
                if (croom.getItemCount() > 0) {
                    updateRoomDetails(croom.getItem(0));
                } else {
                    
                    tfBedType.setText("No Rooms Available");
                    tfFacilities.setText("No Rooms Available");
                    tfPrice.setText("No Rooms Available");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public int getGuestID() {
                return guestID;
            }
        
      public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == availServices) {
        String name = tfname.getText();
        String address = tfaddress.getText();  
        String number = tfnumber.getText();    
        String id = (String) comboid.getSelectedItem();  
        String sex = rmale.isSelected() ? "Male" : "Female"; 
        String room = croom.getSelectedItem();  
        String country = tfcountry.getText();   
        String deposit = tfdeposit.getText();  
        String paymentMethod = (String)comboPayment.getSelectedItem();  
        String totalCost = tfTotalCost.getText();  
        
        // Get the length of stay (you can get it from user input or calculate it based on dates)
        String lengthOfStay =  tflength.getText(); 

        try {
            // Format the current date and time
            SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
            String checkInDate = checkintime.getText();
            
            Date checkInParsedDate = inputFormat.parse(checkInDate);
            
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedCheckInDate = outputFormat.format(checkInParsedDate);
           
            // Calculate check-out date based on length of stay
            int stayLength = Integer.parseInt(lengthOfStay);  // Convert length of stay to integer
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkInParsedDate);  // Set check-in date as the starting point
            cal.add(Calendar.DAY_OF_YEAR, stayLength);  // Add length of stay
            String checkOutDate = outputFormat.format(cal.getTime());
            
            // Query to insert the new guest into the guest table
            String query = "INSERT INTO guest (name, address, number, document, sex, country, room, check_in_date, check_out_date, totalCost, deposit, paymentMethod, length_of_stay) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = ?";

            Conn conn = new Conn();

            PreparedStatement stmt = conn.c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, number);
            stmt.setString(4, id);
            stmt.setString(5, sex);
            stmt.setString(6, country);
            stmt.setString(7, room);
            stmt.setString(8, formattedCheckInDate);  // Use formatted check-in date here
            stmt.setString(9, checkOutDate);  // Use calculated check-out date here
            stmt.setBigDecimal(10, new BigDecimal(totalCost));  // Total cost
            stmt.setBigDecimal(11, new BigDecimal(deposit));  // Deposit amount
            stmt.setString(12, paymentMethod);  // Payment method
            stmt.setInt(13, stayLength);  // Length of stay
            stmt.executeUpdate();

          
            
            // Prepare the statement to update room availability
            PreparedStatement stmt2 = conn.c.prepareStatement(query2);
            stmt2.setString(1, room);
            stmt2.executeUpdate();
             new AvailServices(this, guestID);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (ae.getSource() == chooseCottage) {
        // new SearchCottages(this);
    } else if (ae.getSource() == back) {
        setVisible(false);
        new Reception();
    } 
    
            else if (ae.getSource() == add) {
        try {
            Conn conn = new Conn(); // Initialize your connection object

            // Parse total cost and deposit values
            double totalCost = Double.parseDouble(tfTotalCost.getText().trim());
            double deposit = Double.parseDouble(tfdeposit.getText().trim());

            // Validate deposit range
            if (deposit < totalCost * 0.1 || deposit > totalCost) {
                throw new IllegalArgumentException("Deposit must be within the valid range.");
            }

            // Update the guest table in the database
            String query = "UPDATE guest SET totalCost = ?, deposit = ? WHERE guestID = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setDouble(1, totalCost);
            pstmt.setDouble(2, deposit);
            pstmt.setInt(3, guestID); // Ensure guestID is set correctly

            // Execute the update
            int rowsUpdated = pstmt.executeUpdate();

            // Check if the update was successful
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Check-In Finalized Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Guest not found. Check-In not finalized.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for total cost and deposit.");
            return; // Stop 
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return; // Stop 
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error finalizing Check-In.");
            return; // Stop 
        }

        // Close the current window and return to Reception
        setVisible(false);
        new Reception();
    }
          }

    public static void main(String[] args) {
        new CheckIn();
    }
}