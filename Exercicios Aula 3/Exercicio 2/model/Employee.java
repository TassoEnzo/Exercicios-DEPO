package br.edu.bra.ifsp.model;

public class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void promote(double increase) {
        this.salary += increase;
    }

    public double calculatePay() {
        return salary * 0.9; // Exemplo: descontando impostos
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

