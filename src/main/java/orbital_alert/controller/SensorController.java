package orbital_alert.controller;

import orbital_alert.model.Regiao;
import orbital_alert.model.Sensor;
import orbital_alert.repository.RegiaoRepository;
import orbital_alert.repository.SensorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    private final SensorRepository sensorRepository;
    private final RegiaoRepository regiaoRepository;

    public SensorController(SensorRepository sensorRepository, RegiaoRepository regiaoRepository) {
        this.sensorRepository = sensorRepository;
        this.regiaoRepository = regiaoRepository;
    }

    @GetMapping
    public List<Sensor> listar() {
        return sensorRepository.findAll();
    }

    @PostMapping("/regiao/{regiaoId}")
    public Sensor criar(@PathVariable Long regiaoId, @RequestBody Sensor sensor) {
        Regiao regiao = regiaoRepository.findById(regiaoId).orElseThrow();
        sensor.setRegiao(regiao);
        return sensorRepository.save(sensor);
    }

    @PutMapping("/{id}/regiao/{regiaoId}")
    public Sensor atualizar(
            @PathVariable Long id,
            @PathVariable Long regiaoId,
            @RequestBody Sensor novoSensor
    ) {
        Sensor sensor = sensorRepository.findById(id).orElseThrow();
        Regiao regiao = regiaoRepository.findById(regiaoId).orElseThrow();

        sensor.setTipo(novoSensor.getTipo());
        sensor.setValorAtual(novoSensor.getValorAtual());
        sensor.setStatus(novoSensor.getStatus());
        sensor.setRegiao(regiao);

        return sensorRepository.save(sensor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sensorRepository.deleteById(id);
    }
}