package workflows;

public class NodeProfiler<T> extends NodeDecorator<T> {
    private final StateGraphProfiler<T> graph;

    public NodeProfiler(NodeInterface<T> node, StateGraphProfiler<T> graph) {
        super(node);
        this.graph = graph;
    }

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

    @Override
    public String toString() {
        return super.toString() + " [profiled]";
    }
}