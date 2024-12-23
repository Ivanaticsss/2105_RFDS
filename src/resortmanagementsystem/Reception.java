package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Reception extends JFrame implements ActionListener{
    JButton newGuest, rooms, service, cottages, pools, guestInfo, searchRoom, update, roomStatus, checkout, logout;
    JPopupMenu roomsMenu, poolsMenu, cottagesMenu, guestMenu;
    
    Reception(){
        setTitle("Reception");
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        newGuest = new JButton("New Guest Form");
        newGuest.setBounds(10, 50, 230, 40);
        newGuest.setBackground(Color.decode("#2a1c13"));
        newGuest.setForeground(Color.WHITE);
        newGuest.addActionListener(this);
        add(newGuest);

        guestMenu = new JPopupMenu();
        guestMenu.setBackground(new Color(204, 153, 102));

        JMenuItem checkIn = new JMenuItem("Check-In Form");
        JMenuItem reservation = new JMenuItem("Reservation Form");

        checkIn.addActionListener(this);
        reservation.addActionListener(this);

        guestMenu.add(checkIn);
        guestMenu.add(reservation);

        // Set preferred size to match the button width
        guestMenu.setPreferredSize(new Dimension(newGuest.getWidth(), 50));
        
        //pop up menu for Rooms button
        rooms = new JButton("Rooms");
        rooms.setBounds(10, 100, 230, 40);
        rooms.setBackground(Color.decode("#2a1c13"));
        rooms.setForeground(Color.WHITE);
        rooms.addActionListener(this);
        add(rooms);
        
        roomsMenu = new JPopupMenu();
        roomsMenu.setBackground(new Color(204, 153, 102));
        
        JMenuItem searchRooms = new JMenuItem("Search Rooms");
        JMenuItem updateRoomStatus = new JMenuItem("Update Room Status");
        searchRooms.addActionListener(this);
        updateRoomStatus.addActionListener(this);
        roomsMenu.add(searchRooms);
        roomsMenu.add(updateRoomStatus);
        
        // Set width of menu to match button
        roomsMenu.setPreferredSize(new Dimension(rooms.getWidth(), roomsMenu.getPreferredSize().height));  
       
        
        // Cottages button 
        cottages = new JButton("Cottages");
        cottages.setBounds(10, 150, 230, 40);
        cottages.setBackground(Color.decode("#2a1c13"));
        cottages.setForeground(Color.WHITE);
        cottages.addActionListener(this);
        add(cottages);
        
        // Pools button 
        pools = new JButton("Pools");
        pools.setBounds(10, 200, 230, 40);
        pools.setBackground(Color.decode("#2a1c13"));
        pools.setForeground(Color.WHITE);
        pools.addActionListener(this);
        add(pools);
        
        service = new JButton("Services");
        service.setBounds(10, 250, 230, 40);
        service.setBackground(Color.decode("#2a1c13"));
        service.setForeground(Color.WHITE);
        service.addActionListener(this);
        add(service);
        
        guestInfo = new JButton("Guest Info");
        guestInfo.setBounds(10, 300, 230, 40);
        guestInfo.setBackground(Color.decode("#2a1c13"));
        guestInfo.setForeground(Color.WHITE);
        guestInfo.addActionListener(this);
        add(guestInfo);
        
        checkout = new JButton("Check Out");
        checkout.setBounds(10, 350, 230, 40);
        checkout.setBackground(Color.decode("#2a1c13"));
        checkout.setForeground(Color.WHITE);
        checkout.addActionListener(this);
        add(checkout);
        
        update  = new JButton("Update Status");
        update.setBounds(10, 400, 230, 40);
        update.setBackground(Color.decode("#2a1c13"));;
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);
        
        logout = new JButton("Log Out");
        logout.setBounds(10, 450, 230, 40);
        logout.setBackground(Color.decode("#2a1c13"));
        logout.setForeground(Color.WHITE);
        logout.addActionListener(this);
        add(logout);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourthbg.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        add(image);
        
        setBounds(350, 200, 800, 570);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == newGuest) {
        guestMenu.show(newGuest, 0, newGuest.getHeight());
    } else if (ae.getSource() == rooms) {
        roomsMenu.show(rooms, 0, rooms.getHeight());
    } else if (ae.getActionCommand().equals("Check-In Form")) {
        setVisible(false);
        new CheckIn();
    } else if (ae.getActionCommand().equals("Reservation Form")) {
        setVisible(false);
        new Reservation1();
    } else if (ae.getActionCommand().equals("Search Rooms")) {
        setVisible(false);
        new SearchRoom();
    } else if (ae.getActionCommand().equals("Update Room Status")) {
        setVisible(false);
        new UpdateRoom();
    } else if (ae.getActionCommand().equals("Cottages")) {
        setVisible(false);
        new SearchCottages();
    } else if (ae.getActionCommand().equals("Pools")) {
        setVisible(false);
        new Pool();
    } else if (ae.getSource() == guestInfo) {
        setVisible(false);
        new GuestInfo();
    } else if (ae.getSource() == update) {
        setVisible(false);
        new UpdateCheck();
    } else if (ae.getSource() == checkout) {
        setVisible(false);
        new CheckOut();
    } else if (ae.getSource() == logout) {
        // Prompt the user to confirm logout
        int confirm = JOptionPane.showConfirmDialog(null, 
                                                    "Are you sure you want to log out?", 
                                                    "Confirm Logout", 
                                                    JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // If confirmed, exit the application
            setVisible(false);
            System.exit(0);
        } else {
            // If not confirmed, show a message and stay on the current screen
            JOptionPane.showMessageDialog(null, "Logout canceled");
        }
    } else if (ae.getSource() == service) {
        setVisible(false);
        new Service();
    }
}

    
    public static void main(String[] args){
        new Reception();
    }
    
}
