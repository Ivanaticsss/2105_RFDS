
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
public class Cottage extends JFrame implements ActionListener{
    
    JTable table;
    JButton back;
    
    Cottage(){
        
        setTitle("Cottages");
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eight.jpg"));
        Image i2 =  i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(500, 0, 600, 600);
        add(image);
        
        
        JLabel j1 = new JLabel("Cottage Number");
        j1.setBounds(10, 10, 100, 20);
        add(j1);
        
        JLabel j2 = new JLabel("Availability");
        j2.setBounds(120, 10, 100, 20);
        add(j2);
        
        JLabel j3 = new JLabel("Price");
        j3.setBounds(250, 10, 100, 20);
        add(j3);
        
        JLabel j4 = new JLabel("Cottage Type");
        j4.setBounds(375, 10, 100, 20);
        add(j4);
        
        table = new JTable();
        table.setBounds(0, 40, 500, 400);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from cottage");
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
            new Cottage();
    }
    
    
}
