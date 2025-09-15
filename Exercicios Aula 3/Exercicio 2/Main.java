package br.edu.bra.ifsp;

import br.edu.bra.ifsp.facade.EmployeeService;

public class Main {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        service.promoteEmployee("Enzo", 500.0);
        service.processPayroll("Enzo");
    }
}


