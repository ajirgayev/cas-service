package az.ingress.oca46.MyFirstSpringProject.controller;

import az.ingress.oca46.MyFirstSpringProject.dto.request.AccountRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.AccountResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Account;
import az.ingress.oca46.MyFirstSpringProject.servis.AccountServiceJPA;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/account")
@Validated
public class AccountController {

    private final AccountServiceJPA accountServiceJPA;

    @PostMapping("customer/{customerId}")
    public ResponseEntity<AccountResponseDto> create(@PathVariable long customerId,@Valid @RequestBody AccountRequestDto ard) {
        return ResponseEntity.ok(accountServiceJPA.create(ard, customerId));
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(accountServiceJPA.get());
    }

    @GetMapping("/{id}")
    public Account getById(@Validated @Positive(message = "id menfi ola bilmez") @PathVariable long id) {
        return accountServiceJPA.getById(id);
    }

    @PutMapping("/{id}")
    public AccountResponseDto update(@Validated @Positive(message = "id menfi ola bilmez") @PathVariable long id, @Valid @RequestBody AccountRequestDto ard) {
        return accountServiceJPA.update(id, ard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        accountServiceJPA.delete(id);
        return ResponseEntity.status(204).build();
    }
}
