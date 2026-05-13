package az.ingress.oca46.MyFirstSpringProject.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @Size(min = 4, max = 12)
    @Pattern(regexp = "^[A-Za-z_]+$")
    private String username;

    @Size(min = 4, max = 8)
    @Pattern(regexp = "^(?:\\d{4}|(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,})$")
    private String password;
}
