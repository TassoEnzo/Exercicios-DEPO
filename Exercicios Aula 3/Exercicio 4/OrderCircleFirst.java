package br.edu.bra.ifspt;

import java.util.Comparator;
import java.util.List;

public class OrderCircleFirst implements ShapeOrderingStrategy {
    public List<Shape> order(List<Shape> shapes) {
        shapes.sort(Comparator.comparing(s -> {
            switch (s.getName()) {
                case "Circle": return 0;
                case "Square": return 1;
                case "Triangle": return 2;
                default: return 3;
            }
        }));
        return shapes;
    }
}