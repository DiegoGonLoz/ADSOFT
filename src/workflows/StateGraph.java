package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraph<T> implements StateGraphInterface<T> {
    private final String name;
    private final String description;
    private String initial = null;
    private String last = null;

    private final HashMap<String, Consumer<T>> nodes = new HashMap<>();
    private final HashMap<String, LinkedHashMap<String, Predicate<T>>> edges = new HashMap<>();

    public StateGraph(String name, String description){
        this.name = name;
        this.description = description;
    }

    public StateGraph<T> addNode(String node, Consumer<T> operator) throws AlreadyExistingNode {
        if(existNode(node)){
            throw new AlreadyExistingNode(node, name);
        }

        Node<T> newNode = new Node<T>();
        newNode.setOperator(operator);

        nodes.put(node, newNode);

        return this;
    }

    public <S> WfNode<T, S> addWfNode(String node, StateGraph<S> wf) {
        if(existNode(node)){
            throw new AlreadyExistingNode(node, name);
        }

        WfNode<T, S>wfNode = new WfNode<>(wf);

        nodes.put(node, wfNode);

        return wfNode;
    }

    public StateGraph<T> addEdge(String origin, String destination){
        return addConditionalEdge(origin, destination, (T input) -> true);
    }

    public StateGraph<T> addConditionalEdge(String origin, String destination, Predicate<T> condition){
        if(!existNode(origin)){
            throw new NonExistingNode("Node " + origin + " does not exist in graph " + name);
        }

        if(!existNode(destination)){
            throw new NonExistingNode("Node " + destination + " does not exist in graph " + name);
        }

        edges.computeIfAbsent(origin, k -> new LinkedHashMap<>());
        edges.get(origin).put(destination, condition);

        return this;
    }

    public void setInitial(String node){
        if(!existNode(node)){
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }

        this.initial = node;
    }

    public void setFinal(String node){
        if(!existNode(node)){
            throw new NonExistingNode("Node " + node + " does not exist in graph " + name);
        }

        this.last = node;
    }

    public void accept(T input){
        run(input, false);
    }

    public T run(T input, boolean debug){
        if(this.initial == null){
            throw new NonExistingNode("Initial node does not exist in graph " + name);
        }

        if(debug){
            System.out.println("Workflow '"+name+"'("+description+"):\n");
            /*TODO - Completar*/
        }

        return runSubtree(input, debug, this.initial);
    }

    private boolean existNode(String node){
        return nodes.containsKey(node);
    }

    private T runSubtree(T input, boolean debug, String node){
        T result = null;
        /*TODO - Hacer debug*/

        nodes.get(node).accept(input);


        if(this.last != null &&this.last.equals(node)){
            return input;
        }

        LinkedHashMap<String, Predicate<T>> predicates = edges.get(node);

        Set<String> childs = predicates.keySet();
        for(String child : childs){
            if( predicates.get(child).test(input)){
                result = runSubtree(input, debug, child);
                if(result != null){
                    return result;
                }
            }
        }

        return result;
    }
}
