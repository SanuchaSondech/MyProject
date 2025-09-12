package MyProject.TemperatureApp;

import javax.swing.*;
import java.awt.*;

public class TempView extends JFrame {

    // create text fields
    private JTextField celsiusField = new JTextField(4);
    private JTextField fahrenheitField = new JTextField(4);
    private JTextField kelvinField = new JTextField(4);

    // create labels
    JLabel celsiusLabel = new JLabel("Celsius");
    JLabel fahrenheitLabel = new JLabel("Fahrenheit");
    JLabel kelvinLabel = new JLabel("Kelvin");

    // create Error label
    private JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    // create button
    private JButton convertButton = new JButton("Convert");
    private JButton clearButton = new JButton("Clear");

    public TempView(){

        // set title
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        setSize(400,300);
        setBackground(new Color(220,230,250));

        // create panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(220,230,250));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));

        // create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        buttonPanel.setPreferredSize(new Dimension(600, 40));
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        convertButton.setVisible(true);
        clearButton.setVisible(false);

        // add components to panel
        panel.add(celsiusLabel);
        panel.add(celsiusField);
        panel.add(fahrenheitLabel);
        panel.add(fahrenheitField);
        panel.add(kelvinLabel);
        panel.add(kelvinField);

        // add panel to frame
        add(panel, BorderLayout.NORTH);     // ฟิลด์กรอกข้อมูล
        errorLabel.setForeground(Color.RED);
        add(errorLabel, BorderLayout.CENTER); // ข้อความ error
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
}