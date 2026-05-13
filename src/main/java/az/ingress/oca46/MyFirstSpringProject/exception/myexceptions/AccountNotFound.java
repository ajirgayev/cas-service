package az.ingress.oca46.MyFirstSpringProject.exception.myexceptions;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound(String message) {
        super(message);
    }
}
