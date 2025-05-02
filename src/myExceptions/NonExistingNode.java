package myExceptions;

public class NonExistingNode extends RuntimeException {
    public NonExistingNode(String message) {
        super(message);
    }
}
