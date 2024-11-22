package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//ivan & danah

public class AddRooms extends JFrame implements ActionListener, ItemListener {
    
    private JButton add, cancel;
    private JTextField tfroom, tfprice;
    private JComboBox roomtypecombo, bedtypecombo, availablecombo;
    private JCheckBox wifi, poolAccess, oceanView, airConditioned;
    private JLabel roomImage, heading, lblroomtype, lblroomno, lblavailavle, lblprice, lbltype, lblFacilities; 
    
    AddRooms(){
        setTitle("Add Rooms");
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        lblroomtype = new JLabel("Room Type:");
        lblroomtype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroomtype.setBounds(60, 80, 120, 20);
        add(lblroomtype);
        
        String roomtypeOption[] = {"Standard", "VIP", "VVIP"};
        roomtypecombo = new JComboBox(roomtypeOption);
        roomtypecombo.setBounds(200, 80, 150, 30);
        roomtypecombo.setBackground(Color.WHITE);
        roomtypecombo.addItemListener(this);
        add(roomtypecombo);
        
        lblroomno = new JLabel("Room Number");
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroomno.setBounds(60, 130, 120, 20);
        add(lblroomno);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 130, 150, 30);
        add(tfroom);
        
        lblavailavle = new JLabel("Available");
        lblavailavle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailavle.setBounds(60, 180, 120, 20);
        add(lblavailavle);
        
        String availableOption[] = {"Available", "Occupied"};
        availablecombo = new JComboBox(availableOption);
        availablecombo.setBounds(200, 180, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblprice.setBounds(60, 230, 120, 20);
        add(lblprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);
        
        lbltype = new JLabel("Bed Type");
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbltype.setBounds(60, 280, 120, 20);
        add(lbltype);
        
        String bedtypeOption[] = {"Single Bed", "Double Bed", "Queen Bed", "King Bed"};
        bedtypecombo = new JComboBox(bedtypeOption);
        bedtypecombo.setBounds(200, 280, 150, 30);
        bedtypecombo.setBackground(Color.WHITE);
        add(bedtypecombo);
        
        // Facilities Checkboxes
        lblFacilities = new JLabel("Facilities:");
        lblFacilities.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFacilities.setBounds(60, 330, 120, 20);
        add(lblFacilities);
               
        airConditioned = new JCheckBox("Air Conditioned");
        airConditioned.setBackground(Color.WHITE);
        airConditioned.setBorderPainted(false);
        airConditioned.setBounds(200, 330, 150, 30);
        add(airConditioned);

        wifi = new JCheckBox("WiFi");
        wifi.setBackground(Color.WHITE);
        wifi.setBorderPainted(false);
        wifi.setBounds(200, 360, 100, 30);
        add(wifi);

        poolAccess = new JCheckBox("Pool Access");
        poolAccess.setBackground(Color.WHITE);
        poolAccess.setBorderPainted(false);
        poolAccess.setBounds(200, 390, 120, 30);
        add(poolAccess);

        oceanView = new JCheckBox("Ocean View");
        oceanView.setBackground(Color.WHITE);
        oceanView.setBorderPainted(false);
        oceanView.setBounds(200, 420, 120, 30);
        add(oceanView);

        
         // Image Label for displaying the room image
        roomImage = new JLabel();
        roomImage.setBounds(400, 80, 500, 400);
        add(roomImage);
        
        
        add = new JButton("Add Room");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60, 470, 130, 30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220, 470, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        
        setBounds(330, 200, 940, 570);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String roomnumber = tfroom.getText();
        String availability = (String) availablecombo.getSelectedItem();
        String price = tfprice.getText();
        String roomtype = (String) roomtypecombo.getSelectedItem();
        String type = (String) bedtypecombo.getSelectedItem();
        
        // Capture selected facilities
        String facilities = "";
        if (wifi.isSelected()) facilities += "WiFi, ";
        if (poolAccess.isSelected()) facilities += "Pool Access, ";
        if (oceanView.isSelected()) facilities += "Ocean View, ";
        if (airConditioned.isSelected()) facilities += "Air Conditioned, ";
        
        if (facilities.length() > 0) facilities = facilities.substring(0, facilities.length() - 2);
        
        // Validate fields
        if (roomnumber.isEmpty() || price.isEmpty() || availability == null || type == null || roomtype == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        // Show confirmation dialog
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to add this room?", 
            "Confirm Room Addition", 
            JOptionPane.YES_NO_OPTION);

        // If the user clicks "Yes", proceed with adding the room
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn conn = new Conn();

                String str = "insert into room (roomnumber, roomType, availability, price, bed_type, facilities) values('"
                        + roomnumber + "', '" + roomtype + "','" + availability + "', '" + price + "', '" + type + "', '" + facilities + "')";

                conn.s.executeUpdate(str);

                JOptionPane.showMessageDialog(null, "New Room Added successfully");

                // Clear fields after successful insertion
                roomtypecombo.setSelectedIndex(0);
                tfroom.setText("");
                tfprice.setText("");
                availablecombo.setSelectedIndex(0);
                bedtypecombo.setSelectedIndex(0);
                wifi.setSelected(false);
                poolAccess.setSelected(false);
                oceanView.setSelected(false);
                airConditioned.setSelected(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } else {
        dispose(); // Close the window if cancel button is clicked
    }
}

    
     
    public void itemStateChanged(ItemEvent e) {
        String roomType = (String) roomtypecombo.getSelectedItem();
        ImageIcon roomImageIcon = null;

        // 
        if (roomType.equals("Standard")) {
            roomImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/roombg.jpg"));
        } else if (roomType.equals("VIP")) {
            roomImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/viproom.jpg"));
        } else if (roomType.equals("VVIP")) {
            roomImageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/vviproom.jpg"));
        }

        roomImage.setIcon(roomImageIcon);
    }

    
    public static void main(String[] args){
        new AddRooms();
    }
}
