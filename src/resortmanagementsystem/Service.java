
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
public class Service extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    
    Service(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 =  i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        add(image);
        
        
        JLabel j1 = new JLabel("Service");
        j1.setBounds(10, 10, 100, 20);
        add(j1);
        
        JLabel j2 = new JLabel("Budget");
        j2.setBounds(250, 10, 100, 20);
        add(j2);
        
        
        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from service");
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
            new Service();
    }
    
    
}
