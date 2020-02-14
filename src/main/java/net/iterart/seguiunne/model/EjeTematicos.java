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
@Table(name = "EjeTematicos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjeTematicos.findAll", query = "SELECT e FROM EjeTematicos e")
    , @NamedQuery(name = "EjeTematicos.findByPkIdEje", query = "SELECT e FROM EjeTematicos e WHERE e.pkIdEje = :pkIdEje")
    , @NamedQuery(name = "EjeTematicos.findByDescri", query = "SELECT e FROM EjeTematicos e WHERE e.descri = :descri")})
public class EjeTematicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_eje")
    private Integer pkIdEje;
    @Size(max = 125)
    @Column(name = "descri")
    private String descri;
    @OneToMany(mappedBy = "fkIdEje")
    private List<EjesLineas> ejesLineasList;
    @OneToMany(mappedBy = "fkIdEje")
    private List<DatosGralProyecto> datosGralProyectoList;

    public EjeTematicos() {
    }

    public EjeTematicos(Integer pkIdEje) {
        this.pkIdEje = pkIdEje;
    }

    public Integer getPkIdEje() {
        return pkIdEje;
    }

    public void setPkIdEje(Integer pkIdEje) {
        this.pkIdEje = pkIdEje;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @XmlTransient
    public List<EjesLineas> getEjesLineasList() {
        return ejesLineasList;
    }

    public void setEjesLineasList(List<EjesLineas> ejesLineasList) {
        this.ejesLineasList = ejesLineasList;
    }

    @XmlTransient
    public List<DatosGralProyecto> getDatosGralProyectoList() {
        return datosGralProyectoList;
    }

    public void setDatosGralProyectoList(List<DatosGralProyecto> datosGralProyectoList) {
        this.datosGralProyectoList = datosGralProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdEje != null ? pkIdEje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EjeTematicos)) {
            return false;
        }
        EjeTematicos other = (EjeTematicos) object;
        if ((this.pkIdEje == null && other.pkIdEje != null) || (this.pkIdEje != null && !this.pkIdEje.equals(other.pkIdEje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descri;
    }
    
}
