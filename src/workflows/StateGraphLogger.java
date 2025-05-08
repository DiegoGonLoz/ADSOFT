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
     * @param wrapped grafo empleado
     * @param file fichero asociado
     */
    public StateGraphLogger(StateGraphInterface<T> wrapped, String file) {
        super(wrapped);

        this.file = file;

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
