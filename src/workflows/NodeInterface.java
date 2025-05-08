package workflows;

import java.util.LinkedHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Interfaz para nodos
 * @param <T> objeto asociado al nodo
 * @author Diego Gonzalez
 */
public interface NodeInterface<T> {
    /**
     * Setter del operator
     * @param operator operator a configurar
     */
    public void setOperator(Consumer<? super T> operator);

    /**
     * Metodo run
     * @param input input del metodo run
     */
    public void run(T input);

    /**
     * Metodo para añadir un edge
     * @param node nodo a añadir
     * @param condition condición a añadir
     */
    public void addEdge(String node, Predicate<T> condition);

    /**
     * Getter del edges
     * @return objeto edges
     */
    public LinkedHashMap<String, Predicate<T>> getEdges();

    /**
     * Getter de name
     * @return nombre del nodo
     */
    public String getName();

    /**
     * Metodo toString de un nodo
     * @return String con la información del nodo
     */
    public String toString();
}
