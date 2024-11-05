
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Reception extends JFrame implements ActionListener{
    JButton newGuest, rooms, service;
    
    Reception(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        newGuest = new JButton("New Guest Form");
        newGuest.setBounds(10, 30, 200, 30);
        newGuest.setBackground(Color.decode("#2a1c13"));
        newGuest.setForeground(Color.WHITE);
        add(newGuest);
        
        rooms = new JButton("Rooms");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.decode("#2a1c13"));
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        add(rooms);
        
        service = new JButton("Service");
        service.setBounds(10, 110, 200, 30);
        service.setBackground(Color.decode("#2a1c13"));
        service.setForeground(Color.WHITE);
        service.addActionListener(this);
        add(service);
        
        //10, 150, 200, 30
        //    190
        
        JButton customers = new JButton("Customer Info");
        customers.setBounds(10, 150, 200, 30);
        customers.setBackground(Color.decode("#2a1c13"));
        customers.setForeground(Color.WHITE);
        add(customers);
        
        JButton managerInfo = new JButton("Manager Info");
        managerInfo.setBounds(10, 190, 200, 30);
        managerInfo.setBackground(Color.decode("#2a1c13"));
        managerInfo.setForeground(Color.WHITE);
        add(managerInfo);
        
        JButton checkout = new JButton("Check-Out");
        checkout.setBounds(10, 230, 200, 30);
        checkout.setBackground(Color.decode("#2a1c13"));
        checkout.setForeground(Color.WHITE);
        add(checkout);
        
        JButton update = new JButton("Update Status");
        update.setBounds(10, 270, 200, 30);
        update.setBackground(Color.decode("#2a1c13"));
        update.setForeground(Color.WHITE);
        add(update);
        
        JButton searchRoom = new JButton("Search Room");
        searchRoom.setBounds(10, 310, 200, 30);
        searchRoom.setBackground(Color.decode("#2a1c13"));;
        searchRoom.setForeground(Color.WHITE);
        add(searchRoom);
        
        JButton logout = new JButton("Log Out");
        logout.setBounds(10, 350, 200, 30);
        logout.setBackground(Color.decode("#2a1c13"));
        logout.setForeground(Color.WHITE);
        add(logout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourthbg.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        add(image);
        
        
        setBounds(350, 200, 800, 570);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == rooms){
            setVisible(false);
            new Room();
        }
        else if(ae.getSource() == service){
            setVisible(false);
            new Service();
        }
    }
    
    public static void main(String[] args){
        new Reception();
    }
    
}
