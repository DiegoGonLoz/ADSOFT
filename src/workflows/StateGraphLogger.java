package workflows;

public class StateGraphLogger<T> extends StateGraphDecorator<T>{
    private final String file;

    public StateGraphLogger(StateGraphInterface<T> graph, String file){
        super(graph);

        this.file = file;
    }

    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node){
        return new NodeLogger(super.createNode(node), file);
    }
}
