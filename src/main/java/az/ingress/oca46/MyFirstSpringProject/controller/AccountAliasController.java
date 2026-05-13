package az.ingress.oca46.MyFirstSpringProject.controller;

import az.ingress.oca46.MyFirstSpringProject.dto.response.AccountResponseDto;
import az.ingress.oca46.MyFirstSpringProject.servis.AccountAliasService;
import az.ingress.oca46.MyFirstSpringProject.servis.AccountServiceJPA;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/account-alias")
public class AccountAliasController {
    private final AccountAliasService accountAliasService;
    @PutMapping("/account/{accountId}/link/alias/{aliasId}")
    public ResponseEntity<Void> linkAccountToAlias(@PathVariable long accountId, @PathVariable long aliasId){
        accountAliasService.link(aliasId, accountId);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/account/{accountId}/link/alias/{aliasId}")
        public void deLink(@PathVariable long accountId, @PathVariable long aliasId){
        accountAliasService.delink(accountId, aliasId);
    }

    @PutMapping("/account/{accountId}/setDefault/alias/{aliasId}")
    public void setDefault(@PathVariable long accountId, @PathVariable long aliasId){
        accountAliasService.setDefault(aliasId,accountId);
    }

    @GetMapping("/alias/{aliasId}")
    public AccountResponseDto getDefaultAccount(@PathVariable long aliasId){
        return accountAliasService.getDefaultAccount(aliasId);
    }
}
