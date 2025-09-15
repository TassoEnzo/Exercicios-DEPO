package br.edu.bra.ifsp;

public class Main {
    public static void main(String[] args) {
        SmartModem modem = new SmartModem();
        modem.dial("123456789");
        modem.send('X');
        char recebido = modem.recv();
        System.out.println("Caractere recebido: " + recebido);
        modem.hangup();
    }
}

