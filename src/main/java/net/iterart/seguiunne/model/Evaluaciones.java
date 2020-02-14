/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Evaluaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluaciones.findAll", query = "SELECT e FROM Evaluaciones e")
    , @NamedQuery(name = "Evaluaciones.findByPkIdEval", query = "SELECT e FROM Evaluaciones e WHERE e.pkIdEval = :pkIdEval")
    , @NamedQuery(name = "Evaluaciones.findByFkIdEval", query = "SELECT e FROM Evaluaciones e WHERE e.fkIdEval = :fkIdEval")
    , @NamedQuery(name = "Evaluaciones.findByPuntaje", query = "SELECT e FROM Evaluaciones e WHERE e.puntaje = :puntaje")})
public class Evaluaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_eval")
    private Integer pkIdEval;
    @Column(name = "fk_id_eval")
    private Integer fkIdEval;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntaje")
    private int puntaje;
    @Lob
    @Size(max = 65535)
    @Column(name = "justifica")
    private String justifica;
    @JoinColumn(name = "fk_id_cri", referencedColumnName = "pk_id_cri")
    @ManyToOne(optional = false)
    private Criterios fkIdCri;
    @JoinColumn(name = "fk_id_dat", referencedColumnName = "pk_id_dat")
    @ManyToOne(optional = false)
    private DatosGralProyecto fkIdDat;

    public Evaluaciones() {
    }

    public Evaluaciones(Integer pkIdEval) {
        this.pkIdEval = pkIdEval;
    }

    public Evaluaciones(Integer pkIdEval, int puntaje) {
        this.pkIdEval = pkIdEval;
        this.puntaje = puntaje;
    }

    public Integer getPkIdEval() {
        return pkIdEval;
    }

    public void setPkIdEval(Integer pkIdEval) {
        this.pkIdEval = pkIdEval;
    }

    public Integer getFkIdEval() {
        return fkIdEval;
    }

    public void setFkIdEval(Integer fkIdEval) {
        this.fkIdEval = fkIdEval;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getJustifica() {
        return justifica;
    }

    public void setJustifica(String justifica) {
        this.justifica = justifica;
    }

    public Criterios getFkIdCri() {
        return fkIdCri;
    }

    public void setFkIdCri(Criterios fkIdCri) {
        this.fkIdCri = fkIdCri;
    }

    public DatosGralProyecto getFkIdDat() {
        return fkIdDat;
    }

    public void setFkIdDat(DatosGralProyecto fkIdDat) {
        this.fkIdDat = fkIdDat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdEval != null ? pkIdEval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluaciones)) {
            return false;
        }
        Evaluaciones other = (Evaluaciones) object;
        if ((this.pkIdEval == null && other.pkIdEval != null) || (this.pkIdEval != null && !this.pkIdEval.equals(other.pkIdEval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Evaluaciones[ pkIdEval=" + pkIdEval + " ]";
    }
    
}
