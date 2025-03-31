package proponentes;

public class Ciudadano extends EnteCiudadano{
    private final String NIF;

    public Ciudadano(){

    }

    private boolean validarNIF(String nif) {
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";


        if (nif == null ||  nif.isEmpty()){
            return false;
        }

        if (!nif.matches("^\\d{8}[A-Z]$")) {
            return false;
        }

        int numero = Integer.parseInt(nif.substring(0, 8));
        char letra = nif.charAt(8);

        return letra == LETRAS.charAt(numero % 23);
    }


}
