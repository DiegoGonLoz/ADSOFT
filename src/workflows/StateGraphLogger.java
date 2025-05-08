package workflows;

/**
 * Clase StateGraphLogger
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class StateGraphLogger<T> extends StateGraphDecorator<T>{
    /** Fichero */
    private final String file;

    /**
     * Constructor de la clase StateGraphLogger
     * @param graph grafo empleado
     * @param file fichero asociado
     */
    public StateGraphLogger(StateGraphInterface<T> graph, String file){
        super(graph);

        this.file = file;
    }

    /**
     * Metodo para crear un nodo
     * @param node nodo a crear
     * @return el nodo creado
     */
    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node){
        return new NodeLogger(super.createNode(node), file);
    }
}
