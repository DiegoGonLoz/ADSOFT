package proyectos;

import proponentes.EnteCiudadano;
import proponentes.Proponente;

import java.util.List;

public class ProyectoFundacion extends ProyectoParticipativo{
    private double presupuestoEstimado;
    private int porcentajeAsumeFundacion;

    public ProyectoFundacion(String titulo, String descripcion, Proponente proponente, List<EnteCiudadano> apoyos, double presupuestoEstimado, int porcentajeAsumeFundacion) {
        super(titulo, descripcion, proponente, apoyos);
        this.presupuestoEstimado = presupuestoEstimado;
        this.porcentajeAsumeFundacion = porcentajeAsumeFundacion;
    }


}
