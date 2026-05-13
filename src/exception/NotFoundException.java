package exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Không tìm thấy dữ liệu yêu cầu!");
    }
}
