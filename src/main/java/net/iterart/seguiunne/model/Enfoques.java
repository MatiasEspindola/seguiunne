/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iterart.seguiunne.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Enfoques")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enfoques.findAll", query = "SELECT e FROM Enfoques e")
    , @NamedQuery(name = "Enfoques.findByPkIdEnf", query = "SELECT e FROM Enfoques e WHERE e.pkIdEnf = :pkIdEnf")
    , @NamedQuery(name = "Enfoques.findByDescri", query = "SELECT e FROM Enfoques e WHERE e.descri = :descri")
    , @NamedQuery(name = "Enfoques.findByMax", query = "SELECT e FROM Enfoques e WHERE e.max = :max")})
public class Enfoques implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "pk_id_enf")
    private String pkIdEnf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 110)
    @Column(name = "descri")
    private String descri;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max")
    private int max;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdEnf")
    private List<Criterios> criteriosList;

    public Enfoques() {
    }

    public Enfoques(String pkIdEnf) {
        this.pkIdEnf = pkIdEnf;
    }

    public Enfoques(String pkIdEnf, String descri, int max) {
        this.pkIdEnf = pkIdEnf;
        this.descri = descri;
        this.max = max;
    }

    public String getPkIdEnf() {
        return pkIdEnf;
    }

    public void setPkIdEnf(String pkIdEnf) {
        this.pkIdEnf = pkIdEnf;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @XmlTransient
    public List<Criterios> getCriteriosList() {
        return criteriosList;
    }

    public void setCriteriosList(List<Criterios> criteriosList) {
        this.criteriosList = criteriosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdEnf != null ? pkIdEnf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enfoques)) {
            return false;
        }
        Enfoques other = (Enfoques) object;
        if ((this.pkIdEnf == null && other.pkIdEnf != null) || (this.pkIdEnf != null && !this.pkIdEnf.equals(other.pkIdEnf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Enfoques[ pkIdEnf=" + pkIdEnf + " ]";
    }
    
}
