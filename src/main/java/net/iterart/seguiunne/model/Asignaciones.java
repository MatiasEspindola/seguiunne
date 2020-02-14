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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "asignaciones")
public class Asignaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_rel")
    private Integer pkIdRel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_id_eval")
    private int fkIdEval;
    @JoinColumn(name = "fk_id_dat", referencedColumnName = "pk_id_dat")
    @ManyToOne(optional = false)
    private DatosGralProyecto fkIdDat;

    public Asignaciones() {
    }

    public Asignaciones(Integer pkIdRel) {
        this.pkIdRel = pkIdRel;
    }

    public Asignaciones(Integer pkIdRel, int fkIdEval) {
        this.pkIdRel = pkIdRel;
        this.fkIdEval = fkIdEval;
    }

    public Integer getPkIdRel() {
        return pkIdRel;
    }

    public void setPkIdRel(Integer pkIdRel) {
        this.pkIdRel = pkIdRel;
    }

    public int getFkIdEval() {
        return fkIdEval;
    }

    public void setFkIdEval(int fkIdEval) {
        this.fkIdEval = fkIdEval;
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
        hash += (pkIdRel != null ? pkIdRel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignaciones)) {
            return false;
        }
        Asignaciones other = (Asignaciones) object;
        if ((this.pkIdRel == null && other.pkIdRel != null) || (this.pkIdRel != null && !this.pkIdRel.equals(other.pkIdRel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Asignaciones[ pkIdRel=" + pkIdRel + " ]";
    }
    
}
