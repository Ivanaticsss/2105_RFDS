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
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class CheckIn extends JFrame implements ActionListener {
        
        private JComboBox<String> comboid, comboCottageType, comboCottageNumber, comboPoolSizes;
        private JTextField tfCottage, tfname, tfdeposit, tfaddress, tfnumber,tfFacilities, tfBedType, tfPrice, tflength,
                tfcountry, tfCottagePrice, tfPoolPrice, tfSpaPrice, tfRestoPrice, tfTourPrice, tfServicesCost;
         
        private JCheckBox checkboxPoolSize;
        private JRadioButton rmale, rfemale, rStandard, rVIP, rVVIP;
        private Choice croom;
        private JLabel checkintime, lblGuestID;
        private JButton add, back, chooseCottage, availServices;
        private JTextArea textArea;
        
      

        
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
        
        JLabel lblDays = new JLabel("days");
        lblDays.setBounds(250, 440, 200, 30);
        lblDays.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblDays);
        
        JLabel lbldeposit = new JLabel("Deposit:");
        lbldeposit.setBounds(45, 490, 200, 40);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lbldeposit);
        
        JLabel lblpeso = new JLabel("₱");
        lblpeso.setBounds(205, 485, 200, 40);
        lblpeso.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblpeso);
        
        tfdeposit = new JTextField("Enter Amount");
        tfdeposit.setBounds(220, 495, 150, 25);
        tfdeposit.setFont(new Font("Helvetica", Font.PLAIN, 17)); 
        tfdeposit.setBackground(Color.WHITE); 
        tfdeposit.setForeground(Color.GRAY);
        tfdeposit.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        
        
         tfdeposit.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfdeposit.getText().equals("Enter Amount")) {
                    tfdeposit.setText("");
                    tfdeposit.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfdeposit.getText().isEmpty()) {
                    tfdeposit.setText("Enter Amount");
                    tfdeposit.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfdeposit);
        
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
        JLabel lblgender = new JLabel("Sex");
        lblgender.setBounds(450, 190, 300, 30);
        lblgender.setFont(new Font("Helvetica", Font.PLAIN, 18));
        add(lblgender);
        
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
        
        JLabel lblServices = new JLabel("Selected Services: " + services);
        lblServices.setBounds(45, 525, 400, 30); // Set the position and size
        lblServices.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblServices);
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
                String query = "SELECT roomnumber FROM room WHERE roomtype = ?";
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
                    
                    tfBedType.setText("Not Available");
                    tfFacilities.setText("Not Available");
                    tfPrice.setText("Not Available");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        
       public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String name = tfname.getText();
        
        String id = (String) comboid.getSelectedItem();
        String gender = rmale.isSelected() ? "Male" : "Female";
        String room = croom.getSelectedItem();
        String deposit = tfdeposit.getText();

        try {
            // Format the current date and time
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = formatter.format(new Date()); 

            String query = "INSERT INTO guest (name, document, gender, room, checkintime, deposit) VALUES (?, ?, ?, ?, ?, ?)";
            String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = ?";

            Conn conn = new Conn();
            
            PreparedStatement stmt = conn.c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, name);
            //stmt.setString(2, number);
            stmt.setString(2, id);
            stmt.setString(3, gender);
            stmt.setString(4, room);
            stmt.setString(5, time); // Use formatted time here
            stmt.setBigDecimal(6, new BigDecimal(deposit));
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int guestID = generatedKeys.getInt(1);
                lblGuestID.setText("Guest ID: " + guestID);  // Display the generated GuestID
            } else {
                lblGuestID.setText("Guest ID not available");
            }
            
            // Prepare the second statement for updating room availability
            PreparedStatement stmt2 = conn.c.prepareStatement(query2);
            stmt2.setString(1, room);
            stmt2.executeUpdate();

            JOptionPane.showMessageDialog(null, "New Customer Added Successfully!");

            setVisible(false);
            new Reception();

        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (ae.getSource() == chooseCottage) {
        //new SearchCottages(this);
    } else if (ae.getSource() == back) {
        setVisible(false);
        new Reception();
    } else if (ae.getSource() == availServices) {  
        new AvailServices(this);
    } 
    
        
}


    public static void main(String[] args) {
        new CheckIn();
    }
}
