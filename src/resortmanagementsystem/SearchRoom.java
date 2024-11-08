
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
public class SearchRoom extends JFrame implements ActionListener{
    
    JTable table;
    JButton back, submit;
    JComboBox bedType;
    JCheckBox available;
    
    SearchRoom(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
      
        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma",  Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        
        JLabel lblbed = new JLabel ("Bed Type");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);
        
        
        bedType = new JComboBox(new String[]{"Single Bed", "Double Bed", "Queen Bed", "King Bed"});
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.white);
        add(bedType);
        
        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.white);
        add(available);
        
    
        
        JLabel j1 = new JLabel("Room Number");
        j1.setBounds(50, 160, 100, 20);
        add(j1);
        
        JLabel j2 = new JLabel("Availability");
        j2.setBounds(290, 160, 100, 20);
        add(j2);
        
        JLabel j3 = new JLabel("Price");
        j3.setBounds(540, 160, 100, 20);
        add(j3);
        
        JLabel j4 = new JLabel("Bed Type");
        j4.setBounds(800, 160, 100, 20);
        add(j4);
        
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        submit.setBounds(300, 510, 120, 30);
        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        back.setBounds(500, 510, 120, 30);
        add(back);
        
        setBounds(300, 200, 1000, 600);
        setVisible(true);
        }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            try{
                String query1 = "select * from room where bed_type = '"+bedType.getSelectedItem()+"'";
                String query2 = "select * from room where availability = 'Available' AND bed_type = '"+bedType.getSelectedItem()+"'";
                
                Conn conn = new Conn();
                ResultSet rs;
                if(available.isSelected()){
                    rs = conn.s.executeQuery(query2);
                } 
                else{
                     rs = conn.s.executeQuery(query1);
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));
                
                
                
                
            }
            catch (Exception e){
                e.printStackTrace();
            }
            
        }
        else{
        setVisible(false);
        new Reception();
        }
    }
    
    public static void main(String[] args){
            new SearchRoom();
    }
    
    
}
