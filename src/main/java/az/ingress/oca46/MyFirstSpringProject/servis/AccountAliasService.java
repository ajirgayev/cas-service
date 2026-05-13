package az.ingress.oca46.MyFirstSpringProject.servis;

import az.ingress.oca46.MyFirstSpringProject.dto.response.AccountResponseDto;
import az.ingress.oca46.MyFirstSpringProject.entity.Account;
import az.ingress.oca46.MyFirstSpringProject.entity.AccountAlias;
import az.ingress.oca46.MyFirstSpringProject.entity.Alias;
import az.ingress.oca46.MyFirstSpringProject.exception.AlreadyExistException;
import az.ingress.oca46.MyFirstSpringProject.exception.myexceptions.AccountNotFound;
import az.ingress.oca46.MyFirstSpringProject.repository.AccountAliasRepository;
import az.ingress.oca46.MyFirstSpringProject.repository.AccountRepository;
import az.ingress.oca46.MyFirstSpringProject.repository.AliasRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountAliasService {

    private final AliasRepository aliasRepository;
    private final AccountRepository accountRepository;
    private final AccountAliasRepository accountAliasRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void link(long aliasId, long accountId) {
        boolean isPresent = accountAliasRepository
                .findByAlias_AliasIdAndAccount_AccountId(aliasId, accountId)
                .isPresent();
        if (isPresent) throw new AlreadyExistException(aliasId, accountId);

        Alias alias = aliasRepository.findById(aliasId).orElseThrow(
                () -> new RuntimeException("Alias tapılmadı: " + aliasId));
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFound("Account tapılmadı: " + accountId));

        if (alias.getCustomerId() != account.getCustomerId()) {
            throw new IllegalArgumentException(
                    "Alias və Account eyni müştəriyə məxsus deyil. " +
                            "Alias customerId=" + alias.getCustomerId() +
                            ", Account customerId=" + account.getCustomerId());
        }

        accountAliasRepository.save(AccountAlias.builder()
                .account(account)
                .alias(alias)
                .linkDateTime(LocalDateTime.now())
                .isDefault(false)
                .build());
    }

    @Transactional
    public void setDefault(long aliasId, long accountId) {
        // Mövcud default-u sıfırla
        accountAliasRepository.findByAlias_AliasIdAndIsDefaultTrue(aliasId)
                .ifPresent(aa -> {
                    aa.setDefault(false);
                    accountAliasRepository.save(aa);
                });

        AccountAlias accountAlias = accountAliasRepository
                .findByAlias_AliasIdAndAccount_AccountId(aliasId, accountId)
                .orElseThrow(() -> new AlreadyExistException(
                        aliasId + " " + accountId + " link olunan məlumat yoxdur"));
        accountAlias.setDefault(true);
        accountAliasRepository.save(accountAlias);
    }

    public AccountResponseDto getDefaultAccount(long aliasId) {
        AccountAlias accountAlias = accountAliasRepository
                .findByAlias_AliasIdAndIsDefaultTrue(aliasId)
                .orElseThrow(() -> new RuntimeException(
                        "Alias " + aliasId + " üçün default account təyin edilməyib"));
        Account account = accountRepository.findById(accountAlias.getAccountId())
                .orElseThrow(() -> new AccountNotFound("Account tapılmadı"));
        return modelMapper.map(account, AccountResponseDto.class);
    }

    @Transactional
    public void delink(long accountId, long aliasId) {
        AccountAlias accountAlias = accountAliasRepository
                .findByAlias_AliasIdAndAccount_AccountId(aliasId, accountId)
                .orElseThrow(() -> new AlreadyExistException(
                        aliasId + " " + accountId + " link olunan məlumat yoxdur"));
        accountAliasRepository.delete(accountAlias);
    }
}
