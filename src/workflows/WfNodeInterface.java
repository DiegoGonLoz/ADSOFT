package workflows;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Interfaz para los WfNodes
 * @param <T> primer objeto asociado
 * @param <S> segundo objeto asociado
 * @author Diego Gonzalez
 */
public interface WfNodeInterface<T, S> extends NodeInterface<T>{
    /**
     * Metodo para asignar un injector
     * @param injector injector a asignar
     * @return nodo modificado
     */
    public WfNodeInterface<T, S> withInjector(Function<T, S> injector);

    /**
     * Metodo para asignar un extractor
     * @param extractor extractor a asignar
     * @return nodo modificado
     */
    public WfNodeInterface<T, S> withExtractor(BiConsumer<S, T> extractor);
}
