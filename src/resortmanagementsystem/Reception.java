
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;


public class Reception extends JFrame{
    
    Reception(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JButton newGuest = new JButton("New Guest Form");
        newGuest.setBounds(10, 30, 200, 30);
        newGuest.setBackground(Color.decode("#2a1c13"));
        newGuest.setForeground(Color.WHITE);
        add(newGuest);
        
        JButton rooms = new JButton("Rooms");
        rooms.setBounds(10, 70, 200, 30);
        rooms.setBackground(Color.decode("#2a1c13"));
        rooms.setForeground(Color.WHITE);
        add(rooms);
        
        JButton department = new JButton("Department");
        department.setBounds(10, 110, 200, 30);
        department.setBackground(Color.decode("#2a1c13"));
        department.setForeground(Color.WHITE);
        add(department);
        
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
    
    public static void main(String[] args){
        new Reception();
    }
    
}
