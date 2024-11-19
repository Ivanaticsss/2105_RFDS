package resortmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.util.Date;

public class GenerateBill {
    
    public GenerateBill(String name, String address, String number, String country, String id, String roomNumber, 
                        String roomType, String roomFacilities, String bedType, int lengthOfStay, 
                        Date checkInDate, Date checkOutDate, String servicesAvailed, 
                        double roomPrice, double servicesTotal, double totalCost, 
                        double totalPayment, String paymentStatus) {

       
        JFrame billFrame = new JFrame("Guest Bill - Serenity Cove");
        billFrame.setSize(500, 700);
        billFrame.setLayout(null);

        JTextArea billDetails = new JTextArea();
        billDetails.setEditable(false);
        billDetails.setFont(new Font("Helvetica", Font.PLAIN, 14));
        billDetails.setBounds(20, 20, 440, 620);

        // Start building the bill text
        StringBuilder billText = new StringBuilder();
        billText.append("\t----- Serenity Cove Resort -----\n\n")
                .append("  Thank you for choosing Serenity Cove!\n\n")
                .append(" Guest Details:\n")
                .append(" Name: ").append(name).append("\n")
                .append(" Address: ").append(address).append("\n")
                .append(" Phone Number: ").append(number).append("\n")
                .append(" Country: ").append(country).append("\n")
                .append(" ID: ").append(id).append("\n\n");

        // Only include room details if a room has been reserved
        if (roomNumber != null && !roomNumber.isEmpty()) {
            billText.append(" Room Details:\n")
                    .append(" Room Number: ").append(roomNumber).append("\n")
                    .append(" Room Type: ").append(roomType).append("\n")
                    .append(" Facilities: ").append(roomFacilities).append("\n")
                    .append(" Bed Type: ").append(bedType).append("\n")
                    .append(" Length of Stay: ").append(lengthOfStay).append(" nights\n\n");
        }

        // Services Availed section
        if (servicesAvailed != null && !servicesAvailed.isEmpty()) {
            billText.append(" Services Availed:\n")
                    .append(servicesAvailed).append("\n\n");
        } else {
            billText.append(" No services availed.\n\n");
        }

        // Breakdown of Costs (Room price only included if room is reserved)
        if (roomPrice > 0) {
            billText.append(" Breakdown of Costs:\n")
                    .append(" Room Price: ₱").append(String.format("%.2f", roomPrice)).append("\n");
        }

        if (servicesTotal > 0) {
            billText.append(" Services Total: ₱").append(String.format("%.2f", servicesTotal)).append("\n");
        }

        // Total cost calculation
        billText.append(" Total Cost: ₱").append(String.format("%.2f", totalCost)).append("\n")
                .append(" Total Payment: ₱").append(String.format("%.2f", totalPayment)).append("\n")
                .append(" Payment Status: ").append(paymentStatus.equalsIgnoreCase("paid") ? "Fully Paid" : "Pending").append("\n\n")
                .append("\tWe hope to see you again soon!\n");

     
        billDetails.setText(billText.toString());

        
        JScrollPane scrollPane = new JScrollPane(billDetails);
        scrollPane.setBounds(20, 20, 440, 620);

       
        billFrame.add(scrollPane);

       
        JButton printButton = new JButton("Print Bill");
        printButton.setBounds(180, 650, 120, 30);
        printButton.addActionListener(e -> printBill(billText.toString()));  // Print bill on button click
        billFrame.add(printButton);

       
        billFrame.setVisible(true);
        billFrame.setSize(500, 750);  

    }

 private void printBill(String billContent) {
    PrinterJob printerJob = PrinterJob.getPrinterJob();
    printerJob.setPrintable(new Printable() {
        @Override
        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;  // Only print the first page
            }

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setFont(new Font("Serif", Font.PLAIN, 12));
            FontMetrics metrics = g2d.getFontMetrics();

            // Set line height and margins
            int lineHeight = metrics.getHeight();
            int margin = 20;
            int x = margin;
            int y = margin + lineHeight;

           
            String[] lines = billContent.split("\n");  // Split by line breaks
            for (String line : lines) {
               
                if (metrics.stringWidth(line) > pf.getImageableWidth() - 2 * margin) {
                    
                    String[] wrappedLines = wrapText(line, g2d, pf.getImageableWidth() - 2 * margin);
                    for (String wrappedLine : wrappedLines) {
                        g2d.drawString(wrappedLine, x, y);
                        y += lineHeight; 
                    }
                } else {
                    
                    g2d.drawString(line, x, y);
                    y += lineHeight;  // Move to the next line
                }

                // If the content reaches the end of the page, move to the next page
                if (y > pf.getImageableHeight() - lineHeight) {
                    return PAGE_EXISTS;
                }
            }

            return PAGE_EXISTS;
        }
    });

    if (printerJob.printDialog()) {
        try {
            printerJob.print();
        } catch (PrinterException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error printing the bill.");
        }
    }
}

    // Helper method to wrap text within the page width
    private String[] wrapText(String text, Graphics2D g2d, double pageWidth) {
        FontMetrics metrics = g2d.getFontMetrics();
        int lineLength = (int) (pageWidth / metrics.charWidth('M'));  
        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();
        StringBuilder wrappedText = new StringBuilder();

        for (String word : words) {
            if (metrics.stringWidth(currentLine.toString() + " " + word) <= pageWidth) {
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                wrappedText.append(currentLine).append("\n");
                currentLine = new StringBuilder(word);
            }
        }

        // Append the last line if any
        if (currentLine.length() > 0) {
            wrappedText.append(currentLine);
        }

        return wrappedText.toString().split("\n");
    }
}
