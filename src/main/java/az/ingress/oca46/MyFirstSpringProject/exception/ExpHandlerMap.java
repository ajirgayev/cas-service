package az.ingress.oca46.MyFirstSpringProject.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpHandlerMap {
    private LocalDateTime timestamp;
    private int errorCode;
    private Map<String, String> errorDescription;
    private String status;
    private String path;
}
