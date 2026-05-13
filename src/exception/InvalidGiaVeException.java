package exception;

public class InvalidGiaVeException extends RuntimeException {
    public InvalidGiaVeException(String message) {
        super(message);
    }

    public InvalidGiaVeException() {
        super("Giá vé không hợp lệ! Giá vé phải lớn hơn 0.");
    }
}
