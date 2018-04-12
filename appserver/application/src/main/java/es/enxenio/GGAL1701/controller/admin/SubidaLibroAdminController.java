package es.enxenio.GGAL1701.controller.admin;

import es.enxenio.GGAL1701.controller.util.HeaderUtil;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibro;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibroService;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibro;
import es.enxenio.GGAL1701.model.dominio.subidas.subidaLibro.SubidaLibroService;
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
@RequestMapping(ConstantesRest.URL_ADMIN + "/subidaLibro")
@PreAuthorize(ConstantesRest.HAS_ROLE_ANHADIR)
public class SubidaLibroAdminController {

    @Inject
    private SubidaLibroService subidaLibroService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SubidaLibro> saveOrUpdate(@Valid @RequestBody SubidaLibro subidaLibro, long id) {
        try {
            if (subidaLibro.getId() == null) {
                return new ResponseEntity<>(subidaLibroService.save(subidaLibro, id),
                    HeaderUtil.createAlert("admin.subidas.create.success"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(subidaLibroService.update(subidaLibro, id),
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
            subidaLibroService.delete(id);
        } catch (InstanceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HeaderUtil.createAlert("admin.subidas.delete.success"), HttpStatus.OK);
    }


}
