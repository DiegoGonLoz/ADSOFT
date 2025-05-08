package workflows;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase que representa un nodo
 * @param <T> objeto asociado al nodo
 * @author Diego Gonzalez
 */
public class Node<T> implements NodeInterface<T>{
    /** Nombre del nodo */
    private String name;
    /** Operador de la clase */
    private Consumer<? super T> operator;
    private final LinkedHashMap<String, Predicate<T>> edges = new LinkedHashMap<>();

    public Node(String name){
        this.name = name;
    }

    @Override
    public void setOperator(Consumer<? super T> operator){
        this.operator = operator;
    }

    @Override
    public void run(T input){
        operator.accept(input);
    }

    @Override
    public void addEdge(String node, Predicate<T> condition){
        if(edges.containsKey(node)){
            return;
        }
        edges.put(node, condition);
    }

    @Override
    public LinkedHashMap<String, Predicate<T>> getEdges(){
        return new LinkedHashMap<String, Predicate<T>>(edges);
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Node " + name + " (" + edges.keySet().size() + " output nodes)";
    }
}
