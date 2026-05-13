package az.ingress.oca46.MyFirstSpringProject.exception.myexceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("Istifadəçi adı tapılmadı " + message);
    }
}
