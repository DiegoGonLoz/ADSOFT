package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase que representa un streaming state graph
 * @param <T> objeto asociado
 */
public class StreamingStateGraph<T>{
    /** historial */
    private LinkedList<T> history = new LinkedList<T>();
    /** grafo */
    private StateGraph<List<T>> graph;

    /**
     * Constructor de la clase StreamingStateGraph
     * @param name nombre
     * @param description descripción
     */
    public StreamingStateGraph(String name, String description) {
        graph = new StateGraph<List<T>>(name, description);
    }

    /**
     * Metodo para obtener el historial
     * @return Lista de objetos
     */
    public List<T> history() {
        return Collections.unmodifiableList(history);
    }

    /**
     * Metodo para añadir un nodo
     * @param node nodo a añadir
     * @param operator operador
     * @return grafo modificado
     * @throws AlreadyExistingNode Excepción al añadir un nodo ya existente
     */
    public StateGraphInterface<List<T>> addNode(String node, Consumer<? super List<T>> operator) throws AlreadyExistingNode {
        return graph.addNode(node, operator);
    }

    /**
     * Metodo para añadir un WfNode
     * @param node nodo a añadir
     * @param wf objeto tipo interfaz de grafo
     * @return objeto tipo interfaz WfNodeInterface
     * @param <S> objeto asociado al grafo
     */
    public <S> WfNodeInterface<List<T>, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode {
        return graph.addWfNode(node, wf);
    }

    /**
     * Metodo para añadir un edge
     * @param origin origen del edge
     * @param destination destino del edge
     * @return grafo modificado
     */
    public StateGraphInterface<List<T>> addEdge(String origin, String destination) {
        return graph.addEdge(origin, destination);
    }

    /**
     * Metodo para añadir un edge con condición
     * @param origin origen del edge
     * @param destination destino del edge
     * @param condition condición a aplicar
     * @return grafo modificado
     */
    public StateGraphInterface<List<T>> addConditionalEdge(String origin, String destination, Predicate<List<T>> condition) {
        return graph.addConditionalEdge(origin, destination, condition);
    }

    /**
     * Metodo para configurar un nodo como inicial
     * @param node nodo a configurar
     */
    public void setInitial(String node) {
        graph.setInitial(node);
    }

    /**
     * Metodo para configurar un nodo como final
     * @param node nodo a configurar
     */
    public void setFinal(String node) {
        graph.setFinal(node);
    }

    /**
     * Metodo run
     * @param input input a ejecutar
     * @param debug true o false según haya que debuggear
     * @return lista de objetos
     */
    public List<T> run(List<T> input, boolean debug) {
        history.addAll(input);

        return graph.run(history, debug);
    }

    /**
     * Metodo run
     * @param input input a correr
     * @param debug booleano para saber si hay que debuggear o no
     * @return objeto tipo input
     */
    public T run(T input, boolean debug) {
        history.add(input);

        return graph.run(history, debug).getLast();
    }

    /**
     * Metodo toString de StreamingStateGraph
     * @return String con información relevante
     */
    @Override
    public String toString() {
        return graph.toString();
    }
}
