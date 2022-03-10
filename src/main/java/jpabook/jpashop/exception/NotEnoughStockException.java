package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException{

    //메시지 넘기기위해서 오버라이드 해줘야한당.

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
