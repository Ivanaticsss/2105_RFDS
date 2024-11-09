package resortmanagementsystem;

/**
 *
 * @author Valiente
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddGuest extends JFrame {
    AddGuest() {
        
        JComboBox<String> comboid;
        JTextField tfnumber, tfname, tfdeposit;
        JRadioButton rmale, rfemale;
        Choice croom;
        JLabel checkintime;
        JButton add, back;
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("NEW GUEST FORM");
        text.setBounds(80, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 22));
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setBounds(45, 160, 150, 25);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblid);
        
        String options[] = {"Driver's License", "SSS ID", "Pag-ibig ID", "Voter's ID", "National ID", "Company ID", "Student's ID", "Passport/Visa"};
        comboid = new JComboBox<>(options);
        comboid.setBounds(200, 160, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(45, 120, 150, 25);
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblnumber);
        
        tfnumber = new JTextField("Mobile Number");
        tfnumber.setBounds(200, 120, 150, 25);
        tfnumber.setForeground(Color.GRAY);
        
        tfnumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfnumber.getText().equals("Mobile Number")) {
                    tfnumber.setText("");
                    tfnumber.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfnumber.getText().isEmpty()) {
                    tfnumber.setText("Mobile Number");
                    tfnumber.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfnumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(45, 80, 300, 30);
        lblname.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblname);
        
        tfname = new JTextField("Enter Name");
        tfname.setBounds(200, 80, 150, 25);
        tfname.setForeground(Color.GRAY);
        
        tfname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfname.getText().equals("Enter Name")) {
                    tfname.setText("");
                    tfname.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfname.getText().isEmpty()) {
                    tfname.setText("Enter Name");
                    tfname.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfname);
        
        JLabel lblgender = new JLabel("Sex");
        lblgender.setBounds(45, 190, 300, 30);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblgender);
        
        rmale = new JRadioButton("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 195, 70, 25);
        
        rfemale = new JRadioButton("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(270, 195, 150, 25);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rmale);
        sexGroup.add(rfemale);
        
        add(rmale);
        add(rfemale);
        
        JLabel lblroom = new JLabel("Room No.");
        lblroom.setBounds(45, 225, 100, 30);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblroom);
        
        croom = new Choice();
        
        try {
            Conn conn = new Conn();
            String query = "select * from room";
            ResultSet rs = conn.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        croom.setBounds(200, 225, 150, 25);
        add(croom);
        
        JLabel lbltime = new JLabel("<html>Check-in<br>time</html>");
        lbltime.setBounds(45, 255, 150, 60);
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lbltime);
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm");
        String formattedDate = formatter.format(date);

        checkintime = new JLabel(formattedDate);
        checkintime.setBounds(200, 265, 300, 30);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 17));
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("<html>Reservation<br>Fee</html>");
        lbldeposit.setBounds(45, 325, 150, 40);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lbldeposit);
        
        tfdeposit = new JTextField("Enter Amount");
        tfdeposit.setBounds(200, 325, 150, 25);
        tfdeposit.setForeground(Color.GRAY);
        
         tfdeposit.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfdeposit.getText().equals("Enter Amount")) {
                    tfdeposit.setText("");
                    tfdeposit.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfdeposit.getText().isEmpty()) {
                    tfdeposit.setText("Enter Amount");
                    tfdeposit.setForeground(Color.GRAY);
                }
            }
        });
        
        add(tfdeposit);
        
        add = new JButton ("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds (250, 410, 120, 25);
        add (add);
        
        back = new JButton ("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds (400, 410, 120, 25);
        add (back);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth,jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400,50,300,400);
        add(image);
        
        setBounds(300, 100, 800, 550);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new AddGuest();
    }
}
