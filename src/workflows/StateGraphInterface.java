package workflows;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface StateGraphInterface<T> extends Consumer<T>{
    StateGraph<T> addNode(String node, Consumer<T> operator);

    <S> WfNode<T, S> addWfNode(String node, StateGraph<S> wf);

    StateGraph<T> addEdge(String origin, String destination);

    StateGraph<T> addConditionalEdge(String origin, String destination, Predicate<T> condition);

    void setInitial(String node);

    void setFinal(String node);

    void accept(T input);

    T run(T input, boolean debug);
}
