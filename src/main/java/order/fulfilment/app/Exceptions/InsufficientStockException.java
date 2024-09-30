package order.fulfilment.app.Exceptions;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException() {
        super("Insufficient stock to fulfill the order.");
    }
}
