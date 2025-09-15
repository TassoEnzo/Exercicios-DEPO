package br.edu.bra.ifsp.persistence;

import br.edu.bra.ifsp.model.Employee;

public class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Salvando funcionário: " + employee.getName());
        // Simulação de persistência
    }

    public Employee load(String name) {
        System.out.println("Carregando funcionário: " + name);
        return new Employee(name, 3000.0); // Simulação
    }
}
