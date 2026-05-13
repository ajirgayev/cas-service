package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import az.ingress.oca46.MyFirstSpringProject.dto.request.AliasRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.AliasResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Alias;
import az.ingress.oca46.MyFirstSpringProject.entity.Customer;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.CustomerNotFound;
import az.ingress.oca46.MyFirstSpringProject.repository.AliasRepository;
import az.ingress.oca46.MyFirstSpringProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AliasServiceJPA {

    private final AliasRepository aliasRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public AliasResponseDto create(AliasRequestDto ard, long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFound(String.valueOf(customerId)));
        Alias alias = Alias.builder()
                .aliasType(ard.getAliasType())
                .customer(customer)
                .aliasValue(ard.getAliasValue())
                .status(Status.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Alias saved = aliasRepository.save(alias);
        return modelMapper.map(saved, AliasResponseDto.class);
    }

    public List<Alias> get() {
        return aliasRepository.findAll();
    }

    public Alias getById(long id) {
        return aliasRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Alias tapılmadı: " + id));
    }

    public AliasResponseDto update(long id, AliasRequestDto ard) {
        Alias oldAlias = getById(id);
        oldAlias.setAliasType(ard.getAliasType());
        oldAlias.setAliasValue(ard.getAliasValue());
        oldAlias.setUpdatedAt(LocalDateTime.now());
        aliasRepository.save(oldAlias);
        return modelMapper.map(oldAlias, AliasResponseDto.class);
    }

    public void delete(long id) {
        Alias oldAlias = getById(id);
        oldAlias.setStatus(Status.DELETE);
        aliasRepository.save(oldAlias);
    }
}
