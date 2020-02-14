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
@Table(name = "Universidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidades.findAll", query = "SELECT u FROM Universidades u")
    , @NamedQuery(name = "Universidades.findByPkIdUni", query = "SELECT u FROM Universidades u WHERE u.pkIdUni = :pkIdUni")
    , @NamedQuery(name = "Universidades.findByUni", query = "SELECT u FROM Universidades u WHERE u.uni = :uni")})
public class Universidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_uni")
    private Integer pkIdUni;
    @Size(max = 125)
    @Column(name = "uni")
    private String uni;
    @OneToMany(mappedBy = "fkIdUni")
    private List<Facultades> facultadesList;
    @JoinColumn(name = "fk_id_loc", referencedColumnName = "pk_id_loc")
    @ManyToOne
    private Localidades fkIdLoc;

    public Universidades() {
    }

    public Universidades(Integer pkIdUni) {
        this.pkIdUni = pkIdUni;
    }

    public Integer getPkIdUni() {
        return pkIdUni;
    }

    public void setPkIdUni(Integer pkIdUni) {
        this.pkIdUni = pkIdUni;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    @XmlTransient
    public List<Facultades> getFacultadesList() {
        return facultadesList;
    }

    public void setFacultadesList(List<Facultades> facultadesList) {
        this.facultadesList = facultadesList;
    }

    public Localidades getFkIdLoc() {
        return fkIdLoc;
    }

    public void setFkIdLoc(Localidades fkIdLoc) {
        this.fkIdLoc = fkIdLoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdUni != null ? pkIdUni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidades)) {
            return false;
        }
        Universidades other = (Universidades) object;
        if ((this.pkIdUni == null && other.pkIdUni != null) || (this.pkIdUni != null && !this.pkIdUni.equals(other.pkIdUni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Universidades[ pkIdUni=" + pkIdUni + " ]";
    }
    
}
