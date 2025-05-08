package workflows;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface WfNodeInterface<T, S> {

    public WfNodeInterface<T, S> withInjector(Function<T, S> injector);

    public WfNodeInterface<T, S> withExtractor(BiConsumer<S, T> extractor);
}
