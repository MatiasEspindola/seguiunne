/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Metas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Metas.findAll", query = "SELECT m FROM Metas m")
    , @NamedQuery(name = "Metas.findByPkIdMetas", query = "SELECT m FROM Metas m WHERE m.pkIdMetas = :pkIdMetas")
    , @NamedQuery(name = "Metas.findByOrden", query = "SELECT m FROM Metas m WHERE m.orden = :orden")})
public class Metas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_metas")
    private Integer pkIdMetas;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Metas")
    private String metas;
    @Column(name = "orden")
    private Integer orden;
    @JoinColumn(name = "fk_proyecto_obj_esp", referencedColumnName = "pk_id_obj_esp")
    @ManyToOne
    private Proyectosobjesp fkProyectoObjEsp;
    @OneToMany(mappedBy = "fkIdMetas")
    private List<Indicadores> indicadoresList;

    public Metas() {
    }

    public Metas(Integer pkIdMetas) {
        this.pkIdMetas = pkIdMetas;
    }

    public Integer getPkIdMetas() {
        return pkIdMetas;
    }

    public void setPkIdMetas(Integer pkIdMetas) {
        this.pkIdMetas = pkIdMetas;
    }

    public String getMetas() {
        return metas;
    }

    public void setMetas(String metas) {
        this.metas = metas;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Proyectosobjesp getFkProyectoObjEsp() {
        return fkProyectoObjEsp;
    }

    public void setFkProyectoObjEsp(Proyectosobjesp fkProyectoObjEsp) {
        this.fkProyectoObjEsp = fkProyectoObjEsp;
    }

    @XmlTransient
    public List<Indicadores> getIndicadoresList() {
        return indicadoresList;
    }

    public void setIndicadoresList(List<Indicadores> indicadoresList) {
        this.indicadoresList = indicadoresList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdMetas != null ? pkIdMetas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metas)) {
            return false;
        }
        Metas other = (Metas) object;
        if ((this.pkIdMetas == null && other.pkIdMetas != null) || (this.pkIdMetas != null && !this.pkIdMetas.equals(other.pkIdMetas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Metas[ pkIdMetas=" + pkIdMetas + " ]";
    }
    
}
