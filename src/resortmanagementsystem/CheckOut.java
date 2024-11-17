
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class CheckOut extends JFrame implements ActionListener{
    
    Choice guest;
    JLabel lvlroomnumber, lvlcheckintime, lvlcheckouttime;
    JButton checkOut, back;
    CheckOut(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("Check Out");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tamoha", Font.PLAIN, 20));
        add(text);
        
        JLabel lblid = new JLabel("Guest ID");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);
        
        guest = new Choice();
        guest.setBounds(150, 80, 150, 25);
        add(guest);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel tick = new JLabel(i3);
        tick.setBounds(310, 80, 20, 20);
        add(tick);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);
        
        lvlroomnumber = new JLabel();
        lvlroomnumber.setBounds(150, 130, 100, 30);
        add(lvlroomnumber);
        
        JLabel lblcheckin = new JLabel("Check-In Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);
        
        lvlcheckintime = new JLabel();
        lvlcheckintime.setBounds(150, 180, 100, 30);
        add(lvlcheckintime);
        
        JLabel lblcheckout = new JLabel("Check-Out Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);
        
        Date date = new Date();
        lvlcheckouttime = new JLabel("" + date);
        lvlcheckouttime.setBounds(150, 2300, 150, 30);
        add(lvlcheckouttime);
        
        checkOut = new JButton("Check Out");
        checkOut.setBackground(Color.BLACK);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBounds(30, 280, 120, 30);
        checkOut.addActionListener(this);
        add(checkOut);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from guest");
            while(rs.next()){
                guest.add(rs.getString("guestID"));
                lvlroomnumber.setText(rs.getString("room"));
                lvlcheckintime.setText(rs.getString("checkintime"));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(350, 50, 400, 250);
        add(image);
        
        
        
        setBounds(300, 200, 800, 400);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == checkOut){
            String query1 = "delete from guest where guestID = '"+ guest.getSelectedItem()+"'";
            String query2 = "update room set availability = 'Available' where roomnumber = '"+lvlroomnumber.getText()+"'";
            
            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "Check out done");
                
                setVisible(false);
                new Reception();
            
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        else {
            setVisible(false);
            new Reception();
                 
        }
    }
    public static void main(String[] args){
        new CheckOut();
    
    }
    
}
