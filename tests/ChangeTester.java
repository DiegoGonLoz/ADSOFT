package tests;

import changes.*;

import java.util.List;
/**
 * Pruebas para la clase Change.
 * Autor: Diego González y Diego Lesma
 */
public class ChangeTester {
    /**
     * Metodo principal para ejecutar las pruebas de la clase Change.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main (String[] args) {
        for (Change change : createChanges())
            System.out.println(change);


        for (Change change : createChanges())
            System.out.println(change.getNumberOfLines());

        for (Change change : createChanges())
            System.out.println(change.printForCommit());
    }
    /**
     * Crea una lista de cambios para pruebas.
     *
     * @return Lista de objetos Change.
     */
    public static List<Change> createChanges () {
        Change c1 = new AddChange(0, "/src/main/NuevaClase.java", "import java.util.*;\nimport java.io.*;");
        Change c2 = new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "// Modificación en la clase existente");
        Change c3 = new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java");
        return List.of(c1,c2,c3);
    }
}