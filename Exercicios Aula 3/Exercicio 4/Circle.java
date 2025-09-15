package br.edu.bra.ifsp;

public class Circle implements Shape {
    public void draw() {
        System.out.println("Desenha um círculo");
    }

    public String getName() {
        return "Circle";
    }
}