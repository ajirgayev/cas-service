package az.ingress.oca46.MyFirstSpringProject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpHandler {
    private LocalDateTime timestamp;
    private int errorCode;
    private String errorDescription;
    private String status;
    private String path;
}
