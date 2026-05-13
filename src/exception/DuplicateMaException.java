package exception;
public class DuplicateMaException extends RuntimeException{
    public DuplicateMaException(String message) {
        super(message);
    }

    public DuplicateMaException() {
        super("Mã tuyến đã tồn tại trong hệ thống!");
    }
}
