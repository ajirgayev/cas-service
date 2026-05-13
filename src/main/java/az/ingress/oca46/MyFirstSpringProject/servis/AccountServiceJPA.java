package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import az.ingress.oca46.MyFirstSpringProject.dto.request.AccountRequestDto;
import az.ingress.oca46.MyFirstSpringProject.dto.response.AccountResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Account;
import az.ingress.oca46.MyFirstSpringProject.entity.Customer;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.AccountNotFound;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.CustomerNotFound;
import az.ingress.oca46.MyFirstSpringProject.repository.AccountRepository;
import az.ingress.oca46.MyFirstSpringProject.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceJPA {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;

    public AccountResponseDto create(AccountRequestDto ard, long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFound(String.valueOf(customerId)));
        Account account = Account.builder()
                .customer(customer)
                .accountType(ard.getAccountType())
                .currency(ard.getCurrency())
                .status(Status.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Account saved = accountRepository.save(account);
        return modelMapper.map(saved, AccountResponseDto.class);
    }

    public List<Account> get() {
        return accountRepository.findAll();
    }

    public Account getById(long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFound(id + "id-li Account tapilmadi"));
    }

    public AccountResponseDto update(long id, AccountRequestDto ard) {
        Account oldAccount = getById(id);
        oldAccount.setAccountType(ard.getAccountType());
        oldAccount.setCurrency(ard.getCurrency());
        oldAccount.setUpdatedAt(LocalDateTime.now());
        accountRepository.save(oldAccount);
        return modelMapper.map(oldAccount, AccountResponseDto.class);
    }

    public void delete(long id) {
        Account oldAccount = getById(id);
        oldAccount.setStatus(Status.DELETE);
        accountRepository.save(oldAccount);
    }
}
