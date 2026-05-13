package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import az.ingress.oca46.MyFirstSpringProject.dto.request.CustomerRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.CustomerResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Customer;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.CustomerNotFound;
import az.ingress.oca46.MyFirstSpringProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceJPA {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResponseDto create(CustomerRequestDto crd) {
        Customer c = Customer.builder()
                .firstName(crd.getFirstName())
                .lastName(crd.getLastName())
                .status(Status.ACTIVE)
                .email(crd.getEmail())
                .phone(crd.getPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Customer result = customerRepository.save(c);
        return modelMapper.map(result, CustomerResponseDto.class);
    }

    public List<Customer> get() {
        return customerRepository.findAll();
    }

    public Customer getById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFound(String.valueOf(id)));
    }

    public CustomerResponseDto update(long id, CustomerRequestDto crd) {
        Customer oldCustomer = getById(id);
        oldCustomer.setFirstName(crd.getFirstName());
        oldCustomer.setLastName(crd.getLastName());
        oldCustomer.setPhone(crd.getPhone());
        oldCustomer.setEmail(crd.getEmail());
        oldCustomer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(oldCustomer);
        return modelMapper.map(oldCustomer, CustomerResponseDto.class);
    }

    public void delete(long id) {
        Customer oldCustomer = getById(id);
        oldCustomer.setStatus(Status.DELETE);
        customerRepository.save(oldCustomer);
    }
}
