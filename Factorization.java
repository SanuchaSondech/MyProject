package MyProject;

import javax.swing.*;
import java.awt.*;

// Model________________________________________________________
class valueModel {
    private int value_A;
    private int value_B;
    private int value_C;

    public void setValue_A(int value_A) {
        this.value_A = value_A;
    }
    public void setValue_B(int value_B) {
        this.value_B = value_B;
    }
    public void setValue_C(int value_C) {
        this.value_C = value_C;
    }

    public int getValue_A() {
        return value_A;
    }
    public int getValue_B() {
        return value_B;
    }
    public int getValue_C() {
        return value_C;
    }
}

// View_______________________________________________________
class valueView extends JFrame {

    JTextField value_A_Field = new JTextField(3);
    JTextField value_B_Field = new JTextField(3);
    JTextField value_C_Field = new JTextField(3);
    JButton factorizeButton = new JButton("factorize");

    // ✅ label สำหรับแสดงผล
    JLabel resultLabel = new JLabel("", SwingConstants.CENTER);

    public valueView() {

        setTitle("Value Factorization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // แถวกรอกค่า
        JPanel panel = new JPanel();
        panel.setBackground(new Color(220, 230, 250));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        JLabel label_A = new JLabel("x² + ");
        JLabel label_B = new JLabel(" x + ");
        JLabel label_C = new JLabel(" ");

        panel.add(value_A_Field);
        panel.add(label_A);
        panel.add(value_B_Field);
        panel.add(label_B);
        panel.add(value_C_Field);
        panel.add(label_C);

        add(panel, BorderLayout.NORTH); // ✅ เอาช่องกรอกไว้บน

        // ✅ เอา label แสดงผลไว้กลางจอ
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(resultLabel, BorderLayout.CENTER);

        // ปุ่มอยู่ข้างล่าง
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        buttonPanel.add(factorizeButton);
        buttonPanel.setPreferredSize(new Dimension(600, 40));
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setSize(600, 400);
        setVisible(true);
    }

    public int getValue_A() {
        return Integer.parseInt(value_A_Field.getText().trim());
    }
    public int getValue_B() {
        return Integer.parseInt(value_B_Field.getText().trim());
    }
    public int getValue_C() {
        return Integer.parseInt(value_C_Field.getText().trim());
    }

    // ✅ method สำหรับ set ข้อความผลลัพธ์
    public void setResult(String message) {
        resultLabel.setText(message);
    }
}

// Controller________________________________________________________
class valueController {

    private valueModel model;
    private valueView view;

    public valueController(valueModel model, valueView view) {
        this.model = model;
        this.view = view;

        this.view.factorizeButton.addActionListener(e -> onFactorizeButtonClick());
    }

    private void onFactorizeButtonClick() {
        try {
            model.setValue_A(view.getValue_A());
            model.setValue_B(view.getValue_B());
            model.setValue_C(view.getValue_C());

            double[] factors = factorize();
            showfactorize(model.getValue_A(), model.getValue_B(), model.getValue_C(), factors);

        } catch (NumberFormatException ex) {
            view.setResult("Please enter valid numbers.");
        }
    }

    private double[] factorize() {
        double a = model.getValue_A();
        double b = model.getValue_B();
        double c = model.getValue_C();

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return null;
        } else {
            double sqrtDisc = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDisc) / (2 * a);
            double x2 = (-b - sqrtDisc) / (2 * a);

            return new double[]{x1, x2};
        }
    }

    private void showfactorize(double a, double b, double c, double[] value) {
        if (value == null) {
            view.setResult(String.format("Can not factorize %.2fx² + %.2fx + %.2f", a, b, c));
        } else {
            String s1 = (value[0] >= 0) ? "x - " + value[0] : "x + " + (-value[0]);
            String s2 = (value[1] >= 0) ? "x - " + value[1] : "x + " + (-value[1]);

            String showMessage = "(" + s1 + ")(" + s2 + ")";
            view.setResult(showMessage);
        }
    }
}

// Main________________________________________________________
public class Factorization {
    public static void main(String[] args) {
        valueModel model = new valueModel();
        valueView view = new valueView();
        valueController controller = new valueController(model, view);
    }
}
