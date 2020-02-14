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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "AdminsFacultades")
public class AdminsFacultades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_admfac")
    private Integer pkIdAdmfac;
    @JoinColumn(name = "fk_id_usu", referencedColumnName = "pk_id_usu")
    @ManyToOne
    private Usuarios fkIdUsu;
    @JoinColumn(name = "fk_id_fac", referencedColumnName = "pk_id_fac")
    @ManyToOne
    private Facultades fkIdFac;

    public AdminsFacultades() {
    }

    public AdminsFacultades(Integer pkIdAdmfac) {
        this.pkIdAdmfac = pkIdAdmfac;
    }

    public Integer getPkIdAdmfac() {
        return pkIdAdmfac;
    }

    public void setPkIdAdmfac(Integer pkIdAdmfac) {
        this.pkIdAdmfac = pkIdAdmfac;
    }

    public Usuarios getFkIdUsu() {
        return fkIdUsu;
    }

    public void setFkIdUsu(Usuarios fkIdUsu) {
        this.fkIdUsu = fkIdUsu;
    }

    public Facultades getFkIdFac() {
        return fkIdFac;
    }

    public void setFkIdFac(Facultades fkIdFac) {
        this.fkIdFac = fkIdFac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdAdmfac != null ? pkIdAdmfac.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminsFacultades)) {
            return false;
        }
        AdminsFacultades other = (AdminsFacultades) object;
        if ((this.pkIdAdmfac == null && other.pkIdAdmfac != null) || (this.pkIdAdmfac != null && !this.pkIdAdmfac.equals(other.pkIdAdmfac))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AdminsFacultades[ pkIdAdmfac=" + pkIdAdmfac + " ]";
    }
    
}
