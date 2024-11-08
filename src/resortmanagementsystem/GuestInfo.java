
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;  //rs2xml.jar

/**
 *
 * @author Danah Paris
 */
public class GuestInfo extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    
    GuestInfo(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 =  i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        add(image);
        
        
        JLabel j1 = new JLabel("Name");
        j1.setBounds(10, 10, 100, 20);
        add(j1);
        
        JLabel j2 = new JLabel("Number");
        j2.setBounds(160, 10, 100, 20);
        add(j2);
        
        JLabel j3 = new JLabel("ID");
        j3.setBounds(290, 10, 100, 20);
        add(j3);
        
        JLabel j4 = new JLabel("Sex");
        j4.setBounds(410, 10, 100, 20);
        add(j4);
        
        JLabel j5 = new JLabel("Room Number");
        j5.setBounds(540, 10, 100, 20);
        add(j5);
        
        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setBounds(200, 500, 120, 30);
        add(back);
        
        
        
        setBounds(300, 200, 1050, 600);
        setVisible(true);
        }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new Reception();
    }
    
    public static void main(String[] args){
            new GuestInfo();
    }
    
    
}
