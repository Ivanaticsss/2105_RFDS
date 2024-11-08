
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddPools extends JFrame implements ActionListener {
    
    JButton add, cancel;
    JTextField tfPoolNumber, tfPrice, tfSize;
    JComboBox availablecombo;
    
    AddPools() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Pools");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        JLabel lblPoolNo = new JLabel("Pool Number");
        lblPoolNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPoolNo.setBounds(60, 80, 120, 20);
        add(lblPoolNo);
        
        tfPoolNumber = new JTextField();
        tfPoolNumber.setBounds(200, 80, 150, 30);
        add(tfPoolNumber);
        
        JLabel lblSize = new JLabel("Pool Type");
        lblSize.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSize.setBounds(60, 130, 120, 20);
        add(lblSize);
        
        tfSize = new JTextField();
        tfSize.setBounds(200, 130, 150, 30);
        add(tfSize);
        
        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 180, 120, 20);
        add(lblAvailable);
        
        String availableOptions[] = {"Available", "Occupied"};
        availablecombo = new JComboBox(availableOptions);
        availablecombo.setBounds(200, 180, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        JLabel lblPrice = new JLabel("Price");
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
            String poolSize = tfSize.getText();
            String availability = (String) availablecombo.getSelectedItem();
            String price = tfPrice.getText();
            
            // Validate input fields
            if (poolNumber.isEmpty() || poolSize.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            try {
                Conn conn = new Conn();
                String str = "insert into pool values('" + poolNumber + "', '" + poolSize + "', '" + availability + "', '" + price + "')";
                
                conn.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "New Pool Added Successfully");
                
                 // Clear fields for the next entry
                tfPoolNumber.setText("");
                tfSize.setText("");
                tfPrice.setText("");
                availablecombo.setSelectedIndex(0);
               
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new AddPools();
    }
}
