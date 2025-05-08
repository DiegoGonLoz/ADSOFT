package workflows;

/**
 * Interfaz NodeFactory
 * @param <T> objeto asociado
 * @author Diego Gonzalez
 */
public interface NodeFactory<T> {
    /**
     * Metodo para crear un nodo
     * @param name Nombre del nodo
     * @return nodo creado
     */
    NodeInterface<T> createNode(String name);

    /**
     * Crea un nodo de flujo de trabajo
     * @param wfNode nodo de flujo de trabajo
     * @return nodo creado
     * @param <S> objeto asociado
     */
    <S> NodeInterface<T> createWfNode(WfNodeInterface<T, S> wfNode);
}
