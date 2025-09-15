package br.edu.bra.ifsp.facade;

import br.edu.bra.ifsp.model.Employee;
import br.edu.bra.ifsp.persistence.EmployeeRepository;

public class EmployeeService {
    private EmployeeRepository repository = new EmployeeRepository();

    public void promoteEmployee(String name, double increase) {
        Employee employee = repository.load(name);
        employee.promote(increase);
        repository.save(employee);
        System.out.println("Funcionário promovido: " + employee.getName());
        System.out.println("Novo salário: " + employee.getSalary());
    }

    public void processPayroll(String name) {
        Employee employee = repository.load(name);
        double pay = employee.calculatePay();
        System.out.println("Pagamento processado para " + employee.getName() + ": R$" + pay);
    }
}
