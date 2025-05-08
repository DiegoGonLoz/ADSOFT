package workflows;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase que representa un nodo decorado
 * @param <T> objeto asociado al nodo
 * @author Diego Gonzalez
 */
public class NodeDecorator<T> implements NodeInterface<T>{
    /** Interfaz wrapped */
    private final NodeInterface<T> wrapped;

    /**
     * Constructor de la clase NodeDecorator
     * @param wrapped interfaz wrapped
     */
    public NodeDecorator(NodeInterface<T> wrapped){
        this.wrapped = wrapped;
    }

    /**
     * Setter de operator
     * @param operator operator a configurar
     */
    @Override
    public void setOperator(Consumer<? super T> operator) {
        wrapped.setOperator(operator);
    }

    /**
     * Metodo run
     * @param input input del metodo run
     */
    @Override
    public void run(T input) {
        wrapped.run(input);
    }

    /**
     * Metodo para añadir un edge
     * @param node nodo a añadir
     * @param condition condición a añadir
     */
    @Override
    public void addEdge(String node, Predicate<T> condition) {
        wrapped.addEdge(node, condition);
    }

    /**
     * Getter del edges
     * @return objeto edges
     */
    @Override
    public LinkedHashMap<String, Predicate<T>> getEdges() {
        return wrapped.getEdges();
    }

    /**
     * Getter de name
     * @return nombre del nodo
     */
    @Override
    public String getName(){
        return wrapped.getName();
    }

    /**
     * Metodo toString de NodeDecorator
     * @return String con la información del nodo
     */
    @Override
    public String toString(){
        return wrapped.toString();
    }
}
