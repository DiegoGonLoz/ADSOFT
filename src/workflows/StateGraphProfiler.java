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

    /**
     * Constructor de la clase StateGraphProfiler
     * @param graph grafo a emplear
     */
    public StateGraphProfiler(StateGraphInterface<T> graph){
        super(graph);
    }

    /**
     * Metodo para crear un nodo
     * @param node nodo a crear
     * @return nodo creado
     */
    @Override
    public NodeInterface<T> createNode(NodeInterface<T> node){
        return new NodeProfiler(super.createNode(node), this);
    }

    /**
     * Metodo para añadir una traza
     * @param trace traza a añadir
     */
    public void addTrace(String trace){
        this.history.add(trace);
    }

    /**
     * Metodo para obtener el historial
     * @return Lista de strings con el historial
     */
    public List<String> history(){
        return Collections.unmodifiableList(history);
    }
}
