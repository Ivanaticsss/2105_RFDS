package resortmanagementsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
//import javax.swing.JOptionPane;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Reservation extends JFrame{
    private JLabel lblName, lblAddress, lblNumber, lblCountry, lblSex, lblCheckIn, lblLengthOfStay, lblDateGuide, lblId, lblCheckOut, lblpaymentMethod;
    private JTextField tfName, tfAddress, tfNumber, tfCountry, tfLengthOfStay;
    private JPanel background;
    private JRadioButton rMale, rFemale;
    private JComboBox<String> checkInMonth, checkInDay, checkInYear, comboId, comboPayment;
    private JButton jBAdd, jBBack, jBReset, availServices;

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

        lblId = new JLabel("ID:");
        lblId.setBounds(450, 120, 150, 25);
        lblId.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        background.add(lblId);
        
        String options[] = {"Driver's License", "SSS ID", "Pag-ibig ID", "Voter's ID", "National ID", "Company ID", "Student's ID", "Passport/Visa"};
        comboId = new JComboBox<>(options);
        comboId.setBounds(655, 120, 130, 25);
        comboId.setBackground(Color.WHITE);
        comboId.setFont(new Font("HELVETICA", Font.PLAIN, 15));
        //Border border = BorderFactory.createLineBorder(Color.decode("#D3A376"), 2);  // Change 'Color.BLUE' to your desired color
        //comboId.setBorder(border);
        background.add(comboId);
        
        lblpaymentMethod = new JLabel("Payment Method:");
        lblpaymentMethod.setBounds(450, 170, 150, 25);
        lblpaymentMethod.setFont(new Font("HELVETICA", Font.PLAIN, 17));
        background.add(lblpaymentMethod);
        
        String paymentOptions[] = {"Cash", "Credit Card (VISA, Mastercard, etc.)", "Debit Card", "E-Wallet"};
        comboPayment = new JComboBox<>(paymentOptions);
        comboPayment.setBounds(655, 170, 130, 25);
        comboPayment.setBackground(Color.WHITE);
        comboPayment.setFont(new Font("HELVETICA", Font.PLAIN, 15));
        //Border paymentBorder = BorderFactory.createLineBorder(Color.decode("#D3A376"), 2);  // Change 'Color.BLUE' to your desired color
        //comboPayment.setBorder(paymentBorder);
        background.add(comboPayment);
        
        availServices = new JButton("Avail Services");
        availServices.setBackground(Color.decode("#D2B486"));
        availServices.setForeground(Color.BLACK);
        availServices.setFont(new Font("Helvetica", Font.BOLD, 18));
        availServices.setBounds(520, 320, 200, 40);
        availServices.addActionListener((ActionListener) this);
        background.add(availServices);
        
        jBAdd = new JButton ("Add");
        jBAdd.setBackground(Color.BLACK);
        jBAdd.setForeground(Color.WHITE);
        jBAdd.setBounds (250, 500, 120, 25);
        //jBAdd.addActionListener(this);
        background.add (jBAdd);
        
        jBBack = new JButton ("Back");
        jBBack.setBackground(Color.BLACK);
        jBBack.setForeground(Color.WHITE);
        jBBack.setBounds (400, 500, 120, 25);
        //jBBack.addActionListener(this);
        background.add (jBBack);
        
        jBReset = new JButton ("Reset");
        jBReset.setBackground(Color.BLACK);
        jBReset.setForeground(Color.WHITE);
        jBReset.setBounds (650, 500, 120, 25);
        //jBReset.addActionListener(this);
        background.add (jBReset);


        
        //NANDINEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
        setBounds(230, 70, 900, 600); //ITO FIT SA SCREEN KOOOOOOOOOOOOOOOOOOOOOOOOOO
        //setBounds(300, 100, 1200, 800); ITO YUNG SA SCREEN NI DANAH
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
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == availServices) {
        String name = tfName.getText().trim();
        String address = tfAddress.getText().trim();
        String number = tfNumber.getText().trim();
        String id = (String) comboId.getSelectedItem();  
        String sex = rMale.isSelected() ? "Male" : "Female"; 
        String country = tfCountry.getText().trim();
        String paymentMethod = (String) comboPayment.getSelectedItem();  

        String lengthOfStayStr = tfLengthOfStay.getText().trim();  // Get the length of stay from the input

        // Validate required fields
        if (name.isEmpty() || address.isEmpty() || number.isEmpty() || country.isEmpty() || lengthOfStayStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;  // Exit the method if any required fields are empty
        }

        try {
            // Validate the length of stay input
            int lengthOfStay = Integer.parseInt(lengthOfStayStr);  
            if (lengthOfStay <= 0) {
                JOptionPane.showMessageDialog(this, "Length of stay must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Get the check-in date from the selected values
            String checkInDate = checkInYear.getSelectedItem() + "-" +
                                 checkInMonth.getSelectedItem() + "-" +
                                 checkInDay.getSelectedItem();
            
            // Parse the check-in date to Date format
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInParsedDate = inputFormat.parse(checkInDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedCheckInDate = outputFormat.format(checkInParsedDate);

            // Calculate check-out date based on length of stay
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkInParsedDate);  
            cal.add(Calendar.DAY_OF_YEAR, lengthOfStay);  
            String checkOutDate = outputFormat.format(cal.getTime());

            // SQL query to insert a new guest reservation
            String query = "INSERT INTO guest (name, address, number, document, sex, country, check_in_date, check_out_date, paymentMethod, length_of_stay) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Create a database connection and execute the insert query
            Conn conn = new Conn();
            PreparedStatement stmt = conn.c.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, number);
            stmt.setString(4, id);
            stmt.setString(5, sex);
            stmt.setString(6, country);
            stmt.setString(7, formattedCheckInDate);  // Check-in date
            stmt.setString(8, checkOutDate);  // Check-out date
            stmt.setString(9, paymentMethod);  // Payment method
            stmt.setInt(10, lengthOfStay);  // Length of stay
            stmt.executeUpdate();  // Execute the query
            
            JOptionPane.showMessageDialog(this, "Reservation added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Optionally, you can clear the fields or update UI elements here

        } catch (NumberFormatException e) {
            // Handle invalid length of stay input
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the length of stay.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Handle any other exceptions (e.g., database issues)
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while processing your reservation.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    
    
    public static void main(String[] args) {
        new Reservation();
    }
}
