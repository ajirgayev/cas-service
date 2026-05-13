package az.ingress.oca46.MyFirstSpringProject.dto.response;

import az.ingress.oca46.MyFirstSpringProject.dto.AccountType;
import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDto {
    private long accountId;
    private AccountType accountType;
    private long customerId;
    private String currency;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
