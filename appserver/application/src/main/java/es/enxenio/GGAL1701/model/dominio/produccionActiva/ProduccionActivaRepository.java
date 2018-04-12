package es.enxenio.GGAL1701.model.dominio.produccionActiva;

import es.enxenio.GGAL1701.model.dominio.periodico.Periodico;
import es.enxenio.GGAL1701.model.dominio.periodico.TipoPeriodico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 04/08/2017.
 */
public interface ProduccionActivaRepository extends JpaRepository<ProduccionActiva, Long>, ProduccionActivaRepositoryCustom {

}
