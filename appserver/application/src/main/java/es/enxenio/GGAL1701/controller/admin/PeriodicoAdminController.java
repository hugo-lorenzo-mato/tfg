package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.periodico.PeriodicoService;
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
 * Created by hlorenzo on 10/08/2017.
 **/
@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/periodico")
public class PeriodicoAdminController {

    @Inject
    private PeriodicoService periodicoService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Periodico> saveOrUpdate(@Valid @RequestBody Periodico periodico) {
        try {
            if (periodico.getId() == null) {
                return new ResponseEntity<>(periodicoService.save(periodico),
                    HeaderUtil.createAlert("admin.periodico.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(periodicoService.update(periodico),
                    HeaderUtil.createAlert("admin.periodico.edit.success"), HttpStatus.OK);
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
            periodicoService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.periodico.delete.success"), HttpStatus.OK);
    }

}
