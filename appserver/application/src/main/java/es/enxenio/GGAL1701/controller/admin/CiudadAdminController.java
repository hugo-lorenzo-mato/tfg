package es.enxenio.GGAL1701.controller.admin;


import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.Ciudad;
import es.enxenio.GGAL1701.model.dominio.lugar.ciudad.CiudadService;
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
 * Created by crodriguez on 06/06/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_ADMIN + "/ciudad")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class CiudadAdminController {

    @Inject
    private CiudadService ciudadService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Ciudad> saveOrUpdate(@Valid @RequestBody Ciudad ciudad) {
        try {
            if (ciudad.getId() == null) {
                return new ResponseEntity<>(ciudadService.save(ciudad),
                    HeaderUtil.createAlert("admin.ciudad.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(ciudadService.update(ciudad),
                    HeaderUtil.createAlert("admin.ciudad.edit.success"), HttpStatus.OK);
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
            ciudadService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.ciudad.delete.success"), HttpStatus.OK);
    }

}
