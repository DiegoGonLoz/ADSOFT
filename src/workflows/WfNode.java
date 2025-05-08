package workflows;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class WfNode<T, S> extends Node<T> implements WfNodeInterface<T,S>{
    private Function<T, S> injector;
    private BiConsumer<S, T> extractor;

    private StateGraphInterface<S> workflow;

    public WfNode(String name, StateGraphInterface<S> workflow) {
        super(name);

        this.workflow = workflow;

        setOperator(input -> {
            if (this.injector == null || this.extractor == null) {
                throw new IllegalStateException("Injector y extractor deben ser configurados antes de usar el nodo.");
            }
            S inputS = this.injector.apply(input);
            this.workflow.run(inputS, false);
            this.extractor.accept(inputS, input);
        });
    }

    public WfNode<T, S> withInjector(Function<T, S> injector) {
        this.injector = injector;
        return this;
    }

    public WfNode<T, S> withExtractor(BiConsumer<S, T> extractor) {
        this.extractor = extractor;
        return this;
    }
}