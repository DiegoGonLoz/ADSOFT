import java.util.*;

/**
 * Esta clase contiene toda la funcionalidad de la clase Cartelera Cine y la funcion main. Sirve para inicializar un Cine con varias peliculas en cartelera
 *
 * @author Diego Lesma, Diego Gonzalez
 */

public class CarteleraCine {
    private String nombreCine;
    private List<Pelicula> peliculas = new ArrayList<>();

    /**
     * Es el constructor de la clase CarteleraCine
     *
     * @param cine String con el nombre del cine
     * @param peliculas Coleccion de peliculas que ofrecera el cine
     */
    public CarteleraCine(String cine, Pelicula[] peliculas) {
        this.nombreCine = cine;
        for (Pelicula p : peliculas){
            this.peliculas.add(p);
        }
    }

    /**
     * Metodo para filtrar peliculas de una cartelera segun el genero
     *
     * @param genero String con el genero que buscamos en la cartelera
     * @return Lista de peliculas de la cartelera cuyo genero coincide con el proporcionado
     */
    public List<Pelicula> peliculasPorGenero(String genero){
         List<Pelicula> resultado = new ArrayList<>();
         for (Pelicula pelicula : this.peliculas) {
             if (pelicula.getGenero().equals(genero)) {
                 resultado.add(pelicula);
             }
         }
         return resultado;
    }

    /**
     * Metodo para filtrar peliculas de una cartelera segun el anyo
     *
     * @param anyo entero con el anyo que usamos como cota
     * @return Lista de peliculas que salieron despues del anyo proporcionado
     */
    public List<Pelicula> peliculasPosterioresA(int anyo){
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula pelicula : this.peliculas) {
            if (pelicula.getAnyo() > anyo) {
                resultado.add(pelicula);
            }
        }
        return resultado;
    }

    @Override
    public String toString() {
        return "Cine: "+this.nombreCine+"\nPeliculas en cartelera: "+this.peliculas;
    }

    /**
     * Funcion main, crea una cartelera con varias peliculas para probar los distintos
     * metodos implementados en la clase
     *
     * @param args coleccion de strings (no utilizados)
     */
    public static void main(String[] args) {
        Pelicula peliculas[] = { new Pelicula("Perfect days", "Drama", 2023, "Wim Wenders"),
                new Pelicula("Inception", "Accion", 2010, "Christopher Nolan"),
                new Pelicula("Jumanji", "Aventura", 1995, "Joe Johnston") };
        CarteleraCine cinesTelmo = new CarteleraCine("Telmo", peliculas);
        System.out.println(cinesTelmo.toString());

        List<Pelicula> PeliculasDramas = cinesTelmo.peliculasPorGenero("Drama");
        System.out.println("Dramas: "+PeliculasDramas.toString());

        List<Pelicula> PeliculasPosterioresA2020 = cinesTelmo.peliculasPosterioresA(2020);
        System.out.println("Recientes: "+PeliculasPosterioresA2020.toString());
    }
}
