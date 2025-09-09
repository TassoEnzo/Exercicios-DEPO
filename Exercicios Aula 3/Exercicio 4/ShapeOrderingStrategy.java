package br.edu.bra.ifsp;

import java.util.List;

public interface ShapeOrderingStrategy {
    List<Shape> order(List<Shape> shapes);
}