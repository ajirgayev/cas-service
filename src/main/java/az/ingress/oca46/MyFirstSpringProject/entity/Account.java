package az.ingress.oca46.MyFirstSpringProject.entity;

import az.ingress.oca46.MyFirstSpringProject.dto.AccountType;
import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Account_Info")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private String currency;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    Customer customer;

    @JsonProperty("customerId")
    public long getCustomerId(){
        return customer.getCustomerId();
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
