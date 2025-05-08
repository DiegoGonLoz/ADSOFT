package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface WorkflowInterface<T>{
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode;

    public <S> WfNodeInterface<T, S> addWfNode(String node, WorkflowInterface<S> wf) throws AlreadyExistingNode;

    public WorkflowInterface<T> addEdge(String origin, String destination);

    public WorkflowInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition);

    public void setInitial(String node);

    public void setFinal(String node);

    public T run(T input, boolean debug);

    public String toString();
}
