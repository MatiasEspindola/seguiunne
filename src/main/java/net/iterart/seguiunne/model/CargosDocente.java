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
@Table(name = "CargosDocente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargosDocente.findAll", query = "SELECT c FROM CargosDocente c")
    , @NamedQuery(name = "CargosDocente.findByPkIdCdo", query = "SELECT c FROM CargosDocente c WHERE c.pkIdCdo = :pkIdCdo")
    , @NamedQuery(name = "CargosDocente.findByDescri", query = "SELECT c FROM CargosDocente c WHERE c.descri = :descri")})
public class CargosDocente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_cdo")
    private Integer pkIdCdo;
    @Size(max = 120)
    @Column(name = "descri")
    private String descri;
    @OneToMany(mappedBy = "fkidcarDoc")
    private List<ResponsablesProyecto> responsablesProyectoList;

    public CargosDocente() {
    }

    public CargosDocente(Integer pkIdCdo) {
        this.pkIdCdo = pkIdCdo;
    }

    public Integer getPkIdCdo() {
        return pkIdCdo;
    }

    public void setPkIdCdo(Integer pkIdCdo) {
        this.pkIdCdo = pkIdCdo;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @XmlTransient
    public List<ResponsablesProyecto> getResponsablesProyectoList() {
        return responsablesProyectoList;
    }

    public void setResponsablesProyectoList(List<ResponsablesProyecto> responsablesProyectoList) {
        this.responsablesProyectoList = responsablesProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdCdo != null ? pkIdCdo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargosDocente)) {
            return false;
        }
        CargosDocente other = (CargosDocente) object;
        if ((this.pkIdCdo == null && other.pkIdCdo != null) || (this.pkIdCdo != null && !this.pkIdCdo.equals(other.pkIdCdo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CargosDocente[ pkIdCdo=" + pkIdCdo + " ]";
    }
    
}
