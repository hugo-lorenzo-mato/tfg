package es.enxenio.GGAL1701.controller.admin;


import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.tipologia.Tipologia;
import es.enxenio.GGAL1701.model.dominio.tipologia.TipologiaService;
import es.enxenio.GGAL1701.model.dominio.tipologia.TipologiaService;
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
 * Created by hlorenzo on 06/09/2017.
 */
@RestController
@RequestMapping(ConstantesRest.URL_ADMIN + "/tipologia")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class TipologiaAdminController {

    @Inject
    private TipologiaService tipologiaService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Tipologia> saveOrUpdate(@Valid @RequestBody Tipologia tipologia) {
        try {
            if (tipologia.getId() == null) {
                return new ResponseEntity<>(tipologiaService.save(tipologia),
                    HeaderUtil.createAlert("admin.tipologia.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(tipologiaService.update(tipologia),
                    HeaderUtil.createAlert("admin.tipologia.edit.success"), HttpStatus.OK);
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
            tipologiaService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.tipologia.delete.success"), HttpStatus.OK);
    }

}
