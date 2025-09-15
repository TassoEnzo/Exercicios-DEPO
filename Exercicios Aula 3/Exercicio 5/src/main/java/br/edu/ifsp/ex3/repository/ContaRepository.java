package br.edu.ifsp.ex3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifsp.ex3.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
