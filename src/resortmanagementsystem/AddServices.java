package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//ivan & danah

public class AddServices extends JFrame implements ActionListener{
    
    private JButton add, cancel;
    private JTextField tfservice, tfbudget;
    private JLabel heading, lblservice, lblbudget;
    
    
    AddServices(){
        
        setTitle("Add Services");
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        heading = new JLabel("Add Services");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);
        
        lblservice = new JLabel("Service");
        lblservice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblservice.setBounds(60, 80, 120, 20);
        add(lblservice);
        
        tfservice = new JTextField();
        tfservice.setBounds(200, 80, 150, 30);
        add(tfservice);
        
   
        lblbudget = new JLabel("Budget");
        lblbudget.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblbudget.setBounds(60, 130, 120, 20);
        add(lblbudget);
        
        tfbudget = new JTextField();
        tfbudget.setBounds(200, 130, 150, 30);
        add(tfbudget);
        
        
        add = new JButton("Add Service");
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
            String serviceName = tfservice.getText();
            
            String budget = tfbudget.getText();
            
             // Validate required fields
        if (serviceName.isEmpty() || budget.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
            
            try{
                Conn conn = new Conn();
                String str = "insert into service values('"+serviceName+"', '"+budget+"')";
                
                conn.s.executeUpdate(str);
                
                JOptionPane.showMessageDialog(null, "New Service Added succesfully");
                
                 // Clear fields for the next entry
                tfservice.setText("");
                tfbudget.setText("");
                
        
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
        new AddServices();
        
    }
    
}
