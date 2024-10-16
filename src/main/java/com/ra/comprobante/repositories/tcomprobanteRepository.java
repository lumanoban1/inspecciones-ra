package com.ra.comprobante.repositories;
import com.ra.comprobante.entities.tcomprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface tcomprobanteRepository extends JpaRepository<tcomprobante, Long> {
    @Query("SELECT a FROM tcomprobante a where a.estado = true AND a.codcomprobante = :codcomprobante")
    Optional<tcomprobante> findById(Long codcomprobante);
    @Query("SELECT COALESCE(MAX(t.codcomprobante), 0) FROM tcomprobante t")
    int findMaxCodcomprobante();
    @Query("SELECT c FROM tcomprobante c where c.estado = true ORDER BY c.codcomprobante DESC" )
    List<tcomprobante> findAllOrderedByComprobanteOrdenDesc();
}
