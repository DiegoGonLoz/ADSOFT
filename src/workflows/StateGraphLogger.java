package workflows;

public class StateGraphLogger<T> extends StateGraphDecorator<T> {
    private final String file;

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
