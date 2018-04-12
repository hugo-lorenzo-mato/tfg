package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.citas.citaLibro.CitaLibro;
import es.enxenio.GGAL1701.model.dominio.citas.citaLibro.CitaLibroService;
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
 * Created by hlorenzo on 29/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_ADMIN + "/cita-libro")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class CitaLibroAdminController {

    @Inject
    private CitaLibroService citaLibroService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CitaLibro> saveOrUpdate(@Valid @RequestBody CitaLibro citaLibro) {
        try {
            if (citaLibro.getId() == null) {
                return new ResponseEntity<>(citaLibroService.save(citaLibro),
                    HeaderUtil.createAlert("admin.citaLibro.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(citaLibroService.update(citaLibro),
                    HeaderUtil.createAlert("admin.citaLibro.edit.success"), HttpStatus.OK);
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
            citaLibroService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.citaLibro.delete.success"), HttpStatus.OK);
    }


}
