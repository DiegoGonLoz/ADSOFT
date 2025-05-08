package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraphDecorator<T> implements StateGraphInterface<T>{
    private final StateGraphInterface<T> wrapped;

    public StateGraphDecorator(StateGraphInterface<T> wrapped){
        this.wrapped = wrapped;
    }

    @Override
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode {
        return wrapped.addNode(node, operator);
    }

    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node) {
        return wrapped.createNode(node);
    }

    @Override
    public <S> WfNodeInterface<T, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode {
        return wrapped.addWfNode(node, wf);
    }

    @Override
    public <S> WfNodeInterface<T, S> createWfNode(String node, StateGraphInterface<S> wf) {
        return wrapped.createWfNode(node, wf);
    }

    @Override
    public StateGraphInterface<T> addEdge(String origin, String destination) {
        return wrapped.addEdge(origin, destination);
    }

    @Override
    public StateGraphInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition) {
        return wrapped.addConditionalEdge(origin, destination, condition);
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
}
