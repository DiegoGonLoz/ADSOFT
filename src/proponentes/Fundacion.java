package proponentes;

import proyectos.ProyectoFundacion;

public class Fundacion extends Proponente implements FollowedEntity{
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
        if (cif == null || cif.isEmpty()) {
            return false;
        }

        if (!cif.matches("^[ABCDEFGHJKLMNPQRSUVW]\\d{7}[0-9A-J]$")) {
            return false;
        }

        char tipo = cif.charAt(0);
        String numeroStr = cif.substring(1, 8);
        char digitoControl = cif.charAt(8);

        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 0; i < numeroStr.length(); i++) {
            int digito = Character.getNumericValue(numeroStr.charAt(i));

            if ((i + 1) % 2 == 0) {
                sumaPares += digito;
            } else {
                int doble = digito * 2;
                sumaImpares += (doble > 9) ? (doble - 9) : doble;
            }
        }

        int sumaTotal = sumaPares + sumaImpares;

        int digitoCalculado = (10 - (sumaTotal % 10)) % 10;

        if (Character.isLetter(digitoControl)) {
            String letrasControl = "JABCDEFGHI";
            char letraCalculada = letrasControl.charAt(digitoCalculado);
            return digitoControl == letraCalculada;
        } else {
            int digitoControlNum = Character.getNumericValue(digitoControl);
            return digitoCalculado == digitoControlNum;
        }
    }

    public void proponer(Fundacion fundacion){

    }

}
