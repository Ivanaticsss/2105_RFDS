
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPools extends JFrame implements ActionListener {
    
    private JButton add, cancel;
    private JTextField tfPoolNumber, tfPrice, tfType;
    private JComboBox availablecombo;
    private JLabel heading, lblPoolNo, lblType, lblAvailable, lblPrice;
    
    AddPools() {
        
        setTitle("Add Pools");
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        heading = new JLabel("Add Pools");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        lblPoolNo = new JLabel("Pool Number");
        lblPoolNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPoolNo.setBounds(60, 80, 120, 20);
        add(lblPoolNo);
        
        tfPoolNumber = new JTextField();
        tfPoolNumber.setBounds(200, 80, 150, 30);
        add(tfPoolNumber);
        
        lblType = new JLabel("Pool Type");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblType.setBounds(60, 130, 120, 20);
        add(lblType);
        
        tfType = new JTextField();
        tfType.setBounds(200, 130, 150, 30);
        add(tfType);
        
        lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 180, 120, 20);
        add(lblAvailable);
        
        String availableOptions[] = {"Available", "Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200, 180, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrice.setBounds(60, 230, 120, 20);
        add(lblPrice);
        
        tfPrice = new JTextField();
        tfPrice.setBounds(200, 230, 150, 30);
        add(tfPrice);
        
        add = new JButton("Add Pool");
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
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/poolbg.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        setBounds(330, 200, 940, 470);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String poolNumber = tfPoolNumber.getText();
        String poolType = tfType.getText();
        String availability = (String) availablecombo.getSelectedItem();
        String price = tfPrice.getText();

        // Validate input fields
        if (poolNumber.isEmpty() || poolType.isEmpty() || price.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt the user for confirmation before adding the pool
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Are you sure you want to add this pool?", 
            "Confirm Addition", 
            JOptionPane.YES_NO_OPTION);

        // If the user selects "Yes", proceed with adding the pool
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn conn = new Conn();
                String str = "insert into pool values('" + poolNumber + "', '" + poolType + "', '" + availability + "', '" + price + "')";

                conn.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "New Pool Added Successfully");

                // Clear fields for the next entry
                tfPoolNumber.setText("");
                tfType.setText("");
                tfPrice.setText("");
                availablecombo.setSelectedIndex(0);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // If the user selects "No", do nothing (pool won't be added)
    } else {
        setVisible(false);
    }
}

    
    public static void main(String[] args) {
        new AddPools();
    }
}
