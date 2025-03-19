package repositories;

/**
 * Enumeración que define las estrategias de fusión disponibles.
 * @author Diego González y Diego Lesma
 */
public enum Strategy {
    /**
     * Estrategia que no hace nada en caso de conflicto en un merge
     */
    NONE,
    /**
     * Estrategia que elige el commit en el origen en caso de conflicto en un merge
     */
    ORIGIN,
    /**
     * Estrategia que elige el commit en el destino en caso de conflicto en un merge
     */
    DESTINY,
    /**
     * Estrategia que selecciona ambos commits en un conflicto si ambos son de tipo ADD
     */
    ADDMERGE
}
