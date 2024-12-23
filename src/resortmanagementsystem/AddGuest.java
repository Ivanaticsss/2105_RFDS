package resortmanagementsystem;

/**
 *
 * @author Valiente
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


public class AddGuest extends JFrame implements ActionListener {
        
        private JComboBox<String> comboid;
        private JTextField tfCottage, tfname, tfdeposit;
         
        private JRadioButton rmale, rfemale, rStandard, rVIP, rVVIP;
        private Choice croom;
        private JLabel checkintime, lblGuestID, text, lblname, lblid, lblgender, lblroomtype, lblroom, lbltime, lbldeposit;
        private JButton add, back, chooseCottage;
        
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
        
        AddGuest() {
        setTitle("wala na pala to");
        
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/formbg.jpg"));
        JLabel background = new JLabel(backgroundIcon);
        background.setBounds(0, 0, 800, 550);
            
        setContentPane(background); 
        setLayout(null);
        
        text = new JLabel("NEW GUEST FORM");
        text.setBounds(80, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 22));
        //add(text);
        background.add(text);
        
        lblGuestID = new JLabel("Generating...");
        lblGuestID.setBounds(200, 80, 150, 30);
        lblGuestID.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lblGuestID);
        
        fetchNextGuestID();
        
        
        lblname = new JLabel("Name");
        lblname.setBounds(45, 120, 300, 30);
        lblname.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lblname);
        
        tfname = new JTextField("Enter Name");
        tfname.setBounds(200, 120, 150, 25);
        tfname.setForeground(Color.GRAY);
        
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
        
        background.add(tfname);
        
        
        lblid = new JLabel("ID");
        lblid.setBounds(45, 160, 150, 25);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lblid);
        
        String options[] = {"Driver's License", "SSS ID", "Pag-ibig ID", "Voter's ID", "National ID", "Company ID", "Student's ID", "Passport/Visa"};
        comboid = new JComboBox<>(options);
        comboid.setBounds(200, 160, 150, 25);
        comboid.setBackground(Color.WHITE);
        background.add(comboid);
        
        
        
       
        lblgender = new JLabel("Sex");
        lblgender.setBounds(45, 190, 300, 30);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lblgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 195, 70, 25);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270, 195, 80, 25);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rmale);
        sexGroup.add(rfemale);
        
        background.add(rmale);
        background.add(rfemale);
        
        //choose room
        
        lblroomtype = new JLabel("Room Type:");
        lblroomtype.setBounds(45, 225, 100, 30);
        lblroomtype.setFont(new Font("Helvetica", Font.PLAIN, 18));
        background.add(lblroomtype);
        
        rStandard = new JRadioButton("Standard");
        rStandard.setBackground(Color.WHITE);
        rStandard.setBounds(200, 225, 150, 25);
        
        
        rVIP = new JRadioButton("VIP");
        rVIP.setBackground(Color.WHITE);
        rVIP.setBounds(200, 225, 150, 25);
        
        rVVIP = new JRadioButton("VVIP");
        rVVIP.setBackground(Color.WHITE);
        rVVIP.setBounds(200, 225, 150, 25);
                
           
        
        lblroom = new JLabel("Room No.");
        lblroom.setBounds(45, 225, 100, 30);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lblroom);
        
        croom = new Choice();
        
        try {
            Conn conn = new Conn();
            String query = "select * from room";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        croom.setBounds(200, 225, 150, 25);
        background.add(croom);
        
        lbltime = new JLabel("<html>Check-in<br>time</html>");
        lbltime.setBounds(45, 255, 150, 60);
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lbltime);
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
        String formattedDate = formatter.format(date);

        checkintime = new JLabel(formattedDate);
        checkintime.setBounds(200, 265, 300, 30);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 17));
        background.add(checkintime);
        
        lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(45, 325, 150, 40);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 18));
        background.add(lbldeposit);
        
        tfdeposit = new JTextField("Enter Amount");
        tfdeposit.setBounds(200, 325, 150, 25);
        tfdeposit.setForeground(Color.GRAY);
        
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
        
        background.add(tfdeposit);
        
        add = new JButton ("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds (250, 410, 120, 25);
        add.addActionListener(this);
        background.add (add);
        
        back = new JButton ("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds (400, 410, 120, 25);
        back.addActionListener(this);
        background.add (back);
        
        chooseCottage = new JButton ("Add Cottage");
        chooseCottage.setBackground(Color.BLACK);
        chooseCottage.setForeground(Color.WHITE);
        chooseCottage.setBounds (500, 120, 120, 25);
        chooseCottage.addActionListener(this);
        background.add (chooseCottage);
        
        
    tfCottage = new JTextField("No Cottage Selected");
    tfCottage.setBounds(500, 150, 150, 25); // Adjust the position and size as needed
    tfCottage.setEditable(false); // Make it read-only
    background.add(tfCottage);
        
        /*ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);*/
        
        setBounds(300, 100, 800, 550);
        setVisible(true);
    }
    
        
        // Add this method in AddGuest to set the cottage number
        public void setCottageNumber(String cottageNumber) {
        tfCottage.setText(cottageNumber);
}


        
       public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String name = tfname.getText();
        String id = (String) comboid.getSelectedItem();
        String gender = rmale.isSelected() ? "Male" : "Female";
        String room = croom.getSelectedItem();
        String deposit = tfdeposit.getText();

        // Prompt the user for confirmation before adding the guest
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to add this guest?", 
            "Confirm Addition", 
            JOptionPane.YES_NO_OPTION);

        // If the user selects "Yes", proceed with adding the guest
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Format the current date and time
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = formatter.format(new Date()); // Correctly formatted datetime string

                String query = "INSERT INTO guest (name, document, gender, room, checkintime, deposit) VALUES (?, ?, ?, ?, ?, ?)";
                String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = ?";

                Conn conn = new Conn();
                
                // Prepare the first statement for inserting customer data
                PreparedStatement stmt = conn.c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                stmt.setString(1, name);
                stmt.setString(2, id);
                stmt.setString(3, gender);
                stmt.setString(4, room);
                stmt.setString(5, time); // Use formatted time here
                stmt.setBigDecimal(6, new BigDecimal(deposit));
                stmt.executeUpdate();
                
                // Get the generated Guest ID (if any)
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
        }
        // If the user selects "No", do nothing (guest won't be added)
    } else if (ae.getSource() == chooseCottage) {
        new SearchCottages();
    } else if (ae.getSource() == back) {
        setVisible(false);
        new Reception();
    }
}



    
    public static void main(String[] args) {
        new AddGuest();
    }
}
