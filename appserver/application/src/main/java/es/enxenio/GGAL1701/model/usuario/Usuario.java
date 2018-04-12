package es.enxenio.GGAL1701.model.usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.enxenio.GGAL1701.model.generic.GenericEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(schema = "public", name = "usuario")
public class Usuario extends GenericEntity {

    @NotNull
    private String login;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol role;

    private boolean locked;

    @Version
    @JsonIgnore
    private long version;

    //

    public Usuario() {
    }

    //

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<String> getAuthorities() {
        return Arrays.asList(role.toString());
    }

}
