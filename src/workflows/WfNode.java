package workflows;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class WfNode<T, S> extends Node<T> {
    private Function<T, S> injector;
    private BiConsumer<S, T> extractor;

    private StateGraph<S> workflow;

    public WfNode(StateGraph<S> workflow) {
        this.workflow = workflow;

        setOperator(input -> {
            if (this.injector == null || this.extractor == null) {
                throw new IllegalStateException("Injector y extractor deben ser configurados antes de usar el nodo.");
            }
            S inputS = this.injector.apply(input);
            this.workflow.accept(inputS);
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