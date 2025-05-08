package workflows;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Clase WfNode
 * @param <T> primer objeto asociado
 * @param <S> segundo objeto asociado
 */
public class WfNode<T, S> extends Node<T> implements WfNodeInterface<T,S>{
    /** Injector */
    private Function<T, S> injector;
    /** Extractor */
    private BiConsumer<S, T> extractor;
    /** Workflow */
    private StateGraphInterface<S> workflow;

    /**
     * Constructor de la clase WfNode
     * @param name nombre del nodo
     * @param workflow workflow a seguir
     */
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

    /**
     * Metodo para asignar un injector
     * @param injector injector a asignar
     * @return nodo modificado
     */
    public WfNode<T, S> withInjector(Function<T, S> injector) {
        this.injector = injector;
        return this;
    }

    /**
     * Metodo para asignar un extractor
     * @param extractor extractor a asignar
     * @return nodo modificado
     */
    public WfNode<T, S> withExtractor(BiConsumer<S, T> extractor) {
        this.extractor = extractor;
        return this;
    }
}