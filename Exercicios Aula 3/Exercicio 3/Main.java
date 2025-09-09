package br.edu.bra.ifsp;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void drawAllShapes(List<Shape> shapes) {
        for (Shape s : shapes) {
            s.draw();
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle());
        drawAllShapes(shapes);
    }

    /*
     * Por que agora conseguimos adicionar o Triangle sem modificar a main?
     * Porque todas as formas implementam a interface Shape, e o método drawAllShapes
     * depende apenas dessa abstração. Basta criar uma nova classe que implemente Shape
     * e adicioná-la à lista.
     *
     * Como isso se relaciona com o princípio Aberto/Fechado?
     * O código está aberto para extensão, mas fechado para modificação.
     * Isso torna o sistema mais flexível, escalável e fácil de manter.
     */
}
