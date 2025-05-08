package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StreamingStateGraph<T>{
    private LinkedList<T> history = new LinkedList<T>();
    private StateGraph<List<T>> graph;

    public StreamingStateGraph(String name, String description) {
        graph = new StateGraph<List<T>>(name, description);
    }
    public List<T> history() {
        return Collections.unmodifiableList(history);
    }

    public StateGraph<List<T>> addNode(String node, Consumer<? super List<T>> operator) throws AlreadyExistingNode {
        return graph.addNode(node, operator);
    }

    public <S> WfNodeInterface<List<T>, S> addWfNode(String node, WorkflowInterface<S> wf) throws AlreadyExistingNode {
        return graph.addWfNode(node, wf);
    }

    public WorkflowInterface<List<T>> addEdge(String origin, String destination) {
        return graph.addEdge(origin, destination);
    }

    public WorkflowInterface<List<T>> addConditionalEdge(String origin, String destination, Predicate<List<T>> condition) {
        return graph.addConditionalEdge(origin, destination, condition);
    }

    public void setInitial(String node) {
        graph.setInitial(node);
    }

    public void setFinal(String node) {
        graph.setFinal(node);
    }

    public List<T> run(List<T> input, boolean debug) {
        history.addAll(input);

        return graph.run(history, debug);
    }

    public T run(T input, boolean debug) {
        history.add(input);

        return graph.run(history, debug).getLast();
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
