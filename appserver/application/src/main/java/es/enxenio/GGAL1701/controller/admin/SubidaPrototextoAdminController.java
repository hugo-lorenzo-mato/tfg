package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototexto;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaPrototexto.SubidaPrototextoService;
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
@RequestMapping(ConstantesRest.URL_ADMIN + "/subidaPrototexto")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class SubidaPrototextoAdminController {

    @Inject
    private SubidaPrototextoService subidaPrototextoService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SubidaPrototexto> saveOrUpdate(@Valid @RequestBody SubidaPrototexto subidaPrototexto, long id) {
        try {
            if (subidaPrototexto.getId() == null) {
                return new ResponseEntity<>(subidaPrototextoService.save(subidaPrototexto, id),
                    HeaderUtil.createAlert("admin.subidas.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(subidaPrototextoService.update(subidaPrototexto, id),
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
            subidaPrototextoService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.subidas.delete.success"), HttpStatus.OK);
    }


}
