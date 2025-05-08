package workflows;

import java.util.function.Consumer;

public interface NodeInterface<T> {

    public void setOperator(Consumer<? super T> operator);

    public void run(T input);
}
