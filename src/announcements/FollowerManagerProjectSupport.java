package announcements;

import proyectos.ProyectoParticipativo;

public class FollowerManagerProjectSupport extends FollowerManager {
    private final ProyectoParticipativo proyecto;
    private boolean avisado = false;

    public FollowerManagerProjectSupport(Follower follower, ProyectoParticipativo proyecto) throws NullPointerException{
        super(follower);
        if(proyecto == null){
            throw new NullPointerException("Error en el constructor FollowerManagerProjectSupport: Proyecto null");
        }
        this.proyecto = proyecto;
    }

    @Override
    public void announce(Announcement t) {
        if(!this.avisado && proyecto.obtenerApoyos() > this.umbral){
            follower.receives(t);
            this.avisado = true;
        }
    }

    @Override
    public boolean setUmbral(int n){
        if(super.setUmbral(n)){
            this.avisado = false;
            return true;
        }
        return false;
    }
}
