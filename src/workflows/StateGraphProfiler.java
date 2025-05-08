package workflows;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StateGraphProfiler<T> extends StateGraphDecorator<T> {
    private final List<String> history = new LinkedList<>();

    public StateGraphProfiler(StateGraphInterface<T> graph) {
        super(graph);

        NodeFactory<T> previousFactory = graph.getNodeFactory();
        NodeFactory<T> composed = new NodeFactory<>() {
            @Override
            public NodeInterface<T> createNode(String name) {
                return new NodeProfiler<>(previousFactory.createNode(name), StateGraphProfiler.this);
            }

            @Override
            public <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode) {
                return new NodeProfiler<>(previousFactory.createWfNode(wfNode), StateGraphProfiler.this);
            }
        };

        graph.setNodeFactory(composed);
    }

    public void addTrace(String trace) {
        this.history.add(trace);
    }

    public List<String> history() {
        return Collections.unmodifiableList(history);
    }
}
