package az.ingress.oca46.MyFirstSpringProject.exception.myexceptions;

public class AlreadyUserExistException extends RuntimeException{
    public AlreadyUserExistException(String message) {
        super(" Istifadechi adi artiq movcuddur " + message);
    }
}
