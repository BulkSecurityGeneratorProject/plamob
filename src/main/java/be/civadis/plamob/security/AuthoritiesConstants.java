package be.civadis.plamob.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String RESP_DOMAINE = "ROLE_RESP_DOMAINE";

    public static final String RESP_RESSOURCE_MOBILE = "ROLE_RESP_RESSOURCE_MOBILE";

    public static final String RESSOURCE_MOBILE = "ROLE_RESSOURCE_MOBILE";

    public static final String INTERVENANT = "ROLE_INTERVENANT";

    private AuthoritiesConstants() {
    }
}
