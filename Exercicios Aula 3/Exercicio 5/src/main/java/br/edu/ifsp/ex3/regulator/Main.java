package br.edu.ifsp.ex3.regulator;

public class Main {
    public static void main(String[] args) {
        Thermometer thermometer = new IOChannelThermometer();
        Heater heater = new IOChannelHeater();
        Regulator regulator = new Regulator(thermometer, heater);

        regulator.regulate(20, 25);
    }
}

