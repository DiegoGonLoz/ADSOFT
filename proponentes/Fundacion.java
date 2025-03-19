package proponentes;

public class Fundacion extends Proponente{
    private final String CIF;

    public Fundacion(String name, String contrasena, String cif) {
        super(name, contrasena);
        if(this.validarCIF(cif)){
            this.CIF = cif;
        } else {
            throw new IllegalArgumentException("CIF no valido");
        }
    }

    private boolean validarCIF(String cif) {

    }

}
