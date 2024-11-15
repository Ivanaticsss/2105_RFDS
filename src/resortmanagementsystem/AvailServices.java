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
        comboCottageNumber.setEnabled(isSelected);
        tfCottagePrice.setEnabled(isSelected);

        if (isSelected) {
            // Automatically update the cottage numbers and price when selected
            String selectedType = (String) comboCottageType.getSelectedItem();
            updateCottageNumbers(selectedType); // Load cottage numbers based on selected type
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
                    updateCottageNumbers(selectedType); 
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
    checkIn.updateTotalCost();

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
            
         if (chkCottage.isSelected()) {
        double cottagePrice = Double.parseDouble(tfCottagePrice.getText().isEmpty() ? "0.0" : tfCottagePrice.getText());
        totalCost += cottagePrice;
    }
            // Pool price logic
            if (chkPool.isSelected()) {
        if (chkKiddie.isSelected()) {
            tfPoolPrice.setText("200.00");
            totalCost += Double.parseDouble(tfPoolPrice.getText());
        }
        if (chkAdult.isSelected()) {
            tfPoolPrice.setText("500.00");
            totalCost += Double.parseDouble(tfPoolPrice.getText());
        }
        if (chkInfinity.isSelected()) {
            tfPoolPrice.setText("1500.00");
            totalCost += Double.parseDouble(tfPoolPrice.getText());
        }
    }

            // Spa price
            if (chkSpa.isSelected()) {
                tfSpaPrice.setText("5000.00");  // Display spa price
                totalCost += Double.parseDouble(tfSpaPrice.getText());
            }

            // Buffet price
            if (chkResto.isSelected()) {
                
                tfRestoPrice.setText("8000.00");  // Display buffet price
                totalCost += Double.parseDouble(tfRestoPrice.getText());
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
