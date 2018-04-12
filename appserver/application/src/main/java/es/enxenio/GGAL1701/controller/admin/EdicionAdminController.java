package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.edicion.EdicionService;
import es.enxenio.GGAL1701.model.dominio.edicion.Edicion;
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
 * Created by hlorenzo on 03/09/2017.
 */

@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/edicion")
public class EdicionAdminController {



    @Inject
    private EdicionService edicionService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Edicion> saveOrUpdate(@Valid @RequestBody Edicion edicion) {
        try {
            if (edicion.getId() == null) {
                return new ResponseEntity<>(edicionService.save(edicion),
                    HeaderUtil.createAlert("admin.edicion.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(edicionService.update(edicion),
                    HeaderUtil.createAlert("admin.edicion.edit.success"), HttpStatus.OK);
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
            edicionService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.edicion.delete.success"), HttpStatus.OK);
    }


}
