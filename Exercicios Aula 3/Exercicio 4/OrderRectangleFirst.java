package br.edu.bra.ifsp;

import java.util.Comparator;
import java.util.List;

public class OrderRectangleFirst implements ShapeOrderingStrategy {
    public List<Shape> order(List<Shape> shapes) {
        shapes.sort(Comparator.comparing(s -> {
            switch (s.getName()) {
                case "Square": return 0;
                case "Triangle": return 1;
                case "Circle": return 2;
                default: return 3;
            }
        }));
        return shapes;
    }
}

