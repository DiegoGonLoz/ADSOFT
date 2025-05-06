package myExceptions;

public class AlreadyExistingNode extends RuntimeException {
    public AlreadyExistingNode(String node, String graph) {
        super("Node " + node + " already exists in graph " + graph);
    }
}
