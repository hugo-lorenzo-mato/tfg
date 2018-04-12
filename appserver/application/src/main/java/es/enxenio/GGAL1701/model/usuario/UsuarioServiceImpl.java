package es.enxenio.GGAL1701.model.usuario;

import es.enxenio.GGAL1701.config.util.SecurityUtils;
import es.enxenio.GGAL1701.controller.custom.UserDTO;
import es.enxenio.GGAL1701.controller.custom.util.PageableFilter;
import es.enxenio.GGAL1701.model.usuario.exception.ContrasenaIncorrectaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by crodriguez on 18/05/2016.
 */
@Service("usuarioService")
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired(required = false)
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> get(String login) {
        return usuarioRepository.findOneByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(PageableFilter pageable) {
        return usuarioRepository.findAll(pageable.getPageable());
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUserWithAuthorities() {
        String login = SecurityUtils.getCurrentUserLogin();
        return usuarioRepository.findOneByLogin(login).map(usuario -> usuario).orElse(null);
    }

    @Override
    public void create(UserDTO usuarioDTO) throws InstanceAlreadyExistsException {
        if (usuarioRepository.findOneByLogin(usuarioDTO.getLogin()).isPresent()) {
            throw new InstanceAlreadyExistsException();
        }
        Usuario usuario = new Usuario();
        usuario.setLogin(usuarioDTO.getLogin());
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String actualCifrado = encoder.encodePassword(usuarioDTO.getPassword(), usuarioDTO.getLogin());
        usuario.setPassword(actualCifrado);
        // Cambio en registro para dar opción a escoger rol
        usuario.setRole(usuarioDTO.getRole());
        usuario.setLocked(usuarioDTO.isLocked());

        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) throws InstanceNotFoundException {
        Usuario usuario = usuarioRepository.findOne(id);
        if (usuario == null) {
            throw new InstanceNotFoundException();
        }
        usuarioRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findOneByLogin(login).map(u -> u)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario " + login + " no existe"));

        return new org.springframework.security.core.userdetails.User(usuario.getLogin(), usuario.getPassword(),
            true, true, true, !usuario.isLocked(),
            Arrays.asList(new SimpleGrantedAuthority(usuario.getRole().name())));
    }

    @Override
    public void changePassword(String oldpassword, String newpassword) throws ContrasenaIncorrectaException {

        Usuario usuario = usuarioRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin()).map(u -> u)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no existe"));

        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String encryptedOld = encoder.encodePassword(oldpassword, usuario.getLogin());
        if (encryptedOld.equals(usuario.getPassword())) {
            String encryptedPassword = encoder.encodePassword(newpassword, usuario.getLogin());
            usuario.setPassword(encryptedPassword);
            usuarioRepository.save(usuario);
        } else {
            throw new ContrasenaIncorrectaException();
        }
    }

}
