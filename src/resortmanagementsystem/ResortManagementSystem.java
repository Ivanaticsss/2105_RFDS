//try editing
//rename
package resortmanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ResortManagementSystem extends JFrame implements ActionListener{
    
    ResortManagementSystem(){
        setTitle("Serenity Cove Resort Front Desk");
        //setSize(1366, 565);
        //setLocation(100,100);
        setBounds(100,100,1366,565);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/firstHome.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 565); //locationx, locationy, length, breadth
        add(image);
        
        JLabel text = new JLabel("RESORT FRONT DESK SYSTEM");
        text.setBounds(20, 430,1000,90);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("serif", Font.PLAIN, 50));
        image.add(text);
        
        JButton next = new JButton("Next");
        next.setBounds(1150, 450, 150, 50);
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.addActionListener(this);
        next.setFont(new Font("serif", Font.PLAIN, 24));
        image.add(next);
        
        setVisible(true);
        
        while(true){
            text.setVisible(false);
            try{
                Thread.sleep(500);
                
            }catch(Exception e){
                e.printStackTrace();
            }
            text.setVisible(true);
            try{
                Thread.sleep(500);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }   
    }

    public void actionPerformed(ActionEvent ae){
        dispose(); // Close the ResortManagementSystem JFrame
        new Login();
        
    }
    
    public static void main(String[] args) {
        new ResortManagementSystem();
        
    }
    
}
