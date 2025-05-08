package workflows;

import myExceptions.AlreadyExistingNode;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase tipo grafo decorado
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class StateGraphDecorator<T> implements StateGraphInterface<T>{
    /** Interfaz wrapped */
    private final StateGraphInterface<T> wrapped;

    /**
     * Constructor de la clase StateGraphDecorator
     * @param wrapped interfaz wrapped
     */
    public StateGraphDecorator(StateGraphInterface<T> wrapped){
        this.wrapped = wrapped;
    }

    /**
     * Metodo para añadir un nodo
     * @param node nodo a añadir
     * @param operator operador
     * @return grafo modificado
     * @throws AlreadyExistingNode Excepción al añadir un nodo ya existente
     */
    @Override
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode {
        return wrapped.addNode(node, operator);
    }

    /**
     * Metodo para crear un nodo
     * @param node nodo a crear
     * @return nodo
     */
    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node) {
        return wrapped.createNode(node);
    }

    /**
     * Metodo para añadir un WfNode
     * @param node nodo a añadir
     * @param wf objeto tipo interfaz de grafo
     * @return objeto tipo interfaz WfNodeInterface
     * @param <S> objeto asociado al grafo
     */
    @Override
    public <S> WfNodeInterface<T, S> addWfNode(String node, StateGraphInterface<S> wf) throws AlreadyExistingNode {
        return wrapped.addWfNode(node, wf);
    }

    /**
     * Metodo para crear un WfNode
     * @param node nodo a crear
     * @param wf objeto tipo interfaz de grafo
     * @return nuevo nodo creado
     * @param <S> objeto asociado al grafo
     */
    @Override
    public <S> WfNodeInterface<T, S> createWfNode(String node, StateGraphInterface<S> wf) {
        return wrapped.createWfNode(node, wf);
    }

    /**
     * Metodo para añadir un edge
     * @param origin origen del edge
     * @param destination destino del edge
     * @return grafo modificado
     */
    @Override
    public StateGraphInterface<T> addEdge(String origin, String destination) {
        return wrapped.addEdge(origin, destination);
    }

    /**
     * Metodo para añadir un edge con condición
     * @param origin origen del edge
     * @param destination destino del edge
     * @param condition condición a aplicar
     * @return grafo modificado
     */
    @Override
    public StateGraphInterface<T> addConditionalEdge(String origin, String destination, Predicate<T> condition) {
        return wrapped.addConditionalEdge(origin, destination, condition);
    }

    /**
     * Metodo para configurar un nodo como inicial
     * @param node nodo a configurar
     */
    @Override
    public void setInitial(String node) {
        wrapped.setInitial(node);
    }

    /**
     * Metodo para configurar un nodo como final
     * @param node nodo a configurar
     */
    @Override
    public void setFinal(String node) {
        wrapped.setFinal(node);
    }

    /**
     * Metodo run
     * @param input input a correr
     * @param debug booleano para saber si hay que debuggear o no
     * @return objeto tipo input
     */
    @Override
    public T run(T input, boolean debug) {
        return wrapped.run(input, debug);
    }
}
