package workflows;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface NodeInterface<T> {

    public void setOperator(Consumer<? super T> operator);

    public void run(T input);

    public void addEdge(String node, Predicate<T> condition);

    public LinkedHashMap<String, Predicate<T>> getEdges();

    public String getName();

    public String toString();
}
