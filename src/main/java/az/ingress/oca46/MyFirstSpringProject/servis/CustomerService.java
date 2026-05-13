package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import az.ingress.oca46.MyFirstSpringProject.dto.request.CustomerRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.CustomerResponseDto;
import az.ingress.oca46.MyFirstSpringProject.util.Helper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerService {
    private final Map<Long, CustomerResponseDto> customerRepo = new HashMap<>();
    public CustomerResponseDto create(CustomerRequestDto crd){
        long id = Helper.generateId();
        CustomerResponseDto customerResponseDto =  CustomerResponseDto
                .builder()
                .customerId(id)
                .firstName(crd.getFirstName())
                .lastName(crd.getLastName())
                .status(Status.ACTIVE)
                .email(crd.getEmail())
                .phone(crd.getPhone())
                .createdAt(crd.getCreatedAt())
                .updatedAt(crd.getUpdatedAt()).build();
        customerRepo.put(id, customerResponseDto);
        return customerResponseDto;
    }

    public List<CustomerResponseDto> get(){
        return customerRepo.values().stream().toList();
    }

    public CustomerResponseDto getById(long id){
       return  customerRepo
               .values()
               .stream()
               .filter(customerResponseDto -> customerResponseDto.getCustomerId()==id)
               .findFirst().get();
    }

    public CustomerResponseDto update(long id, CustomerRequestDto crd){
        CustomerResponseDto oldCustomer = getById(id);
        oldCustomer.setFirstName(crd.getFirstName());
        oldCustomer.setLastName(crd.getLastName());
        oldCustomer.setPhone(crd.getPhone());
        oldCustomer.setEmail(crd.getEmail());
        oldCustomer.setUpdatedAt(LocalDateTime.now());
        customerRepo.put(id,oldCustomer);
        return oldCustomer;
    }

    public void delete(long id){
        CustomerResponseDto oldCustomer = getById(id);
        oldCustomer.setStatus(Status.DELETE);
        customerRepo.put(id, oldCustomer);
    }
}
