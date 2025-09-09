package br.edu.bra.ifsp;

public class Triangle implements Shape {
    public void draw() {
        System.out.println("Desenha um triângulo");
    }

    public String getName() {
        return "Triangle";
    }
}