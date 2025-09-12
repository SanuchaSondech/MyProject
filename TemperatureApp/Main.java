package MyProject.TemperatureApp;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TempView view = new TempView();
            TempModel model = new TempModel();
            TempTempController controller = new TempTempController(view, model);
        });
    }
}