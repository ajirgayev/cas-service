package az.ingress.oca46.MyFirstSpringProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data

public class AccountAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Alias alias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    LocalDateTime linkDateTime;

    @Builder.Default
    private boolean isDefault = false;

    @JsonProperty("aliasId")
    public long getAliasId(){
        return alias.getAliasId();
    }

    @JsonProperty("accountId")
    public long getAccountId(){
        return account.getAccountId();
    }
}
