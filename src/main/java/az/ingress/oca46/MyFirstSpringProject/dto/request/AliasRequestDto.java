package az.ingress.oca46.MyFirstSpringProject.dto.request;

import az.ingress.oca46.MyFirstSpringProject.dto.AliasType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AliasRequestDto {

    @NotNull(message = "Alias tipi boş ola bilməz")
    private AliasType aliasType;

    @NotBlank(message = "Alias dəyəri boş ola bilməz")
    @Size(min = 2, max = 100, message = "Alias dəyəri 2-100 simvol arasında olmalıdır")
    private String aliasValue;

    @PastOrPresent(message = "Yaradılma tarixi gələcəkdə ola bilməz")
    LocalDateTime createdAt;
}
