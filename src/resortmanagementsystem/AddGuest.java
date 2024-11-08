package resortmanagementsystem;

/**
 *
 * @author Valiente, Theresa
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddGuest extends JFrame {
    AddGuest(){
        
        JComboBox comboid;
        JTextField tfnumber, tfname;
        JRadioButton rmale, rfemale;
        Choice croom;
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel ("NEW GUEST FORM");
        text.setBounds(50, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 24));
        add(text);
        
        JLabel lblid = new JLabel ("ID");
        lblid.setBounds(100, 160, 150, 25);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblid);
        
        String options[] = {"Driver's License", "SSS ID", "Pag-ibig ID", "Voter's ID", "National ID", "Company ID", "Student's ID", "Passport/Visa"};
        comboid = new JComboBox(options);
        comboid.setBounds(200, 160, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel ("Number");
        lblnumber.setBounds(100, 120, 150, 25);
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
        
        JLabel lblname = new JLabel ("Name");
        lblname.setBounds(100, 80, 300, 30);
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
        
        JLabel lblgender = new JLabel ("Sex");
        lblgender.setBounds(100, 200, 300, 30);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblgender);
        
        rmale = new JRadioButton ("Male");
        rmale.setBackground(Color.WHITE);
        rmale.setBounds(200, 190, 150, 25);
        
        rfemale = new JRadioButton ("Female");
        rfemale.setBackground(Color.WHITE);
        rfemale.setBounds(200, 210, 150, 25);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rmale);
        sexGroup.add(rfemale);
        
        add(rmale);
        add(rfemale);
        
        JLabel lblroom = new JLabel ("Room No.");
        lblroom.setBounds(100, 240, 100, 30);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 18));
        add(lblroom);
        
        croom = new Choice();
        croom.setBounds(200, 240, 150, 25);
        add(croom);
        
        //room options DITOOOOOOOO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        
        setBounds(300, 100, 800, 550);
        setVisible (true);
    }
    
    public static void main (String[] args) {
        new AddGuest();
    }
}
