package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.LinkedHashMap;
import java.util.SequencedSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraph<T> implements StateGraphInterface<T> {
    private final String name;
    private final String description;
    private String initial = null;
    private String last = null;
    private int steps;

    private final LinkedHashMap<String, NodeInterface<T>> nodes = new LinkedHashMap<>();
    private NodeFactory<T> nodeFactory = this;

    public StateGraph(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    @Override
    public StateGraphInterface<T> addEdge(String origin, String destination) {
        return addConditionalEdge(origin, destination, input -> true);
    }

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

    @Override
    public void setInitial(String node) {
        if (!nodes.containsKey(node)) {
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }
        this.initial = node;
    }

    @Override
    public void setFinal(String node) {
        if (!nodes.containsKey(node)) {
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }
        this.last = node;
    }

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
    public NodeInterface<T> createNode(String name) {
        return new Node<>(name);
    }

    @Override
    public <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode) {
        return wfNode;
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

    @Override
    public String toString() {
        return "Workflow '" + name + "' (" + description + "): " +
                "\n- Nodes: " + nodes +
                "\n- Initial: " + initial +
                "\n- Final: " + last;
    }
}
