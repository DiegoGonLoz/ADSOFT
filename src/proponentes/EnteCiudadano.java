package proponentes;

import announcements.FollowedEntity;
import announcements.Follower;
import myExceptions.ErrorAnadiendoCiudadanoExistente;
import proyectos.ProyectoParticipativo;

import java.util.*;

/**
 * Clase que representa a un ente ciudadano
 *
 * @author Diego Gonzalez
 */
public abstract class EnteCiudadano extends Proponente implements Follower {
    /**Set de inscritos*/
    protected Set<Asociacion> inscrito = new HashSet<Asociacion>();
    /**Set de seguidos*/
    protected Set<FollowedEntity> seguidos = new HashSet<FollowedEntity>();

    /**
     * Constructor de la clase EnteCiudadano
     * @param nombre nombre del ente
     * @param contraseña contraseña del ente
     * @throws NullPointerException punteros null no permitidos
     */
    public EnteCiudadano(String nombre, String contraseña) throws NullPointerException{
        super(nombre, contraseña);
    }

    /**
     * Metodo para apoyar un proyecto
     * @param proyecto proyecto a apoyar
     * @return true o false
     */
    public boolean apoyar(ProyectoParticipativo proyecto){
        return proyecto.sumarApoyo(this);
    }

    /**
     * Metodo para inscribirse a una asociación
     * @param asociacion asociacion a la que inscribirse
     * @throws ErrorAnadiendoCiudadanoExistente error ciudadano ya pertenece
     */
    public void inscribirse(Asociacion asociacion) throws ErrorAnadiendoCiudadanoExistente {
        if(asociacion.inscribir(this)){
            inscrito.add(asociacion);
            asociacion.follow(this);
        }
    }

    /**
     * Metodo para saber si es miembro o no otro EnteCiudadano
     * @param enteCiudadano ente a comparar
     * @return true o false
     */
    public abstract boolean esMiembro(EnteCiudadano enteCiudadano);

    /**
     * Metodo para calcular la cantidad de miembros
     * @return cantidad de miembros
     */
    public abstract int cantidadMiembros();
}
