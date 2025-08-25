package MyProject;

import javax.swing.*;
import java.awt.*;

// Model________________________________________________________
class valueModel {
     // ประกาศตัวแปล
    private int value_A;
    private int value_B;
    private int value_C;

    // Setter methods
    public void setValue_A(int value_A) {
        this.value_A = value_A;
    }

    public void setValue_B(int value_B) {
        this.value_B = value_B;
    }

    public void setValue_C(int value_C) {
        this.value_C = value_C;
    }

    // Getter methods
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
class valueView extends JFrame{

    // สร้าง Object
    JTextField value_A_Field = new JTextField(3);
    JTextField value_B_Field = new JTextField(3);
    JTextField value_C_Field = new JTextField(3);
    JButton factorizeButton = new JButton("factorize");

    public valueView() {

        setTitle("Value Factorization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(220, 230, 250)); // เปลี่ยนสีพื้นหลัง
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20)); // ตั้งค่าการจัดเรียง
        

        JLabel label_A = new JLabel("x² + ");
        JLabel label_B = new JLabel(" x + ");
        JLabel label_C = new JLabel(" ");

        panel.add(value_A_Field);
        panel.add(label_A);
        panel.add(value_B_Field);
        panel.add(label_B);
        panel.add(value_C_Field);
        panel.add(label_C);

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 250));
        buttonPanel.add(factorizeButton);
        buttonPanel.setPreferredSize(new Dimension(600, 40)); // ปรับความสูงปุ่ม
        add(buttonPanel, BorderLayout.SOUTH);

        
        setLocationRelativeTo(null); // ตั้งค่าให้หน้าต่างอยู่กลางจอ
        setSize(600, 400); // ตั้งค่าขนาดหน้าต่าง
        //setResizable(false); // ไม่ให้ปรับขนาดหน้าต่างได้
        setVisible(true); // แสดงหน้าต่าง
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
}

// Controller________________________________________________________

class valueController {

    // สร้าง
    private valueModel model;
    private valueView view;

    public valueController(valueModel mode, valueView view){
        this.model = mode;
        this.view = view;

        // เพิ่ม ActionListener ให้กับปุ่ม
        this.view.factorizeButton.addActionListener(e -> onFactorizeButtonClick());

    }

    private void onFactorizeButtonClick() {
        // รับค่าจาก View
        model.setValue_A(view.getValue_A());
        model.setValue_B(view.getValue_B());
        model.setValue_C(view.getValue_C());

        // เรียกใช้ method factorize
        double[] factors = factorize();

        // แสดงผลลัพธ์
        showfactorize(view.getValue_A(), view.getValue_B(), view.getValue_C(), factors);

    }

    // method คำนวณแยกตัวประกอบ
    private double[] factorize() {

        // ดึงค่าจาก Model
        double a = model.getValue_A();
        double b = model.getValue_B();
        double c = model.getValue_C();


        // ตรวจสอบว่าติด i ไหม
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            // คิดไม่ได้
            return null;
        } else {
            // หาตัวประกอบได้
            double sqrtDisc = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDisc) / (2 * a);
            double x2 = (-b - sqrtDisc) / (2 * a);

            return new double[]{x1, x2};
        }
    }

    // method แสดงข้อความ
    private void showfactorize(double a, double b, double c, double[] value) {
        if (value == null) {
            String nullMessage = String.format("Can not factorize %dx² + %dx + %d", a, b, c);
            JOptionPane.showMessageDialog(view, nullMessage);
        } else {

            String s1;
        if (value[0] >= 0) {
            s1 = "x - " + value[0];
        } else {
             s1 = "x + " + (-value[0]);
        }

        String s2;
        if (value[1] >= 0) {
            s2 = "x - " + value[1];
        } else {
            s2 = "x + " + (-value[1]);
        }

            String showMessage = "(" + s1 + ")(" + s2 + ")";
            JOptionPane.showMessageDialog(view, showMessage);
        }
    }
}

public class Factorization {

    public static void main(String[] args) {
        // สร้าง Model, View และ Controller
        valueModel model = new valueModel();
        valueView view = new valueView();
        valueController controller = new valueController(model, view);
        
        // ตั้งค่าขนาดหน้าต่าง
        view.setSize(400, 200);
    }
}
