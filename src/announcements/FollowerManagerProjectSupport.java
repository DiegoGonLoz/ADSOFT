package announcements;

import proyectos.ProyectoParticipativo;

/**
 * Clase que representa a FollowerManagerAllMessages
 *
 * @author Diego Gonzalez
 */
public class FollowerManagerProjectSupport extends FollowerManager {
    /**Proyecto participativo*/
    private final ProyectoParticipativo proyecto;
    /**Booleano con la información de avisado o no*/
    private boolean avisado = false;

    /**
     * Constructor de la clase FollowerManagerFrecuency
     * @param follower follower
     * @param proyecto proyecto participativo
     * @throws NullPointerException no se admiten nulls
     */
    public FollowerManagerProjectSupport(Follower follower, ProyectoParticipativo proyecto) throws NullPointerException{
        super(follower);
        if(proyecto == null){
            throw new NullPointerException("Error en el constructor FollowerManagerProjectSupport: Proyecto null");
        }
        this.proyecto = proyecto;
    }

    /**
     * Metodo announce
     * @param t objeto a anunciar
     */
    @Override
    public void announce(Announcement t) {
        if(!this.avisado && proyecto.obtenerApoyos() > this.umbral){
            follower.receives(t);
            this.avisado = true;
        }
    }

    /**
     * Setter de umbral
     * @param n nuevo umbral
     * @return true o false
     */
    @Override
    public boolean setUmbral(int n){
        if(super.setUmbral(n)){
            this.avisado = false;
            return true;
        }
        return false;
    }
}
