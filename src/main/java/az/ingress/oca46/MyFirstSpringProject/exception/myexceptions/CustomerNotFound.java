package az.ingress.oca46.MyFirstSpringProject.exception.myexceptions;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound(String message) {
        super("Customer Not Found " + message);
    }
}
