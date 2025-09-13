package TemperatureApp;

public class TempModel {

    // create attribute
    private double celsius;
    private double fahrenheit;
    private double kelvin;

    // create getters
    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public double getKelvin() {
        return kelvin;
    }
    // create setters
    public void setCelsius(double celsius) {
        this.celsius = celsius;
    }

    public void setFahrenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public void setKelvin(double kelvin) {
        this.kelvin = kelvin;
    }

    // create conversion methods
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }
    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
    public double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
    public double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }


}
