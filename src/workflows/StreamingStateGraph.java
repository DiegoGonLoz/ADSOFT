package workflows;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StreamingStateGraph<T> extends StateGraph<T>{
    private LinkedList<T> history = new LinkedList<T>();
    public StreamingStateGraph(String name, String description) {
        super(name, description);
    }
    public List<T> history() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public T run(T input, boolean debug) {
        T result = super.run(input, debug);

        history.add(input);

        return result;
    }
}
