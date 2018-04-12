package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.custom.PasswordDTO;
import es.enxenio.GGAL1701.controller.custom.UserDTO;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.usuario.Usuario;
import es.enxenio.GGAL1701.model.usuario.UsuarioService;
import es.enxenio.GGAL1701.model.usuario.exception.ContrasenaIncorrectaException;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;

/**
 * Operaciones para gestionar usuarios.
 * Created by crodriguez on 19/05/2016.
 */
@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ADMIN)
@RequestMapping(ConstantesRest.URL_ADMIN + "/user")
public class UsuarioAdminController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getUser(@PathVariable("login") String login) {

        return usuarioService.get(login).map(user -> new ResponseEntity<>(user, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HeaderUtil.createFailureAlert("admin.list-users.error.notfound"), HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<Page<Usuario>> findAll(PageableFilter pageable) {
        if (pageable != null) {
            return new ResponseEntity<>(usuarioService.findAll(pageable), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(null);
    }

    // Gestión
    @PreAuthorize(ConstantesRest.HAS_ROLE_ADMIN)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {

        try {
            usuarioService.create(userDTO);
        } catch (InstanceAlreadyExistsException e) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("admin.register.error.alreadyexists")).build();
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("admin.register.success")).build();
    }

    @PreAuthorize(ConstantesRest.HAS_ROLE_ADMIN)
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity delete(@RequestParam Long id) {

        try {
            usuarioService.delete(id);
        } catch (InstanceNotFoundException e) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("admin.list-users.delete.error")).build();
        }
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("admin.list-users.delete.success")).build();
    }

    @PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody PasswordDTO passwordDTO) {
        try {
            usuarioService.changePassword(passwordDTO.getPassword(), passwordDTO.getNewpassword());
        } catch (ContrasenaIncorrectaException e) {
            return new ResponseEntity<>(HeaderUtil.createFailureAlert("admin.changepassword.error.incorrect"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.changepassword.success"), HttpStatus.OK);
    }

}
