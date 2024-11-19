
package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;  //rs2xml.jar
import java.util.HashSet;
import javax.swing.border.LineBorder;

/**
 *
 * @author Danah Paris
 */
public class SearchPool extends JFrame implements ActionListener{
    
    JTable table;
    JButton back, submit;
     JComboBox<String> poolType;
    JCheckBox available;
    
    SearchPool(){
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
      
        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma",  Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);
        
        
        JLabel lblbed = new JLabel ("Pool Type");
        lblbed.setBounds(50, 100, 100, 20);
        add(lblbed);
        
        
        poolType = new JComboBox<>(new String[]{""});
        poolType.setBounds(150, 100, 150, 25);
        poolType.setBackground(Color.white);
        add(poolType);

        // Populate poolType with options from database
        populatePoolTypes();
        
        available = new JCheckBox("Only display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.white);
        add(available);
        
    
        
        JLabel j1 = new JLabel("Pool Number");
        j1.setBounds(50, 160, 100, 20);
        add(j1);
        
        JLabel j2 = new JLabel("Availability");
        j2.setBounds(290, 160, 100, 20);
        add(j2);
        
        JLabel j3 = new JLabel("Price");
        j3.setBounds(540, 160, 100, 20);
        add(j3);
        
        JLabel j4 = new JLabel("Pool Type");
        j4.setBounds(800, 160, 100, 20);
        add(j4);
        
        table = new JTable();
        table.setBounds(0, 200, 1000, 300);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from pool");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBackground(Color.decode("#9c6644"));
        submit.setForeground(Color.WHITE);
        submit.setBounds(300, 100, 120, 25);  
        submit.setFont(new Font("Helvetica", Font.BOLD, 14));  
        submit.setBorder(new LineBorder(Color.decode("#9c6644"), 2, true));  
        submit.setFocusPainted(false); 
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        submit.addActionListener(this);
       
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                submit.setBackground(Color.decode("#a17d58")); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                submit.setBackground(Color.decode("#9c6644")); 
            }
        });

        add(submit);
        
        back = new JButton("Back");
        back.setBackground(Color.decode("#2a1c13"));
        back.setForeground(Color.WHITE);
        back.setBounds(400, 510, 120, 30);
        back.setFont(new Font("Helvetica", Font.BOLD, 14));  
        back.setBorder(new LineBorder(Color.decode("#2a1c13"), 2, true));  
        back.setFocusPainted(false); 
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));  
        back.addActionListener(this);

        // Hover effect for the "Back" button
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setBackground(Color.decode("#3e2a1f"));  
            }
            @Override
            public void mouseExited(MouseEvent e) {
                back.setBackground(Color.decode("#2a1c13")); 
            }
        });

        add(back);
        
        setBounds(300, 200, 1000, 600);
        setVisible(true);
        }
    
     private void populatePoolTypes() {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT DISTINCT pool_type FROM pool");
            HashSet<String> poolTypes = new HashSet<>();
            while (rs.next()) {
                String type = rs.getString("pool_type");
                poolTypes.add(type);
            }
            for (String type : poolTypes) {
                poolType.addItem(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            try{
                String query1 = "select * from pool where pool_type = '"+poolType.getSelectedItem()+"'";
                String query2 = "select * from pool where availability = 'Available' AND pool_type = '"+poolType.getSelectedItem()+"'";
                
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
            new SearchPool();
    }
    
    
}
