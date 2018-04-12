package es.enxenio.GGAL1701.controller.custom;


import es.enxenio.GGAL1701.model.usuario.Rol;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by crodriguez on 24/05/2016.
 */
public class UserDTO {

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    private boolean locked;

    UserDTO() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRole() {
        return role;
    }

    public boolean isLocked() {
        return locked;
    }

}
