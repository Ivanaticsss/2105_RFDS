package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.border.LineBorder;

public class AvailServices extends JFrame {
        
    private JComboBox<String> comboCottageType, comboCottageNumber;
    private JTextField tfCottagePrice, tfPoolPrice, tfSpaPrice, tfRestoPrice, tfTourPrice, tfServicesCost;
    
    private JCheckBox chkCottage, chkPool, chkSpa, chkResto, chkTour, chkKiddie, chkAdult, chkInfinity;
    private JButton add, back, chooseCottage, btnConfirm, btnCancel, btnReset;
    private JLabel lblServices, lblServicesCost;
    private Choice ccottage;
     private CheckIn checkIn;
     private int guestID; 
    
    
    AvailServices(CheckIn checkIn, int guestID) {
        
        this.checkIn = checkIn;
        this.guestID = guestID;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Using null layout for custom positioning
        
        lblServices = new JLabel("Avail Services: ");
        lblServices.setBounds(50, 20, 150, 30);
        lblServices.setFont(new Font("Helvetica", Font.BOLD, 18));
        add(lblServices);

        // Checkbox for Cottage
        chkCottage = new JCheckBox("Cottage");
        chkCottage.setBounds(20, 60, 100, 30);
        chkCottage.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkCottage.addItemListener(e -> {
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        comboCottageType.setEnabled(isSelected);
        comboCottageNumber.setEnabled(isSelected);
        tfCottagePrice.setEnabled(isSelected);

        if (isSelected) {
            // Automatically update the cottage numbers and price when selected
            String selectedType = (String) comboCottageType.getSelectedItem();
            updateCottageNumbers(selectedType, Integer.toString(guestID));
            calculateTotalCost(); // Recalculate total cost
        }
    });


        add(chkCottage);

        // ComboBox for Cottage Type
        String[] cottageTypes = {"Standard", "Deluxe", "VIP"};
        comboCottageType = new JComboBox<>(cottageTypes);
        comboCottageType.setBounds(150, 60, 150, 30);
        comboCottageType.setEnabled(false); 
        add(comboCottageType);

        comboCottageNumber = new JComboBox<>();
        comboCottageNumber.setBounds(310, 60, 100, 30);
        comboCottageNumber.setEnabled(false);  // Disabled initially
        add(comboCottageNumber);
        
        comboCottageNumber.addActionListener(e -> {
        String selectedCottage = (String) comboCottageNumber.getSelectedItem();
        if (selectedCottage != null && !selectedCottage.isEmpty()) {
            try {
                updateCottagePrice(selectedCottage); 
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            tfCottagePrice.setText("Not Selected");
        }
    });


        // ItemListener for updating the cottage numbers
        comboCottageType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) { 
                String selectedType = (String) comboCottageType.getSelectedItem(); 
                if (selectedType != null) {
                    updateCottageNumbers(selectedType, Integer.toString(guestID));

 
                }
            }
        });


        // TextField for Cottage Price
        tfCottagePrice = new JTextField();
        tfCottagePrice.setBounds(420, 60, 150, 30);
        tfCottagePrice.setEnabled(false);
        add(tfCottagePrice);

        // Checkbox for Pool
        chkPool = new JCheckBox("Pool");
        chkPool.setBounds(20, 100, 100, 30);
        chkPool.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkPool.addItemListener(e -> {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
            chkKiddie.setEnabled(isSelected);
            chkAdult.setEnabled(isSelected);
            chkInfinity.setEnabled(isSelected);
            tfPoolPrice.setEnabled(isSelected);
            calculateTotalCost();  // Recalculate total cost when service is selected/deselected
        });
        add(chkPool);
        
        chkKiddie = new JCheckBox("Kiddie");
        chkKiddie.setBounds(150, 100, 80, 30);
        chkKiddie.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkKiddie.setEnabled(false);
        chkKiddie.addItemListener(e -> calculateTotalCost());
        add(chkKiddie);
        
        chkAdult = new JCheckBox("Adult");
        chkAdult.setBounds(230, 100, 80, 30);
        chkAdult.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkAdult.setEnabled(false);
        chkAdult.addItemListener(e -> calculateTotalCost());
        add(chkAdult);
        
        chkInfinity = new JCheckBox("Infinity Pool");
        chkInfinity.setBounds(310, 100, 110, 30);
        chkInfinity.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkInfinity.setEnabled(false);
        chkInfinity.addItemListener(e -> calculateTotalCost());
        add(chkInfinity);
        
        // TextField for Pool Price
        tfPoolPrice = new JTextField();
        tfPoolPrice.setBounds(420, 100, 150, 30);
        tfPoolPrice.setEnabled(false);
        add(tfPoolPrice);

        // Checkbox for Spa
        chkSpa = new JCheckBox("Spa");
        chkSpa.setBounds(20, 140, 100, 30);
        chkSpa.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkSpa.addItemListener(e -> {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
            tfSpaPrice.setEnabled(isSelected);
            calculateTotalCost();  // Recalculate total cost when service is selected/deselected
        });
        add(chkSpa);

        // TextField for Spa Price
        tfSpaPrice = new JTextField();
        tfSpaPrice.setBounds(150, 140, 150, 30);
        tfSpaPrice.setEnabled(false);
        add(tfSpaPrice);

        // Checkbox for Restaurant
        chkResto = new JCheckBox("Buffet");
        chkResto.setBounds(20, 180, 120, 30);
        chkResto.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkResto.addItemListener(e -> {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
            tfRestoPrice.setEnabled(isSelected);
            calculateTotalCost();  // Recalculate total cost when service is selected/deselected
        });
        add(chkResto);

        // TextField for Restaurant Price
        tfRestoPrice = new JTextField();
        tfRestoPrice.setBounds(150, 180, 150, 30);
        tfRestoPrice.setEnabled(false);
        add(tfRestoPrice);

        // Checkbox for Tour
        chkTour = new JCheckBox("Tour");
        chkTour.setBounds(20, 220, 100, 30);
        chkTour.setFont(new Font("Helvetica", Font.PLAIN, 16));
        chkTour.addItemListener(e -> {
            boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
            tfTourPrice.setEnabled(isSelected);
            calculateTotalCost();  // Recalculate total cost when service is selected/deselected
        });
        add(chkTour);

        // TextField for Tour Price
        tfTourPrice = new JTextField();
        tfTourPrice.setBounds(150, 220, 150, 30);
        tfTourPrice.setEnabled(false);
        add(tfTourPrice);

        // Label for Total Services Cost
        lblServicesCost = new JLabel("Total Cost: ");
        lblServicesCost.setBounds(20, 260, 150, 30);
        lblServicesCost.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblServicesCost);

        // TextField for Total Services Cost
        tfServicesCost = new JTextField();
        tfServicesCost.setBounds(150, 260, 150, 30);
        tfServicesCost.setEditable(false);
        tfServicesCost.setFont(new Font("Helvetica", Font.PLAIN, 17));
        tfServicesCost.setBackground(Color.WHITE);
        tfServicesCost.setForeground(Color.BLACK);
        tfServicesCost.setBorder(new LineBorder(Color.decode("#D3A376"), 1));
        add(tfServicesCost);

        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(150, 300, 100, 30);
        btnConfirm.addActionListener(e -> confirmAction());  // Define the action here
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 300, 100, 30);
        btnCancel.addActionListener(e -> cancelAction());  // Define the action here
        add(btnCancel);

        btnReset = new JButton("Reset");
        btnReset.setBounds(370, 300, 100, 30);
        btnReset.addActionListener(e -> resetForm());  // Define the action here
        add(btnReset);

        setTitle("Avail Services");
        setBounds(300, 100, 600, 400);  
        setVisible(true);
        setLocationRelativeTo(null);  // Center the window on the screen
        
        
   
    }
    
    private void confirmAction() {
        // Prompt the user for confirmation
        int response = JOptionPane.showConfirmDialog(
                this, 
                "Are you sure you want to confirm these services?", 
                "Confirm Services", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);

        // Check the user's response
        if (response != JOptionPane.YES_OPTION) {
            // If the user clicks "No", exit the method
            return;
        }

        StringBuilder services = new StringBuilder();
        double totalCost = 0.0;   

        checkIn.updateTotalCost(); 

        if (chkCottage.isSelected()) {
            String selectedCottageType = comboCottageType.getSelectedItem().toString();
            String selectedCottageNumber = comboCottageNumber.getSelectedItem().toString();

            try (Conn conn = new Conn()) {
                // Update cottage availability
                String updateQuery = "UPDATE cottage SET availability = 'Occupied' WHERE cottage_type = ? AND cottagenumber = ?";
                try (PreparedStatement updateStmt = conn.c.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, selectedCottageType);
                    updateStmt.setString(2, selectedCottageNumber);
                    updateStmt.executeUpdate();
                }

                // Insert service details into the database
                String insertQuery = "INSERT INTO services (guestID, serviceName, price) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.c.prepareStatement(insertQuery)) {
                    stmt.setInt(1, guestID);
                    stmt.setString(2, "Cottage Type: " + selectedCottageType + ", Cottage Number: " + selectedCottageNumber);
                    stmt.setDouble(3, getCottagePrice(selectedCottageType));
                    stmt.executeUpdate();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // Append the cottage information to services
            services.append("Cottage Type: ").append(selectedCottageType)
                    .append(", Cottage Number: ").append(selectedCottageNumber)
                    .append("\n");

            // Update the total cost
            totalCost += getCottagePrice(selectedCottageType);
        }

        // Pool
        if (chkPool.isSelected()) {
            String poolType = chkKiddie.isSelected() ? "Kiddie" : (chkAdult.isSelected() ? "Adult" : "Infinity");
            services.append("Pool: ").append(poolType).append("\n");
            totalCost += getPoolPrice(); 
        }

        // Spa
        if (chkSpa.isSelected()) {
            services.append("Spa: Yes\n");
            totalCost += getSpaPrice(); 
        }

        // Buffet
        if (chkResto.isSelected()) {
            services.append("Buffet: Yes\n");
            totalCost += getBuffetPrice(); 
        }

        // Tour
        if (chkTour.isSelected()) {
            services.append("Tour: Yes\n");
            totalCost += getTourPrice(); 
        }

        services.append("Total Cost: ").append(totalCost).append("\n");

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO services (guestID, serviceName, price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.c.prepareStatement(query);

            // Cottage
            if (chkCottage.isSelected()) {
                stmt.setInt(1, guestID);  
                stmt.setString(2, "Cottage Type: " + comboCottageType.getSelectedItem() + ", Cottage Number: " + comboCottageNumber.getSelectedItem());
                stmt.setDouble(3, getCottagePrice(comboCottageType.getSelectedItem().toString()));
                stmt.executeUpdate();
            }

            // Pool
            if (chkPool.isSelected()) {
                StringBuilder poolTypes = new StringBuilder();

                if (chkKiddie.isSelected()) poolTypes.append("Kiddie");
                if (chkAdult.isSelected()) {
                    if (poolTypes.length() > 0) poolTypes.append(", ");
                    poolTypes.append("Adult");
                }
                if (chkInfinity.isSelected()) {
                    if (poolTypes.length() > 0) poolTypes.append(", ");
                    poolTypes.append("Infinity");
                }

                if (poolTypes.length() > 0) {
                    stmt.setInt(1, guestID);
                    stmt.setString(2, "Pool: " + poolTypes.toString());
                    stmt.setDouble(3, getPoolPrice());
                    stmt.executeUpdate();
                }
            }

            // Spa
            if (chkSpa.isSelected()) {
                stmt.setInt(1, guestID);  
                stmt.setString(2, "Spa: Yes");
                stmt.setDouble(3, getSpaPrice()); 
                stmt.executeUpdate();
            }

            // Buffet
            if (chkResto.isSelected()) {
                stmt.setInt(1, guestID);  
                stmt.setString(2, "Buffet: Yes");
                stmt.setDouble(3, getBuffetPrice()); 
                stmt.executeUpdate();
            }

            // Tour
            if (chkTour.isSelected()) {
                stmt.setInt(1, guestID); 
                stmt.setString(2, "Tour: Yes");
                stmt.setDouble(3, getTourPrice()); 
                stmt.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "Services and guest details successfully updated!");
            checkIn.displayServices(services.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving the data.");
        }

        setVisible(false); // Close the AvailServices window
    }


    
    
    private void cancelAction(){
        
        setVisible(false);
        
    }
    
        private void resetForm() {
        // Resetting text fields
        tfCottagePrice.setText("");
        tfPoolPrice.setText("");
        tfSpaPrice.setText("");
        tfRestoPrice.setText("");
        tfTourPrice.setText("");
        tfServicesCost.setText("");
        comboCottageType.setSelectedIndex(0);
        comboCottageNumber.setSelectedIndex(-1); 
        // Resetting checkboxes 
        chkCottage.setSelected(false);
        chkPool.setSelected(false);
        chkSpa.setSelected(false);
        chkResto.setSelected(false);
        chkTour.setSelected(false);
        chkKiddie.setSelected(false);
        chkAdult.setSelected(false);
        chkInfinity.setSelected(false);
    }

       private void updateCottagePrice(String cottageNumber) {
            try {
                Conn conn = new Conn();
                String query = "SELECT price FROM cottage WHERE cottagenumber = ?";
                PreparedStatement stmt = conn.c.prepareStatement(query);
                stmt.setString(1, cottageNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    double price = rs.getDouble("price"); // Fetch as double
                    tfCottagePrice.setText(String.format("%.2f", price)); // Display with 2 decimal places
                } else {
                    tfCottagePrice.setText("0.0"); // Default to zero if no cottage is found
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                tfCottagePrice.setText("Error");
            }
        }
       
 private void updateCottageNumbers(String cottageType, String guestID) {
    comboCottageNumber.removeAllItems(); // Clear previous items
    try {
        Conn conn = new Conn();

        String getDatesQuery = "SELECT check_in_date, check_out_date FROM guest WHERE guestID = ?";
        PreparedStatement stmt1 = conn.c.prepareStatement(getDatesQuery);
        stmt1.setString(1, guestID);
        ResultSet rs1 = stmt1.executeQuery();

        Date checkInDate = null;
        Date checkOutDate = null;

        // If dates found, set check-in and check-out dates
        if (rs1.next()) {
            checkInDate = rs1.getDate("check_in_date");
            checkOutDate = rs1.getDate("check_out_date");
        }

        // If no dates found, show error
        if (checkInDate == null || checkOutDate == null) {
            JOptionPane.showMessageDialog(this, "No reservation dates found for the selected guest.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Query to check availability of cottages of the selected type for both current check-ins and future reservations
       String query = "SELECT cottageNumber FROM cottage WHERE cottage_type = ? " +
               "AND availability = 'Available' " +
               "AND cottageNumber NOT IN (" +
               "    SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(serviceName, 'Cottage Number: ', -1), ',', 1) " +
               "    FROM services WHERE serviceName LIKE ? " +
               "    AND guestID != ? " +
               ")";

        PreparedStatement stmt2 = conn.c.prepareStatement(query);
        stmt2.setString(1, cottageType); // Cottage type selected
        stmt2.setString(2, "Cottage Type: " + cottageType + "%"); // Like pattern to match all services related to this cottage type
        stmt2.setString(3, guestID); // Current guest ID to avoid excluding their own booking
        ResultSet rs2 = stmt2.executeQuery();

        boolean hasCottages = false;

        // Add available cottages to the combo box
        while (rs2.next()) {
            hasCottages = true;
            comboCottageNumber.addItem(rs2.getString("cottageNumber"));
        }

        // If no cottages are available, show a message
        if (!hasCottages) {
            JOptionPane.showMessageDialog(this, "No cottages available for the selected type and dates.", "No Availability", JOptionPane.INFORMATION_MESSAGE);
        }

        rs1.close();
        rs2.close();
        stmt1.close();
        stmt2.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}




        private double getCottagePrice(String cottageType) {
            double price = 0.0;
            if (cottageType == null || cottageType.isEmpty()) {
                return 0.0; // Return a default price or handle error
            }
            try {
                Conn conn = new Conn();
              
                String query = "SELECT price FROM cottage WHERE cottage_type = ?"; 
                PreparedStatement stmt = conn.c.prepareStatement(query);
                stmt.setString(1, cottageType);  
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    price = rs.getDouble("price"); 
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return price;
        }
        
                private double getPoolPrice() {
            double price = 0.0;

            if (chkKiddie.isSelected()) {
                price += 200.00; 
            }
            if (chkAdult.isSelected()) {
                price += 500.00; 
            }
            if (chkInfinity.isSelected()) {
                price += 1500.00; 
            }

            return price;
        }
           
                
          private double getSpaPrice() {
                return 5000.0; 
            }

          private double getTourPrice() {
            return 2000.0; // Example fixed price for tour service
        }
          private double getBuffetPrice() {
            return 8000.0; // Example fixed price for tour service
        }




    private void calculateTotalCost() {
         double totalCost = 0.0;
            
         if (chkCottage.isSelected()) {
        double cottagePrice = Double.parseDouble(tfCottagePrice.getText().isEmpty() ? "0.0" : tfCottagePrice.getText());
        totalCost += cottagePrice;
    }

    // Pool price logic (add prices for all selected pool types)
    if (chkPool.isSelected()) {
        double poolPrice = getPoolPrice(); // Total price for selected pool types
        tfPoolPrice.setText(String.format("%.2f", poolPrice));
        totalCost += poolPrice;
    }

            // Spa price
       if (chkSpa.isSelected()) {
        double spaPrice = getSpaPrice();  
        tfSpaPrice.setText(String.format("%.2f", spaPrice));  
        totalCost += spaPrice;
    }

            // Buffet price
        if (chkResto.isSelected()) {
        double buffetPrice = getBuffetPrice(); // Get buffet price
        tfRestoPrice.setText(String.format("%.2f", buffetPrice));  // Display buffet price
        totalCost += buffetPrice;
    }

            // Tour price
        if (chkTour.isSelected()) {
        double tourPrice = getTourPrice(); // Get tour price
        tfTourPrice.setText(String.format("%.2f", tourPrice));  // Display tour price
        totalCost += tourPrice;
    }
        
        tfServicesCost.setText(String.format("%.2f", totalCost)); 
    }
    
        

    public static void main(String[] args) {
        CheckIn checkIn = new CheckIn(); 
        int guestID = checkIn.getGuestID(); 
        new AvailServices(checkIn, guestID);     

    }
}
