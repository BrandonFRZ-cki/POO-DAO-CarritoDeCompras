package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase utilitaria que proporciona métodos para formatear
 * valores monetarios y fechas según una {@link Locale} específica.
 *
 * Se utiliza para mostrar la información de forma adecuada
 * en función de la configuración regional del sistema o usuario.
 *
 * @author Brandon
 * @version 1.0
 */
public class FormateadorUtils {

    /**
     * Formatea un valor monetario según el estilo de moneda del locale especificado.
     *
     * @param cantidad Valor numérico que representa una cantidad de dinero.
     * @param locale Configuración regional (idioma y país).
     * @return Cadena con el valor formateado como moneda.
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Formatea una fecha según el estilo medio de presentación
     * del locale especificado (por ejemplo, "18 jul 2025").
     *
     * @param fecha Fecha a formatear.
     * @param locale Configuración regional (idioma y país).
     * @return Cadena con la fecha formateada.
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }

}
