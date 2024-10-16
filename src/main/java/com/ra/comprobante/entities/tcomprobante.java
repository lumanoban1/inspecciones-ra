package com.ra.comprobante.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class tcomprobante {
    @Id
    private Long codUser;
    private String Name;
    private String Password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern  = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime creadofech;
    private String modificadopor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern  = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modificadofech;
    private Boolean estado;

}
