package sistemas;

import myExceptions.ErrorAnadiendoProponente;
import proponentes.*;
import proyectos.*;
import announcements.*;

import java.util.*;

/**
 * Clase que representa el sistema central del ejercicio
 *
 * @athor Diego Gonzalez y Diego Lesma
 */
public class Sistema {
    /**Variable estática sistema*/
    private static Sistema sistema;
    /**Set de proponentes*/
    Set<Proponente> proponentes;
    /**Set de proyectos*/
    Set<ProyectoParticipativo> proyectos;
    /**Set de anuncios*/
    Set<Announcement> anuncios;

    /**
     * Constructor de la clase Sistema
     */
    private Sistema() {
        this.proponentes = new LinkedHashSet<>();
        this.proyectos = new TreeSet<>();
        this.anuncios = new TreeSet<>();
    }

    /**
     * Invocador del Singleton de la clase en cuestión
     * @return variable sistema
     */
    public static Sistema getInstance() {
        if (sistema == null) {
            sistema = new Sistema();
        }
        return sistema;
    }

    /**
     * Metodo para comprobar la ya existencia de un proponente en el sistema
     * @param proponente proponente a comprobar
     * @return true o false según el resultado
     */
    public boolean existeProponente(Proponente proponente){
        for(Proponente p : proponentes) {
            if (p.equals(proponente)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo para añadir un proponente
     * @param proponente proponente a añadir
     * @throws ErrorAnadiendoProponente error al añadir proponente
     */
    public void addProponente(Proponente proponente) throws ErrorAnadiendoProponente {
        if(existeProponente(proponente)){
            throw proponente.repetido();
        }
        proponentes.add(proponente);
    }

    /**
     * Metodo para obtener un ciudadano
     * @param nombre nombre del ciudadano
     * @return objeto ciudadano o null
     */
    public Ciudadano obtenerCiudadano(String nombre){
        for(Proponente p : proponentes) {
            if(p instanceof Ciudadano) {
                if (p.getNombre().equals(nombre)) {
                    return (Ciudadano) p;
                }
            }
        }
        return null;
    }

    /**
     * Metodo para obtener todos los usuarios
     * @return lista de usuarios del sistema
     */
    public List<Proponente> todosLosUsuarios(){
        return new LinkedList<>(proponentes);
    }

    /**
     * Metodo para proponer un proyecto
     * @param p proyecto a proponer
     * @return true o false según si se haya podido hacer la ejecución
     */
    public boolean proponerProyecto(ProyectoParticipativo p){
        return this.proyectos.add(p);
    }

    /**
     * Metodo para obtener los proyectos registrados
     * @return Set de proyectos
     */
    public Set<ProyectoParticipativo> proyectosRegistrados(){
        return this.proyectos;
    }

    /**
     * Metodo para obtener un proyecto según su nombre
     * @param nombre nombre del proyecto
     * @return objeto proyecto o null
     */
    public ProyectoParticipativo obtenerProyecto(String nombre){
        for(ProyectoParticipativo p : proyectos){
            if(nombre.equals(p.getTitulo())){
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo para obtener un proyecto según su id
     * @param id id del proyecto
     * @return objeto proyecto o null
     */
    public ProyectoParticipativo obtenerProyecto(int id){
        for(ProyectoParticipativo p : proyectos){
            if(id == p.getCodigo()){
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo para generar un mapa proyecto-numMiembros
     * @return Mapa pedido y ordenado
     */
    public SortedMap<ProyectoParticipativo, Integer> obtenerMapaProyectoApoyos(){
        SortedMap<ProyectoParticipativo, Integer> mapa = new TreeMap<>((o1, o2) -> {
            int diff = o2.obtenerApoyos() - o1.obtenerApoyos();
            if(diff == 0){
                return o2.getLastApoyo().compareTo(o1.getLastApoyo());
            }
            return diff;
        });

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p, p.obtenerApoyos());
        }

        return mapa;
    }

    /**
     * Metodo para obtener un mapa proyecto-ciudadanos
     * @return mapa pedido
     */
    public Map<ProyectoParticipativo, Set<Ciudadano>> obtenerMapaProyectoParticipativos(){
        Map<ProyectoParticipativo, Set<Ciudadano>> mapa = new TreeMap<>();

        for(ProyectoParticipativo p : proyectos){
            mapa.put(p, p.todosLosCiudadanos());
        }

        return mapa;
    }

}
