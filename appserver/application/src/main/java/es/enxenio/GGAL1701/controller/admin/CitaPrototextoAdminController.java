package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto.CitaPrototexto;
import es.enxenio.GGAL1701.model.dominio.citas.citaPrototexto.CitaPrototextoService;
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
@RequestMapping(ConstantesRest.URL_ADMIN + "/cita-prototexto")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class CitaPrototextoAdminController {

    @Inject
    private CitaPrototextoService citaPrototextoService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<CitaPrototexto> saveOrUpdate(@Valid @RequestBody CitaPrototexto citaPrototexto) {
        try {
            if (citaPrototexto.getId() == null) {
                return new ResponseEntity<>(citaPrototextoService.save(citaPrototexto),
                    HeaderUtil.createAlert("admin.citaPrototexto.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(citaPrototextoService.update(citaPrototexto),
                    HeaderUtil.createAlert("admin.citaPrototexto.edit.success"), HttpStatus.OK);
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
            citaPrototextoService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.citaPrototexto.delete.success"), HttpStatus.OK);
    }


}
