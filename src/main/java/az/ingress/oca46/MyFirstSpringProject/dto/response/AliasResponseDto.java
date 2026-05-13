package az.ingress.oca46.MyFirstSpringProject.dto.response;

import az.ingress.oca46.MyFirstSpringProject.dto.AliasType;
import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AliasResponseDto {
    private long aliasId;
    private long customerId;
    private AliasType aliasType;
    private String aliasValue;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
