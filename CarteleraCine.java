import java.util.*;

public class CarteleraCine {
    private String nombreCine;
    private List<Pelicula> peliculas = new ArrayList<>();
    public CarteleraCine(String cine, Pelicula[] peliculas) {
        this.nombreCine = cine;
        for (Pelicula p : peliculas){
            this.peliculas.add(p);
        }
    }
    public List<Pelicula> peliculasPorGenero(String genero){
         List<Pelicula> resultado = new ArrayList<>();
         for (Pelicula pelicula : this.peliculas) {
             if (pelicula.getGenero().equals(genero)) {
                 resultado.add(pelicula);
             }
         }
         return resultado;
    }

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
    public static void main(String ...args) {
        Pelicula peliculas[] = { new Pelicula("Perfect days", "Drama", 2023),
                new Pelicula("Inception", "Accion", 2010),
                new Pelicula("Jumanji", "Aventura", 1995) };
        CarteleraCine cinesTelmo = new CarteleraCine("Telmo", peliculas);
        System.out.println(cinesTelmo.toString());

        List<Pelicula> PeliculasDramas = cinesTelmo.peliculasPorGenero("Drama");
        System.out.println("Dramas: "+PeliculasDramas.toString());

        List<Pelicula> PeliculasPosterioresA2020 = cinesTelmo.peliculasPosterioresA(2020);
        System.out.println("Recientes: "+PeliculasPosterioresA2020.toString());
    }
}
