package br.edu.bra.ifsp;

import java.util.*;

public class Main {
    public static void drawAllShapes(List<Shape> shapes) {
        for (Shape s : shapes) {
            s.draw();
        }
    }

    public static void main(String[] args) {
        List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle());

        ShapeOrderingStrategy strategy = new OrderRectangleFirst();

        List<Shape> orderedShapes = strategy.order(shapes);
        drawAllShapes(orderedShapes);
    }

    /*
     * Por que agora conseguimos adicionar ordem sem modificar a main?
     * Porque usamos o padrão Strategy para encapsular a lógica de ordenação.
     * A main depende apenas da abstração ShapeOrderingStrategy.
     *
     * Como isso se relaciona com o princípio Aberto/Fechado?
     * O código está aberto para extensão, mas fechado para modificação.
     * Isso torna o sistema flexível e preparado para mudanças futuras.
     */
}

