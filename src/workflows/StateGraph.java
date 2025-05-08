package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.LinkedHashMap;
import java.util.SequencedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase tipo grafo
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class StateGraph<T> implements StateGraphInterface<T> {
    /** Nombre */
    private final String name;
    /** Descripción */
    private final String description;
    /** Inicial */
    private String initial = null;
    /** Final */
    private String last = null;
    /** Pasos */
    private int steps;
    /** Mapa con los nodos */
    private final LinkedHashMap<String, NodeInterface<T>> nodes = new LinkedHashMap<>();
    private NodeFactory<T> nodeFactory = this;

    /**
     * Constructor de la clase StateGraph
     * @param name nombre del grafo
     * @param description descripción del grafo
     */
    public StateGraph(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Metodo para añadir un nodo
     * @param node nodo a añadir
     * @param operator operador
     * @return grafo modificado
     * @throws AlreadyExistingNode Excepción al añadir un nodo ya existente
     */
    @Override
    public StateGraphInterface<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode {
        if (nodes.containsKey(node)) {
            throw new AlreadyExistingNode(node, name);
        }
        NodeInterface<T> newNode = nodeFactory.createNode(node);
        newNode.setOperator(operator);
        nodes.put(node, newNode);
        return this;
    }

    /**
     * Metodo para crear un nodo
     * @param name nodo a crear
     * @return nodo
     */
    @Override
    public NodeInterface<T> createNode(String name) {
        return new Node<>(name);
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
        if (nodes.containsKey(node)) {
            throw new AlreadyExistingNode(node, name);
        }
        WfNodeInterface<T, S> wfNode = new WfNode<>(node, wf);
        NodeInterface<T> newNode = nodeFactory.createWfNode(wfNode);
        nodes.put(node, newNode);
        return wfNode;
    }

    /**
     * Metodo para crear un WfNode
     * @param wfNode objeto tipo interfaz de grafo
     * @return nuevo nodo creado
     * @param <S> objeto asociado al grafo
     */
    public <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode) {
        return wfNode;
    }

    /**
     * Metodo para añadir un edge
     * @param origin origen del edge
     * @param destination destino del edge
     * @return grafo modificado
     */
    @Override
    public StateGraphInterface<T> addEdge(String origin, String destination) {
        return addConditionalEdge(origin, destination, input -> true);
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
        if (!nodes.containsKey(origin)) {
            throw new NonExistingNode("Node " + origin + " does not exist in graph " + name);
        }
        if (!nodes.containsKey(destination)) {
            throw new NonExistingNode("Node " + destination + " does not exist in graph " + name);
        }
        nodes.get(origin).addEdge(destination, condition);
        return this;
    }

    /**
     * Metodo para configurar un nodo como inicial
     * @param node nodo a configurar
     */
    @Override
    public void setInitial(String node) {
        if (!nodes.containsKey(node)) {
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }
        this.initial = node;
    }

    /**
     * Metodo para configurar un nodo como final
     * @param node nodo a configurar
     */
    @Override
    public void setFinal(String node) {
        if (!nodes.containsKey(node)) {
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }
        this.last = node;
    }

    /**
     * Metodo run
     * @param input input a correr
     * @param debug booleano para saber si hay que debuggear o no
     * @return objeto tipo input
     */
    @Override
    public T run(T input, boolean debug) {
        if (this.initial == null) {
            throw new NonExistingNode("Initial node does not exist in graph " + name);
        }
        steps = 1;
        if (debug) {
            System.out.println("- Step 1 (" + name + ") - input: " + input);
        }
        runSubtree(input, debug, initial);
        return input;
    }

    /**
     * Metodo toString de StateGraph
     * @return String con información relevante
     */
    @Override
    public String toString() {
        return "Workflow '" + name + "' (" + description + "): " +
                "\n- Nodes: " + nodes +
                "\n- Initial: " + initial +
                "\n- Final: " + last;
    }

    /**
     * Metodo para saber si existe un nodo
     * @param node nodo a comprobar
     * @return true o false según exista o no
     */
    private boolean existNode(String node){
        return nodes.containsKey(node);
    }

    /**
     * Metodo para correr un subárbol del principal
     * @param input input a ejecutar
     * @param debug true o false según se quiera debuggear
     * @param node nodo del que obtener el subárbol
     * @return árbol resultado
     */
    private T runSubtree(T input, boolean debug, String node) {
        T result = null;
        steps++;
        NodeInterface<T> runningNode = nodes.get(node);
        runningNode.run(input);
        if (debug) {
            System.out.println("- Step " + steps + ": (" + name + ") - " + node + " executed: " + input);
        }
        if (last != null && last.equals(node)) {
            return input;
        }
        LinkedHashMap<String, Predicate<T>> edges = runningNode.getEdges();
        SequencedSet<String> children = edges.sequencedKeySet();
        for (String child : children) {
            if (edges.get(child).test(input)) {
                result = runSubtree(input, debug, child);
                if (result != null) return result;
            }
        }
        return result;
    }

    @Override
    public NodeFactory<T> getNodeFactory() {
        return nodeFactory;
    }

    @Override
    public StateGraphInterface<T> setNodeFactory(NodeFactory<T> factory) {
        this.nodeFactory = factory;
        return this;
    }


}
