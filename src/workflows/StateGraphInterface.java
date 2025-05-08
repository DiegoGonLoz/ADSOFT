package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface StateGraphInterface<T>{
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode;

    public NodeInterface<T> createNode(NodeInterface<T> node);

    public <S> WfNodeInterface<T, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode;

    public <S> WfNodeInterface<T, S> createWfNode(String name, StateGraphInterface<S> wf);

    public StateGraphInterface<T> addEdge(String origin, String destination);

    public StateGraphInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition);

    public void setInitial(String node);

    public void setFinal(String node);

    public T run(T input, boolean debug);

    public String toString();
}
