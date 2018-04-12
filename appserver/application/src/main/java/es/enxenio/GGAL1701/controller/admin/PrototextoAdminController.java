package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.prototexto.Prototexto;
import es.enxenio.GGAL1701.model.dominio.prototexto.PrototextoService;
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
 * Created by hlorenzo on 05/08/2017.
 */

@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/prototexto")
public class PrototextoAdminController {



    @Inject
    private PrototextoService prototextoService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Prototexto> saveOrUpdate(@Valid @RequestBody Prototexto prototexto) {
        try {
            if (prototexto.getId() == null) {
                return new ResponseEntity<>(prototextoService.save(prototexto),
                    HeaderUtil.createAlert("admin.prototexto.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(prototextoService.update(prototexto),
                    HeaderUtil.createAlert("admin.prototexto.edit.success"), HttpStatus.OK);
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
            prototextoService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.prototexto.delete.success"), HttpStatus.OK);
    }


}
