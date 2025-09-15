package br.edu.ifsp.ex3.controller;

import br.edu.ifsp.ex3.model.Conta;
import br.edu.ifsp.ex3.repository.ContaRepository;
import br.edu.ifsp.ex3.service.PagamentosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaRepository contaRepository;
    private final PagamentosService pagamentosService;

    public ContaController(ContaRepository contaRepository, PagamentosService pagamentosService) {
        this.contaRepository = contaRepository;
        this.pagamentosService = pagamentosService;
    }

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Conta buscarConta(@PathVariable Long id) {
        return contaRepository.findById(id).orElseThrow();
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<String> depositar(@PathVariable Long id, @RequestParam double valor) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        conta.depositar(valor);
        contaRepository.save(conta);
        return ResponseEntity.ok("Depósito realizado com sucesso!");
    }

    @PostMapping("/{id}/pagar")
    public ResponseEntity<String> pagar(@PathVariable Long id, @RequestParam double valor) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        try {
            pagamentosService.pagarConta(conta, valor);
            contaRepository.save(conta);
            return ResponseEntity.ok("Pagamento realizado com sucesso!");
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}

