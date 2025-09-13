package MyProject.TemperatureAppPlus;

public class TemperatureController {

    private TemperatureView view;
    private TemperatureModel model;

    public TemperatureController(TemperatureView view, TemperatureModel model) {
        this.view = view;
        this.model = model;

        this.view.addConvertButtonListener(e -> onConvertButtonClick());
        this.view.addClearButtonListener(e -> onClearButtonClick());
    }

    private void onConvertButtonClick() {
    try {
        String celsiusText = view.getCelsiusField();
        String fahrenheitText = view.getFahrenheitField();
        String kelvinText = view.getKelvinField();

        // นับจำนวนช่องที่มีค่า
        int filledFields = 0;
        if (!celsiusText.isEmpty()) filledFields++;
        if (!fahrenheitText.isEmpty()) filledFields++;
        if (!kelvinText.isEmpty()) filledFields++;

        if (filledFields == 0) {
            view.setError("Please enter a number.");
        } else if (filledFields > 1) {
            view.setError("Please enter only ONE number.");
            view.showClearButton();
        } else {
            view.showClearButton();
            // แปลงอุณหภูมิตามช่องที่มีค่า
            if (!celsiusText.isEmpty()) { // celsius
                double celsius = Double.parseDouble(celsiusText);
                model.setCelsius(celsius);
                model.setFahrenheit(model.celsiusToFahrenheit(celsius));
                model.setKelvin(model.celsiusToKelvin(celsius));
                model.addToHistory(model.getCelsius(), model.getFahrenheit(), model.getKelvin());
                view.updateHistoryTable(model.getHistory());
            } else if (!fahrenheitText.isEmpty()) { // fahrenheit
                double fahrenheit = Double.parseDouble(fahrenheitText);
                model.setFahrenheit(fahrenheit);
                model.setCelsius(model.fahrenheitToCelsius(fahrenheit));
                model.setKelvin(model.fahrenheitToKelvin(fahrenheit));
                model.addToHistory(model.getCelsius(), model.getFahrenheit(), model.getKelvin());
                view.updateHistoryTable(model.getHistory());
            } else { // kelvin
                double kelvin = Double.parseDouble(kelvinText);
                model.setKelvin(kelvin);
                model.setCelsius(model.kelvinToCelsius(kelvin));
                model.setFahrenheit(model.kelvinToFahrenheit(kelvin));
                model.addToHistory(model.getCelsius(), model.getFahrenheit(), model.getKelvin());
                view.updateHistoryTable(model.getHistory());
            }

            // update text fields
            view.setCelsiusField(String.format("%.2f", model.getCelsius()));
            view.setFahrenheitField(String.format("%.2f", model.getFahrenheit()));
            view.setKelvinField(String.format("%.2f", model.getKelvin()));
        }

    } catch (NumberFormatException ex) {
        view.setError("Please enter numbers.");
    }
}

    private void onClearButtonClick() {
        view.setCelsiusField("");
        view.setFahrenheitField("");
        view.setKelvinField("");
        view.setError("");

        view.showConvertButton();
    }

}