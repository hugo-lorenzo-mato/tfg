package es.enxenio.GGAL1701.util;

/**
 * Created by hlorenzo on 24/08/2017
 */
public class ConstantesModel {

    public static final String URL_CARPETA_ARCHIVOS_TEMPORALES = "/tmp";
    public static final String URL_CARPETA_UPLOAD = "/upload";


    /**
     * Separo cada tipo de entidad de la producción activa en carpetas y subcarpetas
     */

    /** PROTOTEXTOS **/

    // Sólo hay una imagen y la URl de la carpeta para prototextos es común

    public static final String URL_CARPETA_PROTOTEXTOS = URL_CARPETA_UPLOAD + "/prototextos/%d";
    public static final String URL_CARPETA_PROTOTEXTOS_IMAGEN = URL_CARPETA_PROTOTEXTOS + "/imagen";


    // Carpeta de archivos para Prototexto Digital COMPLETO

    public static final String URL_CARPETA_PROTOTEXTOS_COMPLETOS = URL_CARPETA_PROTOTEXTOS + "/digital-completo/";

    // Carpeta de archivos para Prototexto Transcripcion

    public static final String URL_CARPETA_PROTOTEXTOS_TRANSCRIPCION = URL_CARPETA_PROTOTEXTOS + "/transcripcion/";

    // Carpeta de archivos para Prototexto Crítica Genética

    public static final String URL_CARPETA_PROTOTEXTOS_CRITICA = URL_CARPETA_PROTOTEXTOS + "/critica-genetica/";

    // Otros archivos

    public static final String URL_CARPETA_PROTOTEXTOS_OTROS = URL_CARPETA_PROTOTEXTOS + "/otros/";

    /** LIBROS **/

    // Sólo hay una imagen y la URl de la carpeta para periodicos es común

    public static final String URL_CARPETA_LIBROS = URL_CARPETA_UPLOAD + "/libros/%d";
    public static final String URL_CARPETA_LIBROS_IMAGEN = URL_CARPETA_LIBROS + "/imagen";


    // Carpeta de archivos para Periodico Digital COMPLETO

    public static final String URL_CARPETA_LIBROS_COMPLETOS = URL_CARPETA_LIBROS + "/digital-completo/";

    // Carpeta de archivos para Periodico Transcripcion

    public static final String URL_CARPETA_LIBROS_TRANSCRIPCION = URL_CARPETA_LIBROS + "/transcripcion/";

    // Carpeta de archivos para Periodico Crítica Genética

    public static final String URL_CARPETA_LIBROS_CRITICA = URL_CARPETA_LIBROS + "/critica-genetica/";

    // Otros archivos

    public static final String URL_CARPETA_LIBROS_OTROS = URL_CARPETA_LIBROS + "/otros/";


    /** PERIODICOS **/

    // Sólo hay una imagen y la URl de la carpeta para periodicos es común

    public static final String URL_CARPETA_PERIODICOS = URL_CARPETA_UPLOAD + "/periodicos/%d";
    public static final String URL_CARPETA_PERIODICOS_IMAGEN = URL_CARPETA_PERIODICOS + "/imagen";


    // Carpeta de archivos para Periodico Digital COMPLETO

    public static final String URL_CARPETA_PERIODICOS_COMPLETOS = URL_CARPETA_PERIODICOS + "/digital-completo/";

    // Carpeta de archivos para Periodico Transcripcion

    public static final String URL_CARPETA_PERIODICOS_TRANSCRIPCION = URL_CARPETA_PERIODICOS + "/transcripcion/";

    // Carpeta de archivos para Periodico Crítica Genética

    public static final String URL_CARPETA_PERIODICOS_CRITICA = URL_CARPETA_PERIODICOS + "/critica-genetica/";

    // Otros archivos

    public static final String URL_CARPETA_PERIODICOS_OTROS = URL_CARPETA_PERIODICOS + "/otros/";

    // Configuración

    public static final String PREFIJO_IMAGEN_NORMAL = "n_";
    public static final int ANCHO_IMAGEN_NORMAL = 180;
    public static final int ALTO_IMAGEN_NORMAL = 240;
    public static final int ANCHO_IMAGEN_PERFIL_NORMAL = 100;
    public static final int ALTO_IMAGEN_PERFIL_NORMAL = 100;
    public static final String[] EXTENSIONES_VALIDAS = {"pdf", "doc", "docx", "odt", "rtf", "tif", "jpeg", "png", "gif",
                                                        "jpg", "txt", "zip", "TXT", "mp3", "mp4", "mkv", "Wma", "Wav",
                                                        "MIDI", "mmf", "Ogg", "Aac", "Raw", "FLV", "MPG", "MPEG", "ASF", "DIVX",
                                                        "divx", "MOV", "mov", "txt"};

    // Eliminado periódico de imágenes temporales (Para pruebas locales: 0/50 * * * * ?) (0 0 5 * * ?)

    public static final String LIMPIADO_ARCHIVOS_TEMPORALES_PERIODO = "0 0 5 * * ?";

}
