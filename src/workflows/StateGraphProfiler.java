package workflows;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase grafo que hereda de StateGraphDecorator
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class StateGraphProfiler<T> extends StateGraphDecorator<T>{
    /** Historial de operaciones */
    private final List<String> history = new LinkedList<String>();

    public StateGraphProfiler(StateGraphInterface<T> graph){
        super(graph);
    }

    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node){
        return new NodeProfiler(super.createNode(node), this);
    }

    public void addTrace(String trace){
        this.history.add(trace);
    }

    public List<String> history(){
        return Collections.unmodifiableList(history);
    }
}
