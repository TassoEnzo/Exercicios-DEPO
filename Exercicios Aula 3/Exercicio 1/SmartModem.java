package br.edu.bra.ifsp;

public class SmartModem implements ConnectionManager, DataTransmitter {
    public void dial(String phoneNumber) {
        System.out.println("Conectando ao número: " + phoneNumber);
    }

    public void hangup() {
        System.out.println("Desconectando...");
    }

    public void send(char c) {
        System.out.println("Enviando caractere: " + c);
    }

    public char recv() {
        System.out.println("Recebendo caractere...");
        return 'A'; // Simulação
    }
}
