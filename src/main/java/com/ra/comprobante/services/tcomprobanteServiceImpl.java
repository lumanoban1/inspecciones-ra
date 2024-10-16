package com.ra.comprobante.services;
import com.ra.comprobante.entities.tcomprobante;
import com.ra.comprobante.repositories.tcomprobanteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class tcomprobanteServiceImpl implements tcomprobanteService {

    private final tcomprobanteRepository comprobanteRepository;

    @Override
    public List<tcomprobante> readAll() {
        return comprobanteRepository.findAllOrderedByComprobanteOrdenDesc();
    }

    @Override
    public Optional<tcomprobante> readById(Long codcomprobante) {
        return comprobanteRepository.findById(codcomprobante);
    }

    @Override
    public tcomprobante create(tcomprobante comprobante) {
        int maxCodcomprobante = comprobanteRepository.findMaxCodcomprobante();
        if (maxCodcomprobante == 0) {
            comprobante.setCodcomprobante((long) (1));
        }else{
            comprobante.setCodcomprobante((long) (maxCodcomprobante + 1));
        }
        return comprobanteRepository.save(comprobante);
    }

    @Override
    public Optional<tcomprobante> update(tcomprobante comprobante, Long codcomprobante) {
        var comprobanteToUpdate = comprobanteRepository.findById(codcomprobante)
                .orElseThrow(()-> new NoSuchElementException("Comprobante no encontrado"));
        comprobanteToUpdate.setCodtipo(comprobante.getCodtipo());
        comprobanteToUpdate.setNumcomprobante(comprobante.getNumcomprobante());
        comprobanteToUpdate.setImpuestoventa(comprobante.getImpuestoventa());
        comprobanteToUpdate.setSubtotal(comprobante.getSubtotal());
        comprobanteToUpdate.setTotal(comprobante.getTotal());
        //comprobanteToUpdate.setFecha(comprobante.getFecha());
        return Optional.of(comprobanteRepository.save(comprobanteToUpdate));
    }

    @Override
    public tcomprobante updateEstado(Long codcomprobante) {
            var comprobanteEstadoToUpdate = this.comprobanteRepository.findById(codcomprobante)
                    .orElseThrow();
            comprobanteEstadoToUpdate.setEstado(false);
            return comprobanteRepository.save(comprobanteEstadoToUpdate);
    }
}
