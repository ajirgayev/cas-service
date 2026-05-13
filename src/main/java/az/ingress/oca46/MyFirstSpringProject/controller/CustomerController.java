package az.ingress.oca46.MyFirstSpringProject.controller;

import az.ingress.oca46.MyFirstSpringProject.dto.request.CustomerRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.CustomerResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Customer;
import az.ingress.oca46.MyFirstSpringProject.servis.CustomerServiceJPA;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/customer")
@Tag(name = "Customer Qeydiyyat xidmeti", description = "Bu xidmet mushterileri cas-da qeydiyyatdan kechirmek uchundiur")
public class CustomerController {

    private final CustomerServiceJPA customerServiceJPA;

    @PostMapping
    @Operation(description = "Cas-da Musteri yaratmaq ",
    summary = "Musteri qeydiyyat xulasesi")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Ugurlu qeydiyyat cavabi"),
            @ApiResponse(responseCode = "400", description = "Xetali muraciet"),
            @ApiResponse(responseCode = "404", description = "Tapilmadi")
    })
    public ResponseEntity<CustomerResponseDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Musteri melumatlari obyekti")
            @Valid @RequestBody CustomerRequestDto crd) {
        return ResponseEntity.ok(customerServiceJPA.create(crd));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerServiceJPA.get());
    }

    @GetMapping("/{id}")
    public Customer getById(@Parameter(
            required = true,
            example = "1",
            description = "Musteri id si mutleq qeyd olunmalidir"
    ) @PathVariable long id) {
        return customerServiceJPA.getById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(@PathVariable long id, @Valid @RequestBody CustomerRequestDto crd) {
        return customerServiceJPA.update(id, crd);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        customerServiceJPA.delete(id);
        return ResponseEntity.status(204).build();
    }
}
