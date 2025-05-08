package workflows;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NodeDecorator<T> implements NodeInterface<T>{
    private final NodeInterface<T> wrapped;

    public NodeDecorator(NodeInterface<T> wrapped){
        this.wrapped = wrapped;
    }
    @Override
    public void setOperator(Consumer<? super T> operator) {
        wrapped.setOperator(operator);
    }

    @Override
    public void run(T input) {
        wrapped.run(input);
    }

    @Override
    public void addEdge(String node, Predicate<T> condition) {
        wrapped.addEdge(node, condition);
    }

    @Override
    public LinkedHashMap<String, Predicate<T>> getEdges() {
        return wrapped.getEdges();
    }

    @Override
    public String getName(){
        return wrapped.getName();
    }

    @Override
    public String toString(){
        return wrapped.toString();
    }
}
