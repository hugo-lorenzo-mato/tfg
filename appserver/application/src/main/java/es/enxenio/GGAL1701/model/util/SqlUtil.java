package es.enxenio.GGAL1701.model.util;

import org.apache.commons.lang3.StringUtils;

public class SqlUtil {

    /**
     * Método que compara un campo con una expresión regular para comprobar si cumple la condición.
     *
     * @param campoBD   expresión regular
     * @param parameter nombre del parámetro que será sustituido a posteriori. Aquí se incluirá la expresión regular generada
     * @return String SQL con la comprobación
     */
    public static String compararParamConRegExp(String campoBD, String parameter) {
        return " length(substring(unaccent(lower(" + campoBD + ")), :" + parameter + ")) > 0 ";
    }

    /**
     * Genera una consulta . Utilizado para ordenación.
     *
     * @param campoBD campo de la entidad que se quiere normalizar
     * @return SQL para ignorar símbolos y acentos
     */
    public static String eliminarSimbolosRegExp(String campoBD) {
        return " REGEXP_REPLACE(lower(unaccent(" + campoBD + ")), '^[^a-zA-Z]', '')";
    }

    /**
     * A partir de un String de búsqueda (Ej: "plus ultra"), genera una expresión regular que permite búsqueda por literales (""), comodines (_ y %).
     * Ejemplo de expresión generada: ^(?=.*plus)(?=.*ultra).*$
     *
     * @param query consulta que se está haciendo
     * @return expresión regular generada
     */
    public static String convertirQueryARegExp(String query) {
        //FIXME: Stripaccents cambia la ñ por n, además, no es posible usarla en expresiones regulares en postgres como la que se está formando debajo
        StringBuilder strNormalized = new StringBuilder(StringUtils.stripAccents(query.toLowerCase()).replace("\\", "")
            .replace(".", "\\.").replace("*", "\\*").replace("(", "\\(")
            .replace(")", "\\)").replace("+", "\\+").replace("[", "\\[")
            .replace("]", "\\]").replace("_", ".").replace("%", ".*"));

        StringBuilder regExp = new StringBuilder("^");
        String[] literals = StringUtils.substringsBetween(strNormalized.toString(), "\"", "\"");
        String strNormalizedWithoutLiterals = new String(strNormalized);
        if (literals != null) {
            for (String literal : literals) {
                strNormalizedWithoutLiterals = strNormalizedWithoutLiterals.replace(literal, "");
                regExp.append("(?=.*");
                regExp.append(literal);
                regExp.append(")");
            }
        }
        String[] parts = strNormalizedWithoutLiterals.replace("\"", "").split(" ");
        for (String part : parts) {
            if (part.length() > 0) {
                regExp.append("(?=.*");
                regExp.append(part);
                regExp.append(")");
            }
        }
        regExp.append(".*$");
        System.out.println("RegExp -> " + regExp.toString());
        return regExp.toString();
    }

}
