package az.ingress.oca46.MyFirstSpringProject.dto.response;

import az.ingress.oca46.MyFirstSpringProject.dto.Status;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDto {
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Status status;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
