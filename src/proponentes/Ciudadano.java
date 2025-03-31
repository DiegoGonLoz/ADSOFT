package proponentes;

public class Ciudadano {
    private final String DNI;

    public Ciudadano(){

    }

    private boolean validarDni(String dni) {
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";


        if (dni == null ||  dni.isEmpty()){
            return false;
        }

        if (!dni.matches("^\\d{8}[A-Z]$")) {
            return false;
        }

        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = dni.charAt(8);

        return letra == LETRAS.charAt(numero % 23);
    }


}
