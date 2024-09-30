package order.fulfilment.app.Exceptions;

public class NoWarehouseAvailableException extends RuntimeException {
    public NoWarehouseAvailableException(String message) {
        super(message);
    }
}