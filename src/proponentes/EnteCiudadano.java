package proponentes;

import announcements.FollowedEntity;
import announcements.Follower;
import proyectos.ProyectoParticipativo;

import java.util.LinkedList;
import java.util.List;

public abstract class EnteCiudadano extends Proponente implements Follower {
    protected LinkedList<Asociacion> inscrito = new LinkedList<Asociacion>();
    protected LinkedList<FollowedEntity> seguidos = new LinkedList<FollowedEntity>();

    public EnteCiudadano(String nombre, String contraseña){
        super(nombre, contraseña);
    }

    public abstract void proponer(ProyectoParticipativo proyecto);

    public abstract boolean esMiembro(EnteCiudadano enteCiudadano);

    public abstract int cantidadMiembros();

    public abstract List<Ciudadano> todosLosCiudadanos();
}
