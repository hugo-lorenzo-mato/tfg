package es.enxenio.GGAL1701.model.usuario;


import es.enxenio.GGAL1701.controller.custom.UserDTO;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.usuario.exception.ContrasenaIncorrectaException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Optional;

public interface UsuarioService extends UserDetailsService {

    Optional<Usuario> get(String login);

    Page<Usuario> findAll(PageableFilter pageable);

    Usuario getUserWithAuthorities();

    void create(UserDTO usuarioDTO) throws InstanceAlreadyExistsException;

    void delete(Long id) throws InstanceNotFoundException;

    void changePassword(String oldpassword, String newpassword) throws ContrasenaIncorrectaException;

}
