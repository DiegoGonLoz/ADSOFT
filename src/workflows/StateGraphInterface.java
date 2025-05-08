package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Interfaz que representa un grafo
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public interface StateGraphInterface<T>{
    /**
     * Metodo para añadir un nodo
     * @param node nodo a añadir
     * @param operator operador
     * @return grafo modificado
     * @throws AlreadyExistingNode Excepción al añadir un nodo ya existente
     */
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode;

    /**
     * Metodo para crear un nodo
     * @param node nodo a crear
     * @return nodo
     */
    public NodeInterface<T> createNode(NodeInterface<T> node);

    /**
     * Metodo para añadir un WfNode
     * @param node nodo a añadir
     * @param wf objeto tipo interfaz de grafo
     * @return objeto tipo interfaz WfNodeInterface
     * @param <S> objeto asociado al grafo
     */
    public <S> WfNodeInterface<T, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode;

    /**
     * Metodo para crear un WfNode
     * @param name nodo a crear
     * @param wf objeto tipo interfaz de grafo
     * @return nuevo nodo creado
     * @param <S> objeto asociado al grafo
     */
    public <S> WfNodeInterface<T, S> createWfNode(String name, StateGraphInterface<S> wf);

    /**
     * Metodo para añadir un edge
     * @param origin origen del edge
     * @param destination destino del edge
     * @return grafo modificado
     */
    public StateGraphInterface<T> addEdge(String origin, String destination);

    /**
     * Metodo para añadir un edge con condición
     * @param origin origen del edge
     * @param destination destino del edge
     * @param condition condición a aplicar
     * @return grafo modificado
     */
    public StateGraphInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition);

    /**
     * Metodo para configurar un nodo como inicial
     * @param node nodo a configurar
     */
    public void setInitial(String node);

    /**
     * Metodo para configurar un nodo como final
     * @param node nodo a configurar
     */
    public void setFinal(String node);

    /**
     * Metodo run
     * @param input input a correr
     * @param debug booleano para saber si hay que debuggear o no
     * @return objeto tipo input
     */
    public T run(T input, boolean debug);

    /**
     * Metodo toString de StateGraphInterface
     * @return String con información relevante
     */
    public String toString();
}
