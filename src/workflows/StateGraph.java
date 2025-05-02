package workflows;

import myExceptions.AlreadyExistingNode;
import myExceptions.NonExistingNode;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class StateGraph<T>{
    private final String name;
    private final String description;
    private String initial = null;
    private String last = null;

    private final HashMap<String, Node<T>> nodes = new HashMap<>();
    private final HashMap<String, List<String>> edges = new HashMap<>();

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
        if(!existNode(origin)){
            throw new NonExistingNode("Node " + origin + " does not exist in graph " + name);
        }

        if(!existNode(destination)){
            throw new NonExistingNode("Node " + destination + " does not exist in graph " + name);
        }

        edges.computeIfAbsent(origin, k -> new LinkedList<>());
        edges.get(origin).add(destination);

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

        return runSubtree(input, debug, this.initial);
    }

    private boolean existNode(String node){
        return nodes.containsKey(node);
    }

    private T runSubtree(T input, boolean debug, String node){
        T result = null;
        /*TODO - Hacer debug*/
        nodes.get(node).run(input);
        if(this.last.equals(node)){
            return input;
        }

        List<String> childs = edges.get(node);
        for(String child : childs){
            result = runSubtree(input, debug, child);
            if(result != null){
                return result;
            }
        }

        return result;
    }
}
