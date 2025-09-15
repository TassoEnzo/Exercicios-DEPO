package br.edu.ifsp.ex3.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("POUPANCA")
public class ContaPoupanca extends Conta {

    public ContaPoupanca() {}

    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }
    // Não tem sacar(), respeita o LSP
}
