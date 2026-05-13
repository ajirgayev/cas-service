package az.ingress.oca46.MyFirstSpringProject.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDto {

    @NotBlank(message = "Ad boş ola bilməz")
    @Size(min = 2, max = 50, message = "Ad 2-50 simvol arasında olmalıdır")
    @Pattern(regexp = "^[a-zA-ZəƏğĞşŞıİöÖüÜçÇ\\s-]+$", message = "Ad yalnız hərf ola bilər")
    @Schema(description = "Musterinin adi", example = "Tofiq Acirqayev")
    private String firstName;

    @NotBlank(message = "Soyad boş ola bilməz")
    @Size(min = 2, max = 50, message = "Soyad 2-50 simvol arasında olmalıdır")
    @Pattern(regexp = "^[a-zA-ZəƏğĞşŞıİöÖüÜçÇ\\s-]+$", message = "Soyad yalnız hərf ola bilər")
    private String lastName;

    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Email formatı düzgün deyil")
    @Size(max = 100, message = "Email maksimum 100 simvol ola bilər")
    private String email;

    @NotBlank(message = "Telefon boş ola bilməz")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Telefon düzgün formatda deyil (nümunə: +994501234567)")
    private String phone;

    @PastOrPresent(message = "Yaradılma tarixi gələcəkdə ola bilməz")
    LocalDateTime createdAt;

    @PastOrPresent(message = "Yenilənmə tarixi gələcəkdə ola bilməz")
    LocalDateTime updatedAt;
}
