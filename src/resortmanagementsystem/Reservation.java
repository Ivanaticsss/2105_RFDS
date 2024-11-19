package resortmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Reservation extends JFrame {
    private JLabel lblName, lblAddress,lblNumber;
    private JTextField tfName, tfAddress, tfNumber;
    private JPanel background;

    public Reservation() {
        setTitle("Book Reservation");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        background = new JPanel(null);
        background.setBounds(0, 0, 800, 700);
        add(background);

        lblName = new JLabel("Full Name:");
        lblName.setBounds(45, 120, 300, 30);
        lblName.setFont(new Font("Helvetica", Font.PLAIN, 17));
        background.add(lblName);

        tfName = new JTextField("Enter Name");
        tfName.setBounds(200, 120, 150, 25);
        tfName.setForeground(Color.GRAY);
        
        tfName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfName.getText().equals("Enter Name")) {
                    tfName.setText("");
                    tfName.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfName.getText().isEmpty()) {
                    tfName.setText("Enter Name");
                    tfName.setForeground(Color.GRAY);
                }
            }
        });
        add (tfName);
        
        lblAddress = new JLabel("Full Address:");
        lblAddress.setBounds(45, 170, 300, 30);
        lblAddress.setFont(new Font("Helvetica", Font.PLAIN, 17));
        background.add(lblAddress);

        tfAddress = new JTextField("Enter Address");
        tfAddress.setBounds(200, 170, 200, 25);
        tfAddress.setForeground(Color.GRAY);
        
        tfAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfAddress.getText().equals("Enter Address")) {
                    tfAddress.setText("");
                    tfAddress.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfAddress.getText().isEmpty()) {
                    tfAddress.setText("Enter Address");
                    tfAddress.setForeground(Color.GRAY);
                }
            }
        });
        add (tfAddress);
        
        
        //NANDINEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        setBounds(300, 100, 800, 550); //ITO FIT SA SCREEN KOOOOOOOOOOOOOOOOOOOOOOOOOO
        //setBounds(300, 100, 1200, 800);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reservation();
    }
}
