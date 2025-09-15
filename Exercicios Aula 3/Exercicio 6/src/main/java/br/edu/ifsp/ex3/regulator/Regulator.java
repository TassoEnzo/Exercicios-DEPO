package br.edu.ifsp.ex3.regulator;

public class Regulator {
    private Thermometer thermometer;
    private Heater heater;

    public Regulator(Thermometer thermometer, Heater heater) {
        this.thermometer = thermometer;
        this.heater = heater;
    }

    public void regulate(double minTemp, double maxTemp) {
        double currentTemp = thermometer.read();
        System.out.println("Temperatura atual: " + currentTemp + "°C");

        if (currentTemp < minTemp) {
            heater.engage();
        } else if (currentTemp > maxTemp) {
            heater.disengage();
        } else {
            System.out.println("Temperatura dentro da faixa. Nenhuma ação necessária.");
        }
    }
}

