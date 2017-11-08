/*
 * NumeroALetraUtil.java
 *
 */

package siayf.rh.reportes.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * Esta clase ayuda en la conversión de números a letras.
 * 
 * <p>En la conversión de las cantidades se considera como configuración el 
 * español y como región México, para transcripción de las cantidades en 
 * letras.</p>
 * 
 * <p>Basado en el trabajo de Rene Gómez.
 * (<a href="https://github.com/Axiacore/numero-a-letras/blob/master/java/NumberToLetterConverter.java">código en GitHub</a>)</p>
 *
 * @author Rene Gómez
 * @author Freddy Barrera (freddy.barrera.moo@gmail.com)
 * @author Eduardo Chuc Mex (Lic.Eduardo_Mex@hotmail.com)
 */
public class NumeroALetraUtil implements Serializable {

    /** Número para serializar la clase. */
    private static final long serialVersionUID = -7523110768389321147L;
    
    private static final String[] UNIDADES = {
        "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ",
        "OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ", 
        "QUINCE ", "DIECISEIS", "DIECISIETE", "DIECIOCHO", "DIECINUEVE", 
        "VEINTE"
    };

    private static final String[] DECENAS = {
        "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ",
        "OCHENTA ", "NOVENTA ", "CIEN "
    };

    private static final String[] CENTENAS = {
        "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ", 
        "QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", 
        "NOVECIENTOS "
    };
    
    // TODO: Usar al escribir los decimales.
    private static final String[] DECIMALES = {
        "DÉCIMAS", "CENTÉSIMAS", "MILÉSIMAS", "DIEZMILÉSIMAS", "CIENMILÉSIMAS", 
        "MILLONÉSIMAS"
    };

    /**
     * Por ser una utilería el constructor no debe ser accesible para crear una 
     * nueva instancia usuado la palabra reservada <code>new</code>.
     */
    private NumeroALetraUtil() {
    }
    
    /**
     * Permite realizar la conversión de un número en texto escrito.
     * 
     * @param numero el número a convertir.
     * @return una cadena con la representación del número en texto.
     */
    public static String convertir(BigDecimal numero) {

        if (numero.scale() != 2) {
            throw new NumberFormatException("El número " + numero + " no tiene el formato moneda a 2 decimales.");
        }

        numero = NumeroUtil.redondear(numero);
        return NumeroALetraUtil.convertirNumeroALetra(numero);
    }

    /**
     * Convierte a letras a un número de la forma $123,456.32
     *
     * @param number número en representacion texto
     * @return número en letras.
     * @throws NumberFormatException Si valor del número no es valido (fuera de
     * rango)
     */
    public static String convertirNumeroALetra(String number) throws NumberFormatException {
        return NumeroALetraUtil.convertirNumeroALetra(new BigDecimal(number));
    }
    
    /**
     * Convierte a letras a un número de la forma $123,456.32
     *
     * @param number número en representacion texto.
     * @param moneda indica si se mostraran los decimales como moneda.
     * @return número en letras.
     * @throws NumberFormatException Si valor del número no es valido (fuera de
     * rango).
     */
    public static String convertirNumeroALetra(String number, boolean moneda) throws NumberFormatException {
        return convertirNumeroALetra(new BigDecimal(number), moneda);
    }

    /**
     * Convierte un numero en representacion númerica a uno en representación de
     * texto.
     * 
     * <p><strong>Nota:</strong>El numero es valido si esta entre 0 y 
     * 999,999.999</p>
     *
     * @param doubleNumber el número a convertir.
     * @return número convertido a texto.
     * @throws NumberFormatException si el número esta fuera del rango.
     */
    public static String convertirNumeroALetra(final BigDecimal doubleNumber) throws NumberFormatException {
        return convertirNumeroALetra(doubleNumber, true);
    }
    
    /**
     * Convierte un numero en representacion númerica a uno en representación de
     * texto.
     * 
     * <p><strong>Nota:</strong>El numero es valido si esta entre 0 y 
     * 999,999.999</p>
     *
     * @param numero el número a convertir.
     * @param moneda indica si se mostraran los decimales como moneda.
     * @return número convertido a texto.
     * @throws NumberFormatException si el número esta fuera del rango.
     */
    public static String convertirNumeroALetra(final BigDecimal numero, final boolean moneda) throws NumberFormatException {
        numero.setScale(3);

        // Validamos que sea un número legal //doubleNumber > 999 999 999
        if (numero.compareTo(new BigDecimal("999999999")) == 1) {
            throw new NumberFormatException("El número es mayor de 999,999.999 no es posible convertirlo");
        }

        if (numero.compareTo(new BigDecimal("0")) == -1) {
            throw new NumberFormatException("El número debe ser positivo");
        }

        String splitNumber[] = String.valueOf(numero).replace('.', '#').split("#");
        StringBuilder converted = new StringBuilder();

        // Descompone el trio de millones
        int millon = Integer.parseInt(String.valueOf(obtenerDigitoEn(splitNumber[0], 8))
                + String.valueOf(obtenerDigitoEn(splitNumber[0], 7)) + String.valueOf(obtenerDigitoEn(splitNumber[0], 6)));
        if (millon == 1) {
            converted.append("UN MILLÓN ");
        } else if (millon > 1) {
            converted.append(convertirNumero(String.valueOf(millon)));
            converted.append("MILLONES ");
        }

        // Descompone el trio de miles
        int miles = Integer.parseInt(String.valueOf(obtenerDigitoEn(splitNumber[0], 5))
                + String.valueOf(obtenerDigitoEn(splitNumber[0], 4)) + String.valueOf(obtenerDigitoEn(splitNumber[0], 3)));
        if (miles == 1) {
            converted.append(" MIL ");
        } else if (miles > 1) {
            converted.append(convertirNumero(String.valueOf(miles)));
            converted.append(" MIL ");
        }

        // Descompone el ultimo trio de unidades
        int cientos = Integer.parseInt(String.valueOf(obtenerDigitoEn(splitNumber[0], 2))
                + String.valueOf(obtenerDigitoEn(splitNumber[0], 1)) + String.valueOf(obtenerDigitoEn(splitNumber[0], 0)));
        if (cientos == 1) {
            converted.append("UN");
        }

        if (millon + miles + cientos == 0) {
            converted.append("CERO");
        }

        if (cientos > 1) {
            converted.append(convertirNumero(String.valueOf(cientos)));
        }

        if(moneda) {
            converted.append(" PESOS ");

            // Descompone los centavos
            int centavos;
            if(splitNumber.length > 1) {
                centavos = Integer.parseInt(String.valueOf(obtenerDigitoEn(splitNumber[1], 2))
                        + String.valueOf(obtenerDigitoEn(splitNumber[1], 1)) + String.valueOf(obtenerDigitoEn(splitNumber[1], 0)));
            } else {
                centavos = 0;
            }
            
            String strCentavos = StringUtils.leftPad(centavos + "", 2, '0');
            converted.append(strCentavos);
            converted.append("/100 M.N.");
        } else {
            /* 
                TODO Transformar los decimales en letras por ejemplo:
                12.35  doce unidades, treinta y cinco centésimas.
                 8.4   ocho unidades, cuatro décimas.
            */
        }

        return converted.toString().trim();
    }

    /**
     * Convierte los trios de números que componen las unidades, las decenas y
     * las centenas del número.
     *
     * @param number número a convetir en digitos
     * @return número convertido en letras
     */
    private static String convertirNumero(String number) {
        if (number.length() > 3) {
            throw new NumberFormatException("La longitud máxima debe ser 3 dígitos");
        }

        // Caso especial con el 100
        if (number.equals("100")) {
            return "CIEN ";
        }

        StringBuilder output = new StringBuilder();
        if (obtenerDigitoEn(number, 2) != 0) {
            output.append(CENTENAS[obtenerDigitoEn(number, 2) - 1]);
        }

        int k = Integer.parseInt(String.valueOf(obtenerDigitoEn(number, 1)) + String.valueOf(obtenerDigitoEn(number, 0)));
        if (k <= 20) {
            output.append(UNIDADES[k]);
        } else if (k > 30 && obtenerDigitoEn(number, 0) != 0) {
            output.append(DECENAS[obtenerDigitoEn(number, 1) - 2]);
            output.append("Y ");
            output.append(UNIDADES[obtenerDigitoEn(number, 0)]);
        } else {
            output.append(DECENAS[obtenerDigitoEn(number, 1) - 2]);
            output.append(UNIDADES[obtenerDigitoEn(number, 0)]);
        }

        return output.toString();
    }

    /**
     * Retorna el dígito numérico en la posición indicada de derecha a izquierda.
     *
     * @param origin cadena en la cual se busca el dígito.
     * @param position posicion de derecha a izquierda a retornar.
     * @return dígito ubicado en la posicion indicada.
     */
    private static int obtenerDigitoEn(String origin, int position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - '0';
        }

        return 0;
    }
}
