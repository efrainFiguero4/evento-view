/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.eventoview.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Kowalski
 */
@Entity
@Data
@Builder
@Table(name = "codigo")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Codigo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCodigo")
    private Integer idCodigo;

    @Basic(optional = false)
    @Column(name = "numero")
    private String numero;

    @Basic(optional = false)
    @Column(name = "estado")
    private boolean confirmado;

    @Basic(optional = false)
    @Column(name = "usuarioCreacion")
    private int usuarioCreacion;

    @Basic(optional = false)
    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "usuarioModificacion")
    private Integer usuarioModificacion;

    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "idFuncion")
    private Integer idFuncion;

    @Column(name = "idZona")
    private Integer idZona;
}
