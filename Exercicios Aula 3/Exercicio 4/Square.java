package br.edu.bra.ifsp;

public class Square implements Shape {
    public void draw() {
        System.out.println("Desenha um quadrado");
    }

    public String getName() {
        return "Square";
    }
}