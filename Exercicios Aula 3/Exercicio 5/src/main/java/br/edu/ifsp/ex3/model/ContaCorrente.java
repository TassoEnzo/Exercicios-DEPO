package br.edu.ifsp.ex3.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CORRENTE")
public class ContaCorrente extends Conta implements ContaSacavel {

    public ContaCorrente() {}

    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public void sacar(double valor) {
        if (valor > getSaldo()) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.saldo -= valor;
    }
}
