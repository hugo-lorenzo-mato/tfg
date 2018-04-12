package es.enxenio.GGAL1701.util;

/**
 * Created by Carlos on 22/05/2016.
 */
public class ConstantesRest {

    public static final String URL_BASE = "/api";
    public static final String URL_ACCOUNT = URL_BASE + "/account";

    public static final String URL_PUBLIC = URL_BASE + "/public";

    public static final String URL_ADMIN = URL_BASE + "/admin";

    // Autorizaciones para la etiqueta @PreAuthorize
    public static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
    public static final String HAS_ROLE_ANHADIR = "hasAnyRole('ADMIN','CATALOGADOR')";
    public static final String PERMIT_ALL = "permitAll";

}
