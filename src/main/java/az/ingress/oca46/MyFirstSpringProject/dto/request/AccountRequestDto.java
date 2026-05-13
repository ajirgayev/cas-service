package az.ingress.oca46.MyFirstSpringProject.dto.request;

import az.ingress.oca46.MyFirstSpringProject.dto.AccountType;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDto {
//    @NotBlank(message = "Hesab tipi bosh ola bilmez")
    @NotNull(message = "Hesab tipi null ola bilmez")
    private AccountType accountType;

    @NotBlank (message = "currency bosh ola bilmez")
    @Size(min = 3, max = 3, message = "Currency uzunlugu 3 simvol olmalidir")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency tipi uygun deyil")
    private String currency;
}
