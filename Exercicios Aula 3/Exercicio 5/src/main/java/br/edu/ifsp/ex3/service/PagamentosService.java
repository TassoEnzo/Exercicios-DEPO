package br.edu.ifsp.ex3.service;

import org.springframework.stereotype.Service;

import br.edu.ifsp.ex3.model.Conta;
import br.edu.ifsp.ex3.model.ContaSacavel;

@Service
public class PagamentosService {
    public void pagarConta(Conta conta, double valor) {
        if (!(conta instanceof ContaSacavel sacavel)) {
            throw new UnsupportedOperationException("Esta conta não permite saques");
        }
        System.out.println("Saldo antes: " + conta.getSaldo());
        sacavel.sacar(valor);
        System.out.println("Saldo depois: " + conta.getSaldo());
    }
}

