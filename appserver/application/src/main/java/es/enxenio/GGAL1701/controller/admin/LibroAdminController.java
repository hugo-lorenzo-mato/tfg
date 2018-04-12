package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.libro.Libro;
import es.enxenio.GGAL1701.model.dominio.libro.LibroService;
import es.enxenio.GGAL1701.util.ConstantesRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.validation.Valid;

/**
 * Created by hlorenzo on 08/08/2017.
 **/
@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/libro")
public class LibroAdminController {



    @Inject
    private LibroService libroService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Libro> saveOrUpdate(@Valid @RequestBody Libro libro) {
        try {
            if (libro.getId() == null) {
                return new ResponseEntity<>(libroService.save(libro),
                    HeaderUtil.createAlert("admin.libro.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(libroService.update(libro),
                    HeaderUtil.createAlert("admin.libro.edit.success"), HttpStatus.OK);
            }
        } catch (InstanceAlreadyExistsException e) {
            return new ResponseEntity<>(
                HeaderUtil.createFailureAlert("admin.form.error.alreadyexists"), HttpStatus.BAD_REQUEST);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            libroService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.libro.delete.success"), HttpStatus.OK);
    }

}
