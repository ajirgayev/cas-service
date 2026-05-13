package az.ingress.oca46.MyFirstSpringProject.exception;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(long aliasid, long accountId) {
        this(aliasid + " " + accountId);
    }

    public AlreadyExistException(String message) {
        super("Account and Alias alredy linked " + message);
    }
}
