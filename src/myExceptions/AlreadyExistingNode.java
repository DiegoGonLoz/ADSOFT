package myExceptions;

public class AlreadyExistingNode extends RuntimeException {
    public AlreadyExistingNode(String s) {
        super(s);
    }
}
