package com.ra.comprobante.controller;
import com.ra.comprobante.entities.tcomprobante;
import com.ra.comprobante.services.tcomprobanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(path = "comprobante")
@Slf4j
@Tag(name =  "Comprobantes resource")
public class tcomprobanteController {

    private  final tcomprobanteService comprobanteService;

    @Operation(summary = "getAll")
    @GetMapping()
    public ResponseEntity<?> getAll() {
        try{
            List<tcomprobante> comprobantes = comprobanteService.readAll();
            if(!comprobantes.isEmpty()){
                log.info("GET: {} comprobantes", comprobantes.size());
                return ResponseEntity.ok(comprobantes);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron comprobantes");
            }
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "get")
    @GetMapping(path = "{codcomprobante}")
    public ResponseEntity<?> get(@PathVariable Long codcomprobante){
        try{
            Optional<tcomprobante> comprobanteController = comprobanteService.readById(codcomprobante);
            if(comprobanteController.isPresent()){
                return ResponseEntity.ok(comprobanteController.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Comprobante no encontrado.");
            }
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "create")
    @PostMapping()
    public ResponseEntity<tcomprobante> post(@RequestBody tcomprobante comprobante){
        try {
            comprobanteService.create(comprobante);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "update")
    @PutMapping(path = "{codcomprobante}")
    public ResponseEntity<?> put(@RequestBody tcomprobante comprobante,
                                     @PathVariable Long codcomprobante){
        try{
            Optional<tcomprobante> comprobanteUpdate = comprobanteService.update(comprobante, codcomprobante);
            if(comprobanteUpdate.isPresent()){
                log.info("Put:comprobante{}",comprobante);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Comprobante no encontrado.");
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comprobante no encontrado.");
        }
    }

    @Operation(summary = "delete")
    @PutMapping("/delete/{codcomprobante}")
    public ResponseEntity<?> delete(@PathVariable Long codcomprobante) {
        try{
            comprobanteService.updateEstado(codcomprobante);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El código de comprobante no existe.");
        }
    }
}
