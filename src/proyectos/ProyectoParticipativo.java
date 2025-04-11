package proyectos;

import announcements.*;
import myExceptions.*;
import proponentes.Ciudadano;
import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que representa a un proyecto
 *
 * @author Diego Lesma
 */
public abstract class ProyectoParticipativo implements FollowedEntity, Comparable<ProyectoParticipativo> {
    /**Identidicador único del proyecto*/
    private final int codigo;
    /**Fecha de creacion*/
    private final LocalDate fecha;
    /**Hora de creacion*/
    private final LocalTime hora;
    /**Título del proyecto*/
    private final String titulo;
    /**Descripcion del proyecto*/
    private final String descripcion;
    /**Proponente del proyecto*/
    private final Proponente proponente;
    /**Fecha y hora del último apoyo*/
    private LocalDateTime lastApoyo;
    /**Set de los apoyos*/
    protected final Set<EnteCiudadano> apoyos;
    /**Set de los followers*/
    private final Set<FollowerManager> followers;
    /**Contador estatico para asignar ids*/
    private static int contador_id=0;

    /**
     * Constructor de la clase ProyectoParticipativo
     * @param titulo título del proyecto
     * @param descripcion descripción del proyecto
     * @param proponente proponente del proyecto
     */
    public ProyectoParticipativo(String titulo, String descripcion, Proponente proponente) {
        this.codigo = contador_id;
        contador_id++;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lastApoyo = LocalDateTime.now();
        this.apoyos = new HashSet<EnteCiudadano>();
        this.followers = new HashSet<FollowerManager>();
        this.proponente = proponente;
    }

    /**
     * Constructor de la clase ProyectoParticipativo
     * @param titulo título del proyecto
     * @param descripcion descripción del proyecto
     * @param proponente proponente del proyecto
     */
    public ProyectoParticipativo(String titulo, String descripcion, EnteCiudadano proponente) {
        this.codigo = contador_id;
        contador_id++;
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lastApoyo = LocalDateTime.now();
        this.apoyos = new HashSet<EnteCiudadano>(Set.of(proponente));
        this.followers = new HashSet<FollowerManager>();
        this.follow(proponente);
        this.proponente = proponente;
    }

    /**
     * Getter del titulo
     * @return título del proyecto
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Getter del id
     * @return id del proyecto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Getter del lastApoyo
     * @return fecha-hora del último apoyo
     */
    public LocalDateTime getLastApoyo() {
        return lastApoyo;
    }

    /**
     * Metodo para sumar un apoyo
     * @param ente ente ciudadano que apoya
     * @return true o false según el resultado
     * @throws ErrorApoyandoProyecto excepcion en caso de error apoyando el proyecto
     */
    public boolean sumarApoyo(EnteCiudadano ente) throws ErrorApoyandoProyecto {
        try {
            if (apoyoPosible(ente)) {
                apoyos.removeIf(ente::esMiembro);
                apoyos.add(ente);
                this.actualizarApoyo();
                this.announce(new Announcement("El proyecto "+this.titulo+" ha recibido un apoyo de "+ente.getNombre()+" ("+this.obtenerApoyos()+" apoyos)"));
                return true;
            }
        } catch (ProyectoPropuestoPorSiMismo | EnteCiudadanoEsMiembro | ProyectoMasDe60Dias e) {
            throw new ErrorApoyandoProyecto("Error en metodo apoyar:"+ e);
        }

        return false;
    }

    /**
     * Metodo para actualizar un apoyo
     */
    private void actualizarApoyo(){
        this.lastApoyo = LocalDateTime.now();
    }

    /**
     * Metodo para obtener el número de apoyos
     * @return numero de apoyos
     */
    public int obtenerApoyos(){
        int numApoyos = 0;
        for(EnteCiudadano ente : apoyos){
            numApoyos += ente.cantidadMiembros();
        }
        return numApoyos;
    }

    /**
     * Metodo para obtener a los ciudadanos
     * @return set de ciudadanos
     */
    public Set<Ciudadano> todosLosCiudadanos(){
        Set<Ciudadano> ciudadanos = new HashSet<Ciudadano>();
        for(EnteCiudadano ente : apoyos){
            ciudadanos.addAll(ente.todosLosCiudadanos());
        }
        return ciudadanos;
    }

    /**
     * Metodo para evaluar si un apoyo es posible
     * @param ente apoyo a evaluar
     * @return true o false según el resultado
     * @throws ProyectoPropuestoPorSiMismo error proyecto propuesto por sí mismo
     * @throws EnteCiudadanoEsMiembro error el ente es o tiene un ciudadano miembro
     * @throws ProyectoMasDe60Dias error el proyecto lleva más de 60 días en alta
     */
    private boolean apoyoPosible(EnteCiudadano ente) throws ProyectoPropuestoPorSiMismo, EnteCiudadanoEsMiembro, ProyectoMasDe60Dias, NullPointerException {
        if(ente == null){
            throw new NullPointerException("\nError en ApoyoPosible: ");
        }
        if(ente.equals(this.proponente)) {
            throw new ProyectoPropuestoPorSiMismo("\nError en ApoyoPosible: ");
        }

        for(EnteCiudadano ente2 : apoyos){
            if(ente2.esMiembro(ente)){
                throw new EnteCiudadanoEsMiembro("\nError en ApoyoPosible: ");
            }
        }

        if(ChronoUnit.DAYS.between(LocalDate.now(), fecha) > 60){
            throw new ProyectoMasDe60Dias("\nError en ApoyoPosible: ", ChronoUnit.DAYS.between(LocalDate.now(), fecha));
        }

        return true;
    }

    /**
     * Metodo equals para comparar proyectos
     * @param obj objeto a comparar
     * @return true o false según coincida el id o no
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof ProyectoCiudadano){
            ProyectoParticipativo p = (ProyectoParticipativo) obj;
            return p.codigo == this.codigo;
        }
        return false;
    }

    /**
     * Metodo hashCode de ProyectoParticipativo
     * @return código hash
     */
    @Override
    public int hashCode() {
        return this.codigo;
    }

    /**
     * Metodo follow para proyecto participativo
     * @param f objeto follower
     * @return true o false según se haya podido seguir o no
     */
    @Override
    public boolean follow(Follower f) {
        return followers.add(new FollowerManagerAllMessages(f));
    }

    /**
     * Metodo unfollow para proyecto participativo
     * @param f objeto follower
     * @return true o false según se haya podido dejar de seguir o no
     */
    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    /**
     * Metodo announce para proyectos
     * @param t objeto a anunciar
     */
    @Override
    public void announce(Announcement t) {
        for(FollowerManager follower : followers){
            follower.announce(t);
        }
    }

    /**
     * Metodo follow según una estrategia
     * @param f objeto follower
     * @param ns estrategia a seguir
     * @return true o false según se haya podido o no
     */
    @Override
    public boolean follow(Follower f, AnnouncementStrategy ns) {
        return switch (ns) {
            case AnnouncementStrategy.ONE_IN_N_MESSAGES -> followers.add(new FollowerManagerFrecuency(f));
            case AnnouncementStrategy.WHEN_N_SUPPORTS_ACHIEVED ->
                    followers.add(new FollowerManagerProjectSupport(f, this));
            default -> followers.add(new FollowerManagerAllMessages(f));
        };
    }

    /**
     * Metodo para cambiar de umbral
     * @param f follower
     * @param umbral nuevo umbral
     * @return true o false según se haya podido hacer la ejecución
     */
    public boolean changeUmbral(Follower f, int umbral){
        for(FollowerManager follower : followers){
            if(follower.getFollower().equals(f)){
                follower.setUmbral(umbral);
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo compareTo entre proyectos
     * @param o el objeto a comparar
     * @return numero entero positivo negativo o 0 según quien gane al comparación
     */
    @Override
    public int compareTo(ProyectoParticipativo o) {
        return this.codigo - o.codigo;
    }

    /**
     * Metodo toString de la clase ProyectoParticipativo
     * @return string con la información del proyecto
     */
    @Override
    public String toString() {
        return this.codigo+ ": " +
                this.titulo + ". " +
                "Proponente: " + proponente;
    }

    /**
     * Getter de fecha
     * @return fecha de creación
     */
    public LocalDate getFecha() {
        return this.fecha;
    }

    /**
     * Getter de hora
     * @return hora de creación
     */
    public LocalTime getHora() {
        return this.hora;
    }

    /**
     * Getter de proponente
     * @return objeto proponente
     */
    public Proponente getProponente() {
        return this.proponente;
    }
}
