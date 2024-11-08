
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Reception extends JFrame implements ActionListener{
    JButton newGuest, rooms, service, cottages, pools, guestInfo, searchRoom, update, roomStatus;
    
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
        
        cottages = new JButton("Cottages");
        cottages.setBounds(10, 110, 200, 30);
        cottages.setBackground(Color.decode("#2a1c13"));
        cottages.setForeground(Color.WHITE);
        cottages.addActionListener(this);
        add(cottages);
        
        //10, 150, 200, 30
        //    190
        //Services, Customer INfo, Manager INfo, Check-out, Update Status, Search Room, Log out
        
        pools = new JButton("Pools");
        pools.setBounds(10, 150, 200, 30);
        pools.setBackground(Color.decode("#2a1c13"));
        pools.setForeground(Color.WHITE);
        pools.addActionListener(this);
        add(pools);
        
        service = new JButton("Services");
        service.setBounds(10, 190, 200, 30);
        service.setBackground(Color.decode("#2a1c13"));
        service.setForeground(Color.WHITE);
        service.addActionListener(this);
        add(service);
        
        guestInfo = new JButton("Guest Info");
        guestInfo.setBounds(10, 230, 200, 30);
        guestInfo.setBackground(Color.decode("#2a1c13"));
        guestInfo.setForeground(Color.WHITE);
        guestInfo.addActionListener(this);
        add(guestInfo);
        
        JButton checkout = new JButton("Check Out");
        checkout.setBounds(10, 270, 200, 30);
        checkout.setBackground(Color.decode("#2a1c13"));
        checkout.setForeground(Color.WHITE);
        add(checkout);
        
        update  = new JButton("Update Status");
        update.setBounds(10, 310, 200, 30);
        update.setBackground(Color.decode("#2a1c13"));;
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        roomStatus = new JButton("Update Room Status");
        roomStatus.setBounds(10, 350, 200, 30);
        roomStatus.setBackground(Color.decode("#2a1c13"));
        roomStatus.setForeground(Color.WHITE);
        roomStatus.addActionListener(this);
        add(roomStatus);
        
        searchRoom = new JButton("Search Room");
        searchRoom.setBounds(10, 390, 200, 30);
        searchRoom.setBackground(Color.decode("#2a1c13"));
        searchRoom.setForeground(Color.WHITE);
        searchRoom.addActionListener(this);
        add(searchRoom);
        
        JButton logout = new JButton("Log Out");
        logout.setBounds(10, 430, 200, 30);
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
        else if(ae.getSource() == cottages){
            setVisible(false);
            new Cottage();
        }
        else if(ae.getSource() == pools){
            setVisible(false);
            new Pool();
        }
        else if(ae.getSource() == newGuest){
            setVisible(false);
            new AddGuest();
        }
        else if(ae.getSource() == guestInfo){
            setVisible(false);
            new GuestInfo();
        }
        else if(ae.getSource() == searchRoom){
            setVisible(false);
            new SearchRoom();
        }
        else if(ae.getSource() == update){
            setVisible(false);
            new UpdateCheck();
        }
        else if(ae.getSource() == roomStatus){
            setVisible(false);
            new UpdateRoom();
        }
    }
    
    public static void main(String[] args){
        new Reception();
    }
    
}
