package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraphDecorator<T> implements StateGraphInterface<T> {
    protected final StateGraphInterface<T> wrapped;

    public StateGraphDecorator(StateGraphInterface<T> wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public StateGraphInterface<T> addNode(String node,
                                          Consumer<? super T> operator)
            throws AlreadyExistingNode {
        return wrapped.addNode(node, operator);
    }

    @Override
    public <S> WfNodeInterface<T, S> addWfNode(String node,
                                               StateGraphInterface<S> wf)
            throws AlreadyExistingNode {
        return wrapped.addWfNode(node, wf);
    }

    @Override
    public StateGraphInterface<T> addEdge(String o, String d) {
        return wrapped.addEdge(o, d);
    }

    @Override
    public StateGraphInterface<T> addConditionalEdge(String o, String d,
                                                     Predicate<T> cond) {
        return wrapped.addConditionalEdge(o, d, cond);
    }

    @Override
    public void setInitial(String node) {
        wrapped.setInitial(node);
    }

    @Override
    public void setFinal(String node) {
        wrapped.setFinal(node);
    }

    @Override
    public T run(T input, boolean debug) {
        return wrapped.run(input, debug);
    }

    @Override
    public StateGraphInterface<T> setNodeFactory(NodeFactory<T> factory) {
        return wrapped.setNodeFactory(factory);
    }

    @Override
    public NodeFactory<T> getNodeFactory() {
        return wrapped.getNodeFactory();
    }

    @Override
    public NodeInterface<T> createNode(String name) {
        return wrapped.createNode(name);
    }

    @Override
    public <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode) {
        return wrapped.createWfNode(wfNode);
    }

    @Override
    public String toString() {
        return wrapped.toString();
    }
}
