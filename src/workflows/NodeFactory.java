package workflows;

public interface NodeFactory<T> {
    /** Crea un nodo “simple” dado su nombre. */
    NodeInterface<T> createNode(String name);

    /** Crea un nodo de flujo de trabajo, dado un WfNode preconfigurado. */
    <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode);
}
