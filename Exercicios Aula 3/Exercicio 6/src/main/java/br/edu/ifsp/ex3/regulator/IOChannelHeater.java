package br.edu.ifsp.ex3.regulator;

public class IOChannelHeater implements Heater {
    @Override
    public void engage() {
        System.out.println("Furnace ON");
    }

    @Override
    public void disengage() {
        System.out.println("Furnace OFF");
    }
}
