package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//ivan & danah

public class AddRooms extends JFrame implements ActionListener{
    
    JButton add, cancel;
    JTextField tfroom, tfprice;
    JComboBox typecombo, availablecombo;
    
    AddRooms(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroomno.setBounds(60, 80, 120, 20);
        add(lblroomno);
        
        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);
        
        JLabel lblavailavle = new JLabel("Available");
        lblavailavle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailavle.setBounds(60, 130, 120, 20);
        add(lblavailavle);
        
        String availableOption[] = {"Available", "Occupied"};
        availablecombo = new JComboBox(availableOption);
        availablecombo.setBounds(200, 130, 150, 30);
        availablecombo.setBackground(Color.WHITE);
        add(availablecombo);
        
        /*JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblclean.setBounds(60, 180, 120, 20);
        add(lblclean);
        
        String cleanOption[] = {"Cleaned", "Dirty"};
        cleancombo = new JComboBox(cleanOption);
        cleancombo.setBounds(200, 180, 150, 30);
        cleancombo.setBackground(Color.WHITE);
        add(cleancombo); */
        
        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblprice.setBounds(60, 180, 120, 20);
        add(lblprice);
        
        tfprice = new JTextField();
        tfprice.setBounds(200, 180, 150, 30);
        add(tfprice);
        
        JLabel lbltype = new JLabel("Bed Type");
        lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbltype.setBounds(60, 230, 120, 20);
        add(lbltype);
        
        String typeOption[] = {"Single Bed", "Double Bed", "Queen Bed", "King Bed"};
        typecombo = new JComboBox(typeOption);
        typecombo.setBounds(200, 230, 150, 30);
        typecombo.setBackground(Color.WHITE);
        add(typecombo);
        
        add = new JButton("Add Room");
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
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/roombg.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);
        
        
        setBounds(330, 200, 940, 470);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == add){
            String roomnumber = tfroom.getText();
            String availability = (String) availablecombo.getSelectedItem();
            //String status = (String) cleancombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) typecombo.getSelectedItem();
            
             // Validate required fields
        if (roomnumber.isEmpty() || price.isEmpty() || availability == null || type == null) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if any field is empty
        }
            
            try{
                Conn conn = new Conn();
                String str = "insert into room values('"+roomnumber+"', '"+availability+"', '"+price+"', '"+type+"')";
                
                conn.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "New Room Added succesfully");
                
                 // Clear fields for the next entry
                tfroom.setText("");
                tfprice.setText("");
                availablecombo.setSelectedIndex(0);
                typecombo.setSelectedIndex(0);
        
                //setVisible(false);
            } catch(Exception e){
                e.printStackTrace();
            }
           
            
        } else{
            //setVisible(false);
            dispose();
        }
    }
    
    public static void main(String[] args){
        new AddRooms();
        
    }
    
}
