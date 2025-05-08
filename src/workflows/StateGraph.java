package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraph<T> implements WorkflowInterface<T> {
    private final String name;
    private final String description;
    private String initial = null;
    private String last = null;

    private int steps;

    private final LinkedHashMap<String, Node<T>> nodes = new LinkedHashMap<>();

    public StateGraph(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public StateGraph<T> addNode(String node, Consumer<? super T> operator) throws AlreadyExistingNode {
        if(existNode(node)){
            throw new AlreadyExistingNode(node, name);
        }

        Node<T> newNode = new Node<T>(node);
        newNode.setOperator(operator);

        nodes.put(node, newNode);

        return this;
    }

    @Override
    public <S> WfNode<T, S> addWfNode(String node, WorkflowInterface<S> wf) {
        if(existNode(node)){
            throw new AlreadyExistingNode(node, name);
        }

        WfNode<T, S>wfNode = new WfNode<>(node, wf);

        nodes.put(node, wfNode);

        return wfNode;
    }

    @Override
    public StateGraph<T> addEdge(String origin, String destination){
        return addConditionalEdge(origin, destination, (T input) -> true);
    }

    @Override
    public StateGraph<T> addConditionalEdge(String origin, String destination, Predicate<T> condition){
        if(!existNode(origin)){
            throw new NonExistingNode("Node " + origin + " does not exist in graph " + name);
        }

        if(!existNode(destination)){
            throw new NonExistingNode("Node " + destination + " does not exist in graph " + name);
        }

        nodes.get(origin).addEdge(destination, condition);

        return this;
    }

    @Override
    public void setInitial(String node){
        if(!existNode(node)){
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }

        this.initial = node;
    }

    @Override
    public void setFinal(String node){
        if(!existNode(node)){
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }

        this.last = node;
    }

    @Override
    public T run(T input, boolean debug){
        if(this.initial == null){
            throw new NonExistingNode("Initial node does not exist in graph " + name);
        }

        steps = 1;

        if(debug){
            System.out.println("- Step 1 ("+name+") - input: " + input);
        }

        runSubtree(input, debug, this.initial);

        return input;
    }

    @Override
    public String toString(){
        return "Workflow '" + name + "' (" + description + "): \n"+
                "- Nodes: "+ nodes + "\n"+
                "- Initial: " + initial +"\n"+
                "- Final: " + last;
    }

    private boolean existNode(String node){
        return nodes.containsKey(node);
    }

    private T runSubtree(T input, boolean debug, String node){
        T result = null;

        steps++;

        Node<T> runningNode = nodes.get(node);

        runningNode.run(input);

        if(debug){
            System.out.println("- Step "+ steps+": ("+name+") - "+node+" executed: " +input);
        }

        if(this.last != null &&this.last.equals(node)){
            return input;
        }

        LinkedHashMap<String, Predicate<T>> edges = runningNode.getEdges();

        SequencedSet<String> childs = edges.sequencedKeySet();
        for(String child : childs){
            if(edges.get(child).test(input)){
                result = runSubtree(input, debug, child);
                if(result != null){
                    return result;
                }
            }
        }

        return result;
    }


}
