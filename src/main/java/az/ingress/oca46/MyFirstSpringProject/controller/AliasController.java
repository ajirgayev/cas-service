package az.ingress.oca46.MyFirstSpringProject.controller;

import az.ingress.oca46.MyFirstSpringProject.dto.request.AliasRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.AliasResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Alias;
import az.ingress.oca46.MyFirstSpringProject.servis.AliasServiceJPA;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/alias")
public class AliasController {

    private final AliasServiceJPA aliasServiceJPA;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<AliasResponseDto> create(@PathVariable long customerId, @Valid @RequestBody AliasRequestDto ard) {
        return ResponseEntity.ok(aliasServiceJPA.create(ard, customerId));
    }

    @GetMapping
    public ResponseEntity<List<Alias>> getAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(aliasServiceJPA.get());
    }

    @GetMapping("/{id}")
    public Alias getById(@PathVariable long id) {
        return aliasServiceJPA.getById(id);
    }

    @PutMapping("/{id}")
    public AliasResponseDto update(@PathVariable long id, @Valid @RequestBody AliasRequestDto ard) {
        return aliasServiceJPA.update(id, ard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        aliasServiceJPA.delete(id);
        return ResponseEntity.status(204).build();
    }
}
