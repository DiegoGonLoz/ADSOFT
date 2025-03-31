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
        // Comprobación básica de nulo o vacío
        if (cif == null || cif.isEmpty()) {
            return false;
        }

        // Expresión regular para formato básico de CIF
        if (!cif.matches("^[ABCDEFGHJKLMNPQRSUVW]\\d{7}[0-9A-J]$")) {
            return false;
        }

        // Extraer partes del CIF
        char tipo = cif.charAt(0); // Letra de tipo de organización
        String numeroStr = cif.substring(1, 8); // 7 dígitos centrales
        char digitoControl = cif.charAt(8); // Dígito de control (letra o número)

        // 1. Calcular suma de los dígitos pares e impares
        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 0; i < numeroStr.length(); i++) {
            int digito = Character.getNumericValue(numeroStr.charAt(i));

            if ((i + 1) % 2 == 0) { // Posiciones pares (2º, 4º y 6º dígito)
                sumaPares += digito;
            } else { // Posiciones impares (1º, 3º, 5º y 7º dígito)
                int doble = digito * 2;
                sumaImpares += (doble > 9) ? (doble - 9) : doble;
            }
        }

        // 2. Calcular suma total
        int sumaTotal = sumaPares + sumaImpares;

        // 3. Obtener dígito de control calculado
        int digitoCalculado = (10 - (sumaTotal % 10)) % 10;

        // 4. Validar según tipo de CIF
        if (Character.isLetter(digitoControl)) {
            // Para tipos A, B, E, H: el dígito de control es una letra
            String letrasControl = "JABCDEFGHI";
            char letraCalculada = letrasControl.charAt(digitoCalculado);
            return digitoControl == letraCalculada;
        } else {
            // Para otros tipos: el dígito de control es un número
            int digitoControlNum = Character.getNumericValue(digitoControl);
            return digitoCalculado == digitoControlNum;
        }
    }

}
