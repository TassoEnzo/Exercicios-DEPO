package br.edu.ifsp.ex3.regulator;

public class IOChannelThermometer implements Thermometer {
    @Override
    public double read() {
        return 18.5;
    }
}

