package es.enxenio.GGAL1701.model.dominio.agente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by hlorenzo on 04/09/2017.
 */
public interface AgenteRepository extends JpaRepository<Agente, Long> {

    @Query(value = "SELECT a" +
        " FROM Agente a" +
        " WHERE ( str(a.id) like %:query%" +
        " OR lower(unaccent(a.nombre)) like %:query%" +
        " OR lower(unaccent(a.apellidos)) like %:query% )")
    Page<Agente> filter(@Param("query") String query, Pageable pageable);

}
