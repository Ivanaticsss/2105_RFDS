package resortmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.LocalDate;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Reservation extends JFrame {
    private JLabel lblName, lblAddress, lblNumber, lblCountry, lblSex, lblCheckIn, lblLengthOfStay, lblDateGuide, lblId, lblCheckOut;
    private JTextField tfName, tfAddress, tfNumber, tfCountry, tfLengthOfStay;
    private JPanel background;
    private JRadioButton rMale, rFemale;
    private JComboBox<String> checkInMonth, checkInDay, checkInYear, comboId;

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
        add(tfName);

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
        add(tfAddress);

        lblNumber = new JLabel("Enter Number:");
        lblNumber.setBounds(45, 220, 300, 30);
        lblNumber.setFont(new Font("Helvetica", Font.PLAIN, 17));
        background.add(lblNumber);

        tfNumber = new JTextField("Mobile Number");
        tfNumber.setBounds(200, 220, 200, 25);
        tfNumber.setForeground(Color.GRAY);

        tfNumber.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfNumber.getText().equals("Mobile Number")) {
                    tfNumber.setText("");
                    tfNumber.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfNumber.getText().isEmpty()) {
                    tfNumber.setText("Mobile Number");
                    tfNumber.setForeground(Color.GRAY);
                }
            }
        });
        add(tfNumber);

        lblCountry = new JLabel("Country:");
        lblCountry.setBounds(45, 270, 300, 30);
        lblCountry.setFont(new Font("Helvetica", Font.PLAIN, 17));
        background.add(lblCountry);

        tfCountry = new JTextField("Enter Country");
        tfCountry.setBounds(200, 270, 200, 25);
        tfCountry.setForeground(Color.GRAY);

        tfCountry.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfCountry.getText().equals("Enter Country")) {
                    tfCountry.setText("");
                    tfCountry.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfCountry.getText().isEmpty()) {
                    tfCountry.setText("Enter Country");
                    tfCountry.setForeground(Color.GRAY);
                }
            }
        });
        add(tfCountry);

        lblSex = new JLabel("Sex:");
        lblSex.setBounds(45, 320, 300, 30);
        lblSex.setFont(new Font("Helvetica", Font.PLAIN, 17));
        add(lblSex);

        rMale = new JRadioButton("Male");
        rMale.setBounds(200, 320, 70, 25);

        rFemale = new JRadioButton("Female");
        rFemale.setBounds(270, 320, 80, 25);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(rMale);
        sexGroup.add(rFemale);

        background.add(lblSex);
        background.add(rMale);
        background.add(rFemale);

        lblCheckIn = new JLabel("Check-In Date:");
        lblCheckIn.setBounds(45, 370, 150, 25);
        lblCheckIn.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        background.add(lblCheckIn);

        lblDateGuide = new JLabel("MM // DD // YY");
        lblDateGuide.setBounds(200, 350, 150, 25);
        lblDateGuide.setForeground(Color.GRAY);
        lblDateGuide.setFont(new Font("HELVETICA", Font.ITALIC, 10));
        background.add(lblDateGuide);

        String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        checkInMonth = new JComboBox<>(months);
        checkInMonth.setBounds(200, 370, 65, 25);
        background.add(checkInMonth);

        checkInDay = new JComboBox<>();
        checkInDay.setBounds(280, 370, 65, 25);
        background.add(checkInDay);

        String[] years = {"2024", "2025"};
        checkInYear = new JComboBox<>(years);
        checkInYear.setBounds(360, 370, 65, 25);
        background.add(checkInYear);

        checkInMonth.addActionListener(e -> updateDays());
        checkInYear.addActionListener(e -> updateDays());

        lblLengthOfStay = new JLabel("Length Of Stay:");
        lblLengthOfStay.setBounds(45, 420, 200, 25);
        lblLengthOfStay.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        background.add(lblLengthOfStay);

        tfLengthOfStay = new JTextField("Enter Number of Days");
        tfLengthOfStay.setBounds(200, 420, 200, 25);
        tfLengthOfStay.setForeground(Color.GRAY);

        tfLengthOfStay.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfLengthOfStay.getText().equals("Enter Number of Days")) {
                    tfLengthOfStay.setText("");
                    tfLengthOfStay.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfLengthOfStay.getText().isEmpty()) {
                    tfLengthOfStay.setText("Enter Number of Days");
                    tfLengthOfStay.setForeground(Color.GRAY);
                }
            }
        });

        tfLengthOfStay.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                calculateCheckOut();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                calculateCheckOut();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                calculateCheckOut();
            }
        });
        background.add(tfLengthOfStay);

        lblCheckOut = new JLabel("Check-out:");
        lblCheckOut.setBounds(200, 450, 390, 25);
        lblCheckOut.setFont(new Font("HELVETICA", Font.PLAIN, 10));
        background.add(lblCheckOut);

        
        
        //NANDINEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        setBounds(300, 100, 800, 550); //ITO FIT SA SCREEN KOOOOOOOOOOOOOOOOOOOOOOOOOO
        //setBounds(300, 100, 1200, 800);
        setVisible(true);
    }


    private void updateDays() {
        checkInDay.removeAllItems();
        String selectedMonth = (String) checkInMonth.getSelectedItem();
        int month = Integer.parseInt(selectedMonth);
        int year = Integer.parseInt((String) checkInYear.getSelectedItem());
        int daysInMonth = getDaysInMonth(month, year);

        for (int i = 1; i <= daysInMonth; i++) {
            checkInDay.addItem(String.valueOf(i));
        }
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 30;
        }
    }

    private void calculateCheckOut() {
        try {
            int lengthOfStay = Integer.parseInt(tfLengthOfStay.getText());
            if (lengthOfStay > 0) {
                LocalDate checkInDate = LocalDate.of(
                    Integer.parseInt((String) checkInYear.getSelectedItem()),
                    Integer.parseInt((String) checkInMonth.getSelectedItem()),
                    Integer.parseInt((String) checkInDay.getSelectedItem())
                );
                LocalDate checkOutDate = checkInDate.plusDays(lengthOfStay);
                lblCheckOut.setText("Check-out Date: " + checkOutDate);
                lblCheckOut.setBounds(200, 450, 400, 25);
                lblCheckOut.setForeground(Color.gray);
                lblCheckOut.setFont(new Font("HELVETICA", Font.ITALIC, 10));
            }
        } catch (Exception e) {
            lblCheckOut.setText("Invalid date or length of stay");
            lblCheckOut.setForeground(Color.RED);  // Set error text color
            lblCheckOut.setBounds(200, 450, 400, 30);
            lblCheckOut.setForeground(Color.red);
            lblCheckOut.setFont(new Font("HELVETICA", Font.ITALIC, 10));
        }
    }

    public static void main(String[] args) {
        new Reservation();
    }
}
