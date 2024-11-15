
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCottages extends JFrame implements ActionListener {
    
    JButton add, cancel;
    JTextField tfCottageNumber, tfPrice;
    JComboBox typecombo, availablecombo;
    
    AddCottages() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Cottages");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        JLabel lblCottageNo = new JLabel("Cottage Number");
        lblCottageNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCottageNo.setBounds(60, 80, 120, 20);
        add(lblCottageNo);
        
        tfCottageNumber = new JTextField();
        tfCottageNumber.setBounds(200, 80, 150, 30);
        add(tfCottageNumber);
        
        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 130, 120, 20);
        add(lblAvailable);
        
        String availableOptions[] = {"Available", "Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200, 130, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrice.setBounds(60, 180, 120, 20);
        add(lblPrice);
        
        tfPrice = new JTextField();
        tfPrice.setBounds(200, 180, 150, 30);
        add(tfPrice);
        
        JLabel lblType = new JLabel("Cottage Type");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblType.setBounds(60, 230, 120, 20);
        add(lblType);
        
        String typeOptions[] = {"Standard", "Deluxe", "VIP"};
        typecombo = new JComboBox(typeOptions);
        typecombo.setBounds(200, 230, 150, 30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);
        
        add = new JButton("Add Cottage");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60, 350, 130, 30);
        add.addActionListener(this);
        add(add);
        
        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220, 350, 130, 30);
        cancel.addActionListener(this);
        add(cancel);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/cottagebg.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        setBounds(330, 200, 940, 470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String cottageNumber = tfCottageNumber.getText();
        String availability = (String) availablecombo.getSelectedItem();
        String priceText = tfPrice.getText();
        String type = (String) typecombo.getSelectedItem();

        // Validate input fields
        if (cottageNumber.isEmpty() || priceText.isEmpty() || availability == null || type == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if any field is empty
        }

        try {
            // Parse the price as a double
            double price = Double.parseDouble(priceText);

            // Establish connection and execute query
            Conn conn = new Conn();
            String str = "INSERT INTO cottage (cottageNumber, availability, price, cottage_type) VALUES ('"
                         + cottageNumber + "', '" + availability + "', " + price + ", '" + type + "')";
            
            conn.s.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "New Cottage Added Successfully");

            // Clear fields for the next entry
            tfCottageNumber.setText("");
            tfPrice.setText("");
            availablecombo.setSelectedIndex(0);
            typecombo.setSelectedIndex(0);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric value for price.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        setVisible(false);
    }
}

    
    public static void main(String[] args) {
        new AddCottages();
    }
}

