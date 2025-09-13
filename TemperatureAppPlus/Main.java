package MyProject.TemperatureAppPlus;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TemperatureView view = new TemperatureView();
            TemperatureModel model = new TemperatureModel();
            TemperatureController controller = new TemperatureController(view, model);
        });
    }
}