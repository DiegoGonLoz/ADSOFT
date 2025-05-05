package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateGraph<T>{
    private final String name;
    private final String description;
    private String initial = null;
    private String last = null;

    private final HashMap<String, Node<T>> nodes = new HashMap<>();
    private final HashMap<String, LinkedHashMap<String, Predicate<T>>> edges = new HashMap<>();

    public StateGraph(String name, String description){
        this.name = name;
        this.description = description;
    }

    public StateGraph addNode(String node, Consumer<T> operator) throws AlreadyExistingNode {
        if(existNode(node)){
            throw new AlreadyExistingNode("Node " + node + " already exists in graph " + name);
        }

        nodes.put(node, new Node<T>(operator));

        return this;
    }

    public StateGraph addEdge(String origin, String destination){
        return addConditionalEdge(origin, destination, (T input) -> true);
    }

    public StateGraph addConditionalEdge(String origin, String destination, Predicate<T> condition){
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

    public T run(T input, boolean debug){
        if(this.initial == null){
            throw new NonExistingNode("Initial node does not exist in graph " + name);
        }
        if(this.last == null){
            throw new NonExistingNode("Last node does not exist in graph " + name);
        }

        if(debug){
            System.out.println("Workflow '"+name+"'("+description+"):\n");
            /*TODO - Completar*/
        }

        return runSubtree(input, debug, this.initial, true);
    }

    private boolean existNode(String node){
        return nodes.containsKey(node);
    }

    private T runSubtree(T input, boolean debug, String node, boolean runnable){
        T result = null;
        /*TODO - Hacer debug*/
        if(runnable){
            nodes.get(node).run(input);
        }

        if(this.last.equals(node)){
            return input;
        }

        LinkedHashMap<String, Predicate<T>> predicates = edges.get(node);

        Set<String> childs = predicates.keySet();
        for(String child : childs){
            result = runSubtree(input, debug, child, predicates.get(child).test(input));
            if(result != null){
                return result;
            }
        }

        return result;
    }

    public T addwfNode(String calculate, StateGraph<NumericData> wfNumeric) {
    }
}
