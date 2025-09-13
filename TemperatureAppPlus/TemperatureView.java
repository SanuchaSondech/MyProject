package MyProject.TemperatureAppPlus;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class TemperatureView extends JFrame {

    // create text fields
    private JTextField celsiusField = new JTextField(4);
    private JTextField fahrenheitField = new JTextField(4);
    private JTextField kelvinField = new JTextField(4);

    // create labels
    JLabel celsiusLabel = new JLabel("Celsius");
    JLabel fahrenheitLabel = new JLabel("Fahrenheit");
    JLabel kelvinLabel = new JLabel("Kelvin");
    JLabel historyLabel = new JLabel("History");

    // create Error label
    private JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    // create button
    private JButton convertButton = new JButton("Convert");
    private JButton clearButton = new JButton("Clear");

    // create history table
    private String[] columns = {"Celsius", "Fahrenheit", "Kelvin"};
    private JTable historyTable = new JTable(new DefaultTableModel(columns, 0));

    public TemperatureView(){

        // set title
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setSize(400,300);
        setBackground(new Color(220,230,250));

        // create panel
        JPanel tempPanel = new JPanel();
        tempPanel.setBackground(new Color(220,230,250));
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));

        // create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        buttonPanel.setPreferredSize(new Dimension(600, 40));
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        convertButton.setVisible(true);
        clearButton.setVisible(false);

        // create history scroll pane
        JScrollPane historyScrollPane = new JScrollPane(historyTable);
        
        // create top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.add(tempPanel);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setForeground(Color.RED);
        topPanel.add(errorLabel);
        topPanel.setBackground(new Color(220,200,200));

        // create center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        historyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        historyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(historyLabel);
        centerPanel.add(Box.createVerticalStrut(4));
        centerPanel.add(historyScrollPane);


        // add components to panel
        tempPanel.add(celsiusLabel);
        tempPanel.add(celsiusField);
        tempPanel.add(fahrenheitLabel);
        tempPanel.add(fahrenheitField);
        tempPanel.add(kelvinLabel);
        tempPanel.add(kelvinField);

        // add panel to frame
        add(topPanel, BorderLayout.NORTH); // ส่วนบน
        add(centerPanel); // ส่วนกลาง
        add(buttonPanel, BorderLayout.SOUTH); // ปุ่ม
        setVisible(true);

    }

    // create method to set error message
        public void setError(String message) {
            errorLabel.setText(message);
        }

    // create getters for text fields
    public String getCelsiusField() {
        return celsiusField.getText();
    }
    public String getFahrenheitField() {
        return fahrenheitField.getText();
    }

    public String getKelvinField() {
        return kelvinField.getText();
    }

    // create setters for text fields
    public void setCelsiusField(String text) {
        celsiusField.setText(text);
    }

    public void setFahrenheitField(String text) {
        fahrenheitField.setText(text);
    }

    public void setKelvinField(String text) {
        kelvinField.setText(text);
    }

    // create getters for Error label
    public String getErrorLabel() {
        return errorLabel.getText();
    }

    // create method to add Listener ConvertButton
    public void addConvertButtonListener(java.awt.event.ActionListener listener) {
        convertButton.addActionListener(listener);
    }
    // create method to add Listener ClearButton
    public void addClearButtonListener(java.awt.event.ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    // create method to set visible button
    public void showConvertButton() {
        convertButton.setVisible(true);
        clearButton.setVisible(false);
    }

    public void showClearButton() {
        convertButton.setVisible(false);
        clearButton.setVisible(true);
    }

    // create method to update history table
    public void updateHistoryTable(java.util.List<double[]> history) {
        // ดึงข้อมูลจาก model มาเก็บในตาราง model
        DefaultTableModel model = (DefaultTableModel) historyTable.getModel(); 
        model.setRowCount(0); // clear all rows

        // วนลูปเพิ่มข้อมูลในตาราง
        for (int i = history.size() - 1; i >= 0; i--) {
            double[] entry = history.get(i);
            model.addRow(new Object[]{
            String.format("%.2f", entry[0]),
            String.format("%.2f", entry[1]),
            String.format("%.2f", entry[2])
        });
        }
         
    }
    
}
