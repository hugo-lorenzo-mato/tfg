package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.agente.AgenteService;
import es.enxenio.GGAL1701.model.dominio.agente.Agente;
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
 * Created by hlorenzo on 04/09/2017.
 */
@RestController
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
@RequestMapping(ConstantesRest.URL_ADMIN + "/agente")
public class AgenteAdminController {



    @Inject
    private AgenteService agenteService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Agente> saveOrUpdate(@Valid @RequestBody Agente agente) {
        try {
            if (agente.getId() == null) {
                return new ResponseEntity<>(agenteService.save(agente),
                    HeaderUtil.createAlert("admin.agente.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(agenteService.update(agente),
                    HeaderUtil.createAlert("admin.agente.edit.success"), HttpStatus.OK);
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
            agenteService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.agente.delete.success"), HttpStatus.OK);
    }


}
