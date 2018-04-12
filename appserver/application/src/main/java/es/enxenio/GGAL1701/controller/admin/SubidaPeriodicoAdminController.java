package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodico;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodicoService;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPeriodico.SubidaPeriodicoService;
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
 * Created by hlorenzo on 31/08/2017.
 **/
@RestController
@RequestMapping(ConstantesRest.URL_ADMIN + "/subidaPeriodico")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class SubidaPeriodicoAdminController {

    @Inject
    private SubidaPeriodicoService subidaPeriodicoService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SubidaPeriodico> saveOrUpdate(@Valid @RequestBody SubidaPeriodico subidaPeriodico, long id) {
        try {
            if (subidaPeriodico.getId() == null) {
                return new ResponseEntity<>(subidaPeriodicoService.save(subidaPeriodico, id),
                    HeaderUtil.createAlert("admin.subidas.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(subidaPeriodicoService.update(subidaPeriodico, id),
                    HeaderUtil.createAlert("admin.subidas.edit.success"), HttpStatus.OK);
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
            subidaPeriodicoService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.subidas.delete.success"), HttpStatus.OK);
    }


}
