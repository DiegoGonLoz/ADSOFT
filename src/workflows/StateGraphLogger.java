package workflows;

/**
 * Clase StateGraphLogger
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class StateGraphLogger<T> extends StateGraphDecorator<T>{
    /** Fichero */
public class StateGraphLogger<T> extends StateGraphDecorator<T> {
    private final String file;

    /**
     * Constructor de la clase StateGraphLogger
     * @param graph grafo empleado
     * @param file fichero asociado
     */
    public StateGraphLogger(StateGraphInterface<T> graph, String file){
        super(graph);
    public StateGraphLogger(StateGraphInterface<T> wrapped, String file) {
        super(wrapped);

        this.file = file;

    /**
     * Metodo para crear un nodo
     * @param node nodo a crear
     * @return el nodo creado
     */
    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node){
        return new NodeLogger(super.createNode(node), file);
        NodeFactory<T> previousFactory = wrapped.getNodeFactory();
        NodeFactory<T> composed = new NodeFactory<>() {
            @Override
            public NodeInterface<T> createNode(String name) {
                return new NodeLogger<>(previousFactory.createNode(name), file);
            }

            @Override
            public <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode) {
                return new NodeLogger<>(previousFactory.createWfNode(wfNode), file);
            }
        };

        wrapped.setNodeFactory(composed);
    }
}
