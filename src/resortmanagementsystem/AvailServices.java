package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.border.LineBorder;

public class AvailServices extends JFrame {
        
    JComboBox<String> comboCottageType, comboCottageNumber;
    JTextField tfCottagePrice, tfPoolPrice, tfSpaPrice, tfRestoPrice, tfTourPrice, tfServicesCost;
    
    JCheckBox chkCottage, chkPool, chkSpa, chkResto, chkTour, chkKiddie, chkAdult, chkInfinity;
    JButton add, back, chooseCottage;
    Choice ccottage;
     private CheckIn checkIn;
    
    AvailServices(CheckIn checkIn) {
        this.checkIn = checkIn;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);  // Using null layout for custom positioning
        
        JLabel lblServices = new JLabel("Avail Services: ");
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
        comboCottageNumber.setEnabled(isSelected);  // Enable/disable based on checkbox
        tfCottagePrice.setEnabled(isSelected);
        calculateTotalCost();  // Recalculate total cost when service is selected/deselected
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
        
        // ActionListener for updating the cottage price
        comboCottageNumber.addActionListener(e -> {
            String selectedCottage = (String) comboCottageNumber.getSelectedItem();
            if (selectedCottage != null && !selectedCottage.isEmpty()) {
                try {
                    updateCottagePrice(selectedCottage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ItemListener for updating the cottage numbers
        comboCottageType.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) { 
                String selectedType = (String) comboCottageType.getSelectedItem(); 
                if (selectedType != null) {
                    updateCottageNumbers(selectedType); 
                }
            }
        });


        // TextField for Cottage Price
        tfCottagePrice = new JTextField();
        tfCottagePrice.setBounds(420, 60, 150, 30);
        tfCottagePrice.setEnabled(false);
        tfCottagePrice.setEditable(false); // read-only
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
        JLabel lblServicesCost = new JLabel("Total Cost: ");
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

        
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setBounds(150, 300, 100, 30);
        btnConfirm.addActionListener(e -> confirmAction());  // Define the action here
        add(btnConfirm);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(260, 300, 100, 30);
        btnCancel.addActionListener(e -> cancelAction());  // Define the action here
        add(btnCancel);

        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(370, 300, 100, 30);
        btnReset.addActionListener(e -> resetForm());  // Define the action here
        add(btnReset);

        setTitle("Avail Services");
        setBounds(300, 100, 600, 400);  
        setVisible(true);
        setLocationRelativeTo(null);  // Center the window on the screen
        
        
   
    }
    
    private void confirmAction(){
   
    StringBuilder services = new StringBuilder();

    // Cottage
    if (chkCottage.isSelected()) {
        services.append("Cottage Type: ").append(comboCottageType.getSelectedItem()).append(", ");
        services.append("Cottage Number: ").append(comboCottageNumber.getSelectedItem()).append("\n");
    }

    // Pool
    if (chkPool.isSelected()) {
        if (chkKiddie.isSelected()) {
            services.append("Pool: Kiddie\n");
        } else if (chkAdult.isSelected()) {
            services.append("Pool: Adult\n");
        } else if (chkInfinity.isSelected()) {
            services.append("Pool: Infinity\n");
        }
    }

    // Spa
    if (chkSpa.isSelected()) {
        services.append("Spa: Yes\n");
    }

    // Buffet
    if (chkResto.isSelected()) {
        services.append("Buffet: Yes\n");
    }

    // Tour
    if (chkTour.isSelected()) {
        services.append("Tour: Yes\n");
    }

    // Total Price
    services.append("Total Cost: ").append(tfServicesCost.getText());

    checkIn.displayServices(services.toString()); 

    setVisible(false);
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
        comboCottageNumber.setSelectedIndex(0); // Reset to default
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
        System.out.println("Updating details for cottage number: " + cottageNumber); // Debugging: Check if the method is called with the correct room number
        try {
            Conn conn = new Conn();
            String query = "SELECT price FROM cottage WHERE cottagenumber = ?";
            PreparedStatement stmt = conn.c.prepareStatement(query);
            stmt.setString(1, cottageNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String price = rs.getString("price");

                System.out.println("Price: " + price);

              
                tfCottagePrice.setText(price);
            } else {
                // Clear the fields if no room is found
                 System.out.println("Cottage not found, updating text fields...");
                tfCottagePrice.setText("Not Found");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        private void updateCottageNumbers(String roomType) {
        comboCottageNumber.removeAllItems();  

        try {
            Conn conn = new Conn();
            String query = "SELECT cottagenumber FROM cottage WHERE cottage_type = ?";
            PreparedStatement stmt = conn.c.prepareStatement(query);
            stmt.setString(1, roomType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                comboCottageNumber.addItem(rs.getString("cottagenumber")); 
            }

            // If there are rooms, set the first one as selected to update the room details
            if (comboCottageNumber.getItemCount() > 0) {
                updateCottagePrice((String) comboCottageNumber.getSelectedItem());
            } else {
                tfCottagePrice.setText("Not Available");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 }


    private void calculateTotalCost() {
    double totalCost = 0.0;

    // Cottage price
    if (chkCottage.isSelected()) {
        totalCost += Double.parseDouble(tfCottagePrice.getText());  // Add cottage price
        
    }
    
    // Pool price logic
    double poolPrice = 0.0;
    if (chkPool.isSelected()) {
        if (chkKiddie.isSelected()) {
            poolPrice = 200;  // Kiddie pool price
        } else if (chkAdult.isSelected()) {
            poolPrice = 500;  // Adult pool price
        } else if (chkInfinity.isSelected()) {
            poolPrice = 1500;  // Infinity pool price
        }
    }
    tfPoolPrice.setText(String.format("%.2f", poolPrice));  // Display the pool price

    totalCost += poolPrice;  // Add pool price to total cost

    // Spa price
    if (chkSpa.isSelected()) {
        totalCost += 5000.00;  // Spa price
        tfSpaPrice.setText("5000.00");  // Display spa price
    }

    // Buffet price
    if (chkResto.isSelected()) {
        totalCost += 8000.00;  // Buffet price
        tfRestoPrice.setText("8000.00");  // Display buffet price
    }

    // Tour price
    if (chkTour.isSelected()) {
        totalCost += 2000.00;  // Tour price
        tfTourPrice.setText("2000.00");  // Display tour price
    }

    // Update total cost field
    tfServicesCost.setText(String.format("%.2f", totalCost));  // Update the total cost field
}

    

    
    public static void main(String[] args) {
    CheckIn checkIn = new CheckIn(); 
    new AvailServices(checkIn);     

    }
}
