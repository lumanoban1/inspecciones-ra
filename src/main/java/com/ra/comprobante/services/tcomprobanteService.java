package com.ra.comprobante.services;
import com.ra.comprobante.entities.tcomprobante;
import java.util.List;
import java.util.Optional;

public interface tcomprobanteService {
    List<tcomprobante> readAll();
    tcomprobante create(tcomprobante comprobante);
    Optional<tcomprobante> readById(Long codcomprobante);
    Optional<tcomprobante> update(tcomprobante comprobante, Long codcomprobante);
    tcomprobante updateEstado(Long codcomprobante);
}
