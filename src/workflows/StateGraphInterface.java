package workflows;

import myExceptions.AlreadyExistingNode;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface StateGraphInterface<T> extends NodeFactory<T> {
    StateGraphInterface<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode;

    <S> WfNodeInterface<T, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode;

    StateGraphInterface<T> addEdge(String origin, String destination);

    StateGraphInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition);

    void setInitial(String node);
    void setFinal(String node);
    T run(T input, boolean debug);

    NodeFactory<T> getNodeFactory();
    StateGraphInterface<T> setNodeFactory(NodeFactory<T> factory);
}
