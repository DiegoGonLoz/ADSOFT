package myExceptions;

/**
 * Clase exception que salta al crear un nodo ya existente
 * @author Diego Gonzalez
 */
public class AlreadyExistingNode extends RuntimeException {
    /**
     * Constructor de AlreadyExistingNode
     * @param node nodo ya existente
     * @param graph grafo que contiene el nodo
     */
    public AlreadyExistingNode(String node, String graph) {
        super("Node " + node + " already exists in graph " + graph);
    }
}
