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
    /** Mapa edges */
    private final LinkedHashMap<String, Predicate<T>> edges = new LinkedHashMap<>();

    /**
     * Constructor de la clase Node
     * @param name nombre del nodo
     */
    public Node(String name){
        this.name = name;
    }

    /**
     * Setter del operator
     * @param operator operator a configurar
     */
    @Override
    public void setOperator(Consumer<? super T> operator){
        this.operator = operator;
    }

    /**
     * Metodo run
     * @param input input del metodo run
     */
    @Override
    public void run(T input){
        operator.accept(input);
    }

    /**
     * Metodo para añadir un edge
     * @param node nodo a añadir
     * @param condition condición a añadir
     */
    @Override
    public void addEdge(String node, Predicate<T> condition){
        if(edges.containsKey(node)){
            return;
        }
        edges.put(node, condition);
    }

    /**
     * Getter del edges
     * @return objeto edges
     */
    @Override
    public LinkedHashMap<String, Predicate<T>> getEdges(){
        return new LinkedHashMap<String, Predicate<T>>(edges);
    }

    /**
     * Getter de name
     * @return nombre del nodo
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * Metodo toString de Node
     * @return String con la información del nodo
     */
    @Override
    public String toString(){
        return "Node " + name + " (" + edges.keySet().size() + " output nodes)";
    }
}
