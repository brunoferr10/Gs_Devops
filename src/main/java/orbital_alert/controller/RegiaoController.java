package orbital_alert.controller;

import orbital_alert.model.Regiao;
import orbital_alert.repository.RegiaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regioes")
public class RegiaoController {

    private final RegiaoRepository repository;

    public RegiaoController(RegiaoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Regiao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Regiao criar(@RequestBody Regiao regiao) {
        return repository.save(regiao);
    }

    @PutMapping("/{id}")
    public Regiao atualizar(@PathVariable Long id, @RequestBody Regiao novaRegiao) {
        Regiao regiao = repository.findById(id).orElseThrow();

        regiao.setNome(novaRegiao.getNome());
        regiao.setCidade(novaRegiao.getCidade());
        regiao.setEstado(novaRegiao.getEstado());
        regiao.setNivelRisco(novaRegiao.getNivelRisco());

        return repository.save(regiao);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}