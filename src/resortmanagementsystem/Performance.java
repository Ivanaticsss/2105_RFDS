package resortmanagementsystem;

import com.sun.jdi.connect.spi.Connection;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Vector;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchart.style.PieStyler;

public class Performance extends JFrame {
    private JPanel mainPanel, chartPanel;
    private JTable tableRevenue;
    private JComboBox<String> dateFilter, demographicFilter;
    private JButton refreshButton, backButton, toggleButton, showDemographicButton;
    private boolean showingTable = true;

    public Performance() {
        setTitle("Resort Performance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       
        mainPanel = new JPanel();
        mainPanel.setLayout(null); 
        mainPanel.setBackground(Color.WHITE);

       
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(null);
        filterPanel.setBackground(Color.decode("#B7957F")); 
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        
        dateFilter = new JComboBox<>(new String[]{"Last 7 Days", "This Month", "Last Year"});
        dateFilter.setFont(new Font("Arial", Font.PLAIN, 14));
        dateFilter.setBackground(Color.WHITE);
        dateFilter.setBounds(20, 10, 150, 30); 
        filterPanel.add(dateFilter);
        
        
        toggleButton = new JButton("Switch to Graph View");
        toggleButton.setBackground(Color.decode("#8c6e63"));
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setFont(new Font("Arial", Font.BOLD, 14));
        toggleButton.setBounds(170, 10, 200, 30); 
        toggleButton.addActionListener(e -> toggleView());
        filterPanel.add(toggleButton);

 
        demographicFilter = new JComboBox<>(new String[]{"Sex", "Country", "Room", "Status"});
        demographicFilter.setFont(new Font("Arial", Font.PLAIN, 14));
        demographicFilter.setBackground(Color.WHITE);
        demographicFilter.setBounds(400, 10, 150, 30);  
        filterPanel.add(demographicFilter);

     
        showDemographicButton = new JButton("Show Demographic");
        showDemographicButton.setBackground(Color.decode("#8c6e63"));
        showDemographicButton.setForeground(Color.WHITE);
        showDemographicButton.setFont(new Font("Arial", Font.BOLD, 14));
        showDemographicButton.setBounds(550, 10, 180, 30); // Position it with setBounds
        showDemographicButton.addActionListener(e -> updateDemographicChart((String) demographicFilter.getSelectedItem()));
        filterPanel.add(showDemographicButton);
        

        backButton = new JButton("Back to Dashboard");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.decode("#eFEBE9"));
        backButton.setForeground(Color.BLACK);
        backButton.setBounds(790, 10, 180, 30); 
        backButton.addActionListener(e -> goBackToDashboard());
        filterPanel.add(backButton);
        
        
        
        filterPanel.setBounds(10, 10, 1000, 40); 
        mainPanel.add(filterPanel);

        // Chart Panel Setup
        chartPanel = new JPanel();
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(10, 70, 1000, 600); 
        mainPanel.add(chartPanel);

        // Refresh Button Setup
        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 14));
        refreshButton.setBackground(Color.decode("#3E2522"));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setBounds(230, 700, 500, 30); 
        refreshButton.addActionListener(e -> fetchPerformanceData());
        mainPanel.add(refreshButton);

        // Table Setup
        tableRevenue = new JTable();
        tableRevenue.setBackground(Color.WHITE);
        tableRevenue.setForeground(Color.BLACK);
        tableRevenue.getTableHeader().setBackground(Color.decode("#D3A376"));
        tableRevenue.getTableHeader().setForeground(Color.WHITE);
        tableRevenue.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tableRevenue.setRowHeight(20);
        JScrollPane tableScroll = new JScrollPane(tableRevenue);
        tableScroll.setBounds(20, 70, 950, 620); 
        mainPanel.add(tableScroll);

        setBounds(350, 200, 1000, 800); 
        setVisible(true);
        setLocationRelativeTo(null);
        add(mainPanel);
    }
    
   private Map<String, Long> fetchDemographics(String field) {
        String query = "SELECT " + field + ", COUNT(*) as count FROM guest_records GROUP BY " + field;
        Map<String, Long> demographics = new HashMap<>();

        try (Conn conn = new Conn();
             PreparedStatement stmt = conn.c.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fieldValue = rs.getString(field);
                if (fieldValue == null || fieldValue.trim().isEmpty()) {
                    fieldValue = "Unknown"; 
                }
                demographics.put(fieldValue, rs.getLong("count"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching demographics: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        return demographics;
    }

private void updateDemographicChart(String field) {
    // Fetch demographic data
    Map<String, Long> demographics = fetchDemographics(field);

    List<Map.Entry<String, Long>> sortedDemographics = demographics.entrySet().stream()
        .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue()))
        .collect(Collectors.toList());

    final int MAX_CATEGORIES = 10;
    long othersCount = 0;
    if (sortedDemographics.size() > MAX_CATEGORIES) {
        for (int i = MAX_CATEGORIES; i < sortedDemographics.size(); i++) {
            othersCount += sortedDemographics.get(i).getValue();
        }
        sortedDemographics = sortedDemographics.subList(0, MAX_CATEGORIES);
        sortedDemographics.add(new AbstractMap.SimpleEntry<>("Others", othersCount));
    }

    if (sortedDemographics.isEmpty()) {
        JOptionPane.showMessageDialog(this, "No demographic data available for the selected field.", "No Data", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // Create the pie chart
    PieChart chart = new PieChartBuilder().width(950).height(650).title("Demographics: " + field).build();
    chart.getStyler().setChartBackgroundColor(Color.WHITE);  
    chart.getStyler().setPlotBorderColor(Color.WHITE);  
    chart.getStyler().setPlotContentSize(0.8); 
    chart.getStyler().setLegendVisible(false); 
    

   
    Color[] brownShades = {
        new Color(139, 69, 19), // Brown
        new Color(160, 82, 45), // Sienna
        new Color(205, 133, 63), // Peru
        new Color(244, 164, 96), // Sandy Brown
        new Color(210, 105, 30), // Chocolate
        new Color(255, 222, 173), // Navajo White
        new Color(153, 101, 21), // Dark Goldenrod
        new Color(255, 140, 0),   // Dark Orange
        new Color(178, 34, 34),   // Firebrick
        new Color(165, 42, 42)    // Brownish Red
    };

    for (int i = 0; i < sortedDemographics.size(); i++) {
        Map.Entry<String, Long> entry = sortedDemographics.get(i);
        chart.addSeries(entry.getKey(), entry.getValue())
             .setFillColor(brownShades[i % brownShades.length]); // Cycle through shades of brown
    }

   
    XChartPanel<PieChart> pieChartPanel = new XChartPanel<>(chart);


    JPanel legendPanel = new JPanel();
    legendPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); 
    legendPanel.setBackground(Color.WHITE);  
    for (int i = 0; i < sortedDemographics.size(); i++) {
        Map.Entry<String, Long> entry = sortedDemographics.get(i);
        JLabel legendItem = new JLabel(entry.getKey() + ": " + entry.getValue());
        legendItem.setForeground(brownShades[i % brownShades.length]); 
        
        legendItem.setFont(new Font("Arial", Font.BOLD, 14)); 
                
        legendPanel.add(legendItem);
    }

  
    JPanel chartAndLegendPanel = new JPanel();
    chartAndLegendPanel.setLayout(new BorderLayout());
    chartAndLegendPanel.setBackground(Color.WHITE); 
    chartAndLegendPanel.add(pieChartPanel, BorderLayout.CENTER); 
    chartAndLegendPanel.add(legendPanel, BorderLayout.SOUTH); 

   
    chartPanel.removeAll();
    chartPanel.setLayout(new BorderLayout());
    chartPanel.add(chartAndLegendPanel, BorderLayout.CENTER);

    // Hide the table and display the chart
    tableRevenue.setVisible(false);
    chartPanel.setVisible(true);
    mainPanel.revalidate();
    mainPanel.repaint();
}





 private void fetchPerformanceData() {
    String selectedFilter = (String) dateFilter.getSelectedItem();
    String query = "";

    // Define SQL query based on the selected filter
    if ("Last 7 Days".equals(selectedFilter)) {
        query = "SELECT check_in_date, totalCost FROM guest_records WHERE check_in_date >= CURDATE() - INTERVAL 7 DAY";
    } else if ("This Month".equals(selectedFilter)) {
        query = "SELECT check_in_date, totalCost FROM guest_records WHERE MONTH(check_in_date) = MONTH(CURDATE())";
    } else if ("Last Year".equals(selectedFilter)) {
        query = "SELECT check_in_date, totalCost FROM guest_records WHERE YEAR(check_in_date) = YEAR(CURDATE()) - 1";
    }

    System.out.println("Executing query: " + query);

    if (query.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Query is empty. Please check the selected filter.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Conn conn = new Conn();
         PreparedStatement stmt = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery()) {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Check-In Date");
        model.addColumn("Revenue");

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            row.add(rs.getDate("check_in_date"));
            row.add(rs.getDouble("totalCost"));
            model.addRow(row);
        }

        tableRevenue.setModel(model);

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void updateChart(String filter) {
    String query = "";
    if ("Last 7 Days".equals(filter)) {
        query = "SELECT check_in_date, SUM(totalCost) AS revenue FROM guest_records WHERE check_in_date >= CURDATE() - INTERVAL 7 DAY GROUP BY check_in_date";
    } else if ("This Month".equals(filter)) {
        query = "SELECT DATE(check_in_date) AS date, SUM(totalCost) AS revenue FROM guest_records WHERE MONTH(check_in_date) = MONTH(CURDATE()) GROUP BY date";
    } else if ("Last Year".equals(filter)) {
        query = "SELECT DATE(check_in_date) AS date, SUM(totalCost) AS revenue FROM guest_records WHERE YEAR(check_in_date) = YEAR(CURDATE()) - 1 GROUP BY date";
    }

    System.out.println("Executing chart query: " + query);

    if (query.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Query is empty. Please check the selected filter.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Conn conn = new Conn();
         PreparedStatement stmt = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         ResultSet rs = stmt.executeQuery()) {

        if (!rs.next()) {
            JOptionPane.showMessageDialog(this, "No data available for the selected period.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        XYChart chart = new XYChartBuilder().width(900).height(600)
                .title("Revenue Trends").xAxisTitle("Date").yAxisTitle("Revenue").build();
        chart.getStyler().setDatePattern("yyyy-MM-dd");
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setYAxisDecimalPattern("###,###");
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);

        List<Date> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        rs.beforeFirst(); 
        while (rs.next()) {
            Date dateLabel = "Last 7 Days".equals(filter) ? rs.getDate("check_in_date") : rs.getDate("date");
            double revenue = rs.getDouble("revenue");

            if (dateLabel != null) {
                xData.add(dateLabel);
                yData.add(revenue);
            }
        }

        if (xData.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No data available for the selected period.", "No Data", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        chart.addSeries("Revenue", xData, yData);

        chartPanel.removeAll();
        chartPanel.add(new XChartPanel<>(chart), BorderLayout.CENTER);
        chartPanel.validate();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating chart: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
 private void toggleView() {
        if (showingTable) {
            updateChart((String) dateFilter.getSelectedItem());
            tableRevenue.setVisible(false);
            chartPanel.setVisible(true);
            toggleButton.setText("Switch to Table View");
        } else {
            chartPanel.setVisible(false);
            tableRevenue.setVisible(true);
            toggleButton.setText("Switch to Graph View");
        }
        showingTable = !showingTable;
    }

    private void goBackToDashboard() {
    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to leave this page and return to the Dashboard?", 
        "Confirm Exit", 
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    
    if (confirm == JOptionPane.YES_OPTION) {
        this.dispose(); 
         
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Performance performance = new Performance();
            performance.setVisible(true);
        });
    }
}
