package workflows;

/**
 * Clase nodo que hereda de NodeDecorator
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public class NodeProfiler<T> extends NodeDecorator<T> {
    /** Grafo de nodos */
    private final StateGraphProfiler<T> graph;

    /**
     * Constructor de la clase NodeProfiler
     * @param node objeto tipo NodeInterface
     * @param graph objeto tipo grafo
     */
    public NodeProfiler(NodeInterface<T> node, StateGraphProfiler<T> graph) {
        super(node);
        this.graph = graph;
    }

    /**
     * Metodo run
     * @param input input del metodo run
     */
    @Override
    public void run(T input) {
        String traceInput = input.toString();

        long startTime = System.nanoTime();

        super.run(input);

        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        String formattedDuration = String.format("%.4f", durationMs);

        graph.addTrace("[" + getName() + " with " + traceInput + " " + formattedDuration + " ms]");
    }

    /**
     * Metodo toString de NodeProfiler
     * @return string con la información del objeto
     */
    @Override
    public String toString() {
        return super.toString() + " [profiled]";
    }
}