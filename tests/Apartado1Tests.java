import myExceptions.*;
import proponentes.*;
import sistemas.Sistema;

import java.util.*;

/**
 * Clase que hace tests del apartado 1 de la práctica
 *
 * @author Diego Gonzales
 */
public class Apartado1Tests {
    /**Lista de proponentes para las pruebas*/
    private static List<Proponente> proponentes = new ArrayList<Proponente>();
    /**Función main del test*/
   public static void main (String[] args) {
        try{
            System.out.println("\n\n=== TEST 1: Creación de proponentes ===");
            testCreacionProponentes();

            System.out.println("\n=== TEST 2: Inscripcion en asociaciones ===");
            testInscripcionAsociaciones();

            System.out.println("\n=== TEST 3: Proponentes repetidos ===");
            testProponentesRepetidos();

            System.out.println("\n=== TEST 4: Busqueda de proponentes ===");
            testBusquedaProponentes();

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 1===");
        } catch (Exception e){
            System.out.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }

    /**
     * Metodo para obtener los proponentes para diversos tests
     * @return Lista de proponentes
     */
    public static List<Proponente> obtenerProponentesTest(){
       if(!proponentes.isEmpty()) return new ArrayList<>(proponentes);

       List<Proponente> nuevosProponentes = new ArrayList<Proponente>();

       nuevosProponentes.add(new Ciudadano("Juan Bravo", "12345", "01234567L"));
       nuevosProponentes.add(new Ciudadano("Ana López", "12345", "01234568C"));
       nuevosProponentes.add(new Ciudadano("Luisa Gómez", "12345", "01234569K"));
       nuevosProponentes.add(new Asociacion("conservemos el manzanares", "12345", (Ciudadano) nuevosProponentes.get(2)));
       nuevosProponentes.add(new Asociacion("amigos de los pajaros", "12345", (Ciudadano) nuevosProponentes.get(2)));
       nuevosProponentes.add(new Fundacion("Fundación Canal", "12345", "A1234567B"));

       ((Ciudadano) nuevosProponentes.get(0)).inscribirse((Asociacion) nuevosProponentes.get(3));
       ((Asociacion) nuevosProponentes.get(4)).inscribirse((Asociacion) nuevosProponentes.get(3));
        ((Ciudadano) nuevosProponentes.get(1)).inscribirse((Asociacion) nuevosProponentes.get(4));

       proponentes = nuevosProponentes;

       return new ArrayList<>(nuevosProponentes);
    }

    /**
     * Metodo para crear proponentes
     */
    public static void testCreacionProponentes() {
        try{
            new Ciudadano(null, "12345", "01234567L");
            throw new RuntimeException("Error no detectado al crear ciudadano sin nombre");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            new Ciudadano("Juan Bravo", null, "01234567L");
            throw new RuntimeException("Error no detectado al crear ciudadano sin contraseña");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            new Ciudadano("Juan Bravo", "12345", null);
            throw new RuntimeException("Error no detectado al crear ciudadano sin NIF");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            new Ciudadano("Juan Bravo", "12345", "01234567H");
            throw new RuntimeException("Error no detectado al introducir un numero de DNI incorrecto");
        } catch (FormatoNifIncorrecto e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            new Asociacion("conservemos el manzanares", "12345", null);
            throw new RuntimeException("Error no detectado al crear asociacion sin representante");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            new Fundacion("Fundación Canal", "12345", "A12345678");
            throw new RuntimeException("Error no detectado al crear fundacion con CIF incorrecto");
        } catch (FormatoCifIncorrecto e) {
            System.out.println("Error capturado correctamente: \n" + e);
        }
    }

    /**
     * Metodo para probar las inscripciones de asociaciones
     */
    public static void testInscripcionAsociaciones() {
        Ciudadano ciudadano1 = new Ciudadano("Juan Bravo", "12345", "01234567L");
        Ciudadano ciudadano2 = new Ciudadano("Ana López", "12345", "01234568C");

        Asociacion asociacion1 = new Asociacion("conservemos el manzanares", "12345", ciudadano1);
        Asociacion asociacion2 = new Asociacion("amigos de los pajaros", "12345", ciudadano1);
        Asociacion asociacion3 = new Asociacion("amigos del agua", "12345", ciudadano1);
        Asociacion asociacion4 = new Asociacion("amigos del bosque", "12345", ciudadano2);

        ciudadano2.inscribirse(asociacion1);
        asociacion2.inscribirse(asociacion1);
        System.out.println(asociacion1);

        ciudadano2.inscribirse(asociacion3);
        try{
            asociacion3.inscribirse(asociacion1);
            throw new RuntimeException("Error no detectado al inscribir asociacion no vacia");
        } catch (InscripcionInviable e){
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            asociacion4.inscribirse(asociacion1);
            throw new RuntimeException("Error no detectado al inscribir asociacion con distinto representante");
        } catch (InscripcionInviable e){
            System.out.println("Error capturado correctamente: \n" + e);
        }

        try{
            ciudadano1.inscribirse(asociacion1);
            throw new RuntimeException("Error no detectado al inscribir ciudadano perteneciente a la asociacion");
        } catch (EnteCiudadanoEsMiembro e){
            System.out.println("Error capturado correctamente: \n" + e);
        }

        System.out.println("\nAsociaciones antes de la baja de Ana López:");
        System.out.println(asociacion1 + " : " + asociacion1.todosLosCiudadanos());
        System.out.println(asociacion3 + " : " + asociacion3.todosLosCiudadanos());
        ciudadano2.darseDeBaja();
        System.out.println("\nAsociaciones despues de la baja de Ana López:");
        System.out.println(asociacion1 + " : " + asociacion1.todosLosCiudadanos());
        System.out.println(asociacion3 + " : " + asociacion3.todosLosCiudadanos());

    }

    /**
     * Metodo para probar casos como proponentes repetidos
     */
    public static void testProponentesRepetidos() {
       for(Proponente proponente : obtenerProponentesTest()){
           Sistema.getInstance().addProponente(proponente);
       }

       try{
           Sistema.getInstance().addProponente((Ciudadano)obtenerProponentesTest().get(0));
           throw new RuntimeException("Error no detectado al añadir un ciudadano existente al sistema");
       } catch(ErrorAnadiendoCiudadanoExistente e){
           System.out.println("Error capturado correctamente: \n" + e);
       }
       try{
           Sistema.getInstance().addProponente((Asociacion)obtenerProponentesTest().get(3));
           throw new RuntimeException("Error no detectado al añadir una asociacion existente al sistema");
       } catch(ErrorAnadiendoAsociacionExistente e){
           System.out.println("Error capturado correctamente: \n" + e);
       }
       try{
           Sistema.getInstance().addProponente((Fundacion)obtenerProponentesTest().get(5));
           throw new RuntimeException("Error no detectado al añadir una fundacion existente al sistema");
       } catch(ErrorAnadiendoFundacionExistente e){
           System.out.println("Error capturado correctamente: \n" + e);
       }
    }

    public static void testBusquedaProponentes() {
        if(!obtenerProponentesTest().get(0).equals(Sistema.getInstance().obtenerCiudadano(obtenerProponentesTest().get(0).getNombre())))
            throw new RuntimeException("Error en la busqueda de ciudadanos");
        else
            System.out.println("Ciudadano encontrado correctamente: " + Sistema.getInstance().obtenerCiudadano(obtenerProponentesTest().get(0).getNombre()));

        System.out.println(Sistema.getInstance().todosLosUsuarios());
    }
}
