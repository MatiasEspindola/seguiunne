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
@Table(name = "TiposMiembro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposMiembro.findAll", query = "SELECT t FROM TiposMiembro t")
    , @NamedQuery(name = "TiposMiembro.findByPkIdTpm", query = "SELECT t FROM TiposMiembro t WHERE t.pkIdTpm = :pkIdTpm")
    , @NamedQuery(name = "TiposMiembro.findByDescri", query = "SELECT t FROM TiposMiembro t WHERE t.descri = :descri")})
public class TiposMiembro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_tpm")
    private Integer pkIdTpm;
    @Size(max = 255)
    @Column(name = "descri")
    private String descri;
    @OneToMany(mappedBy = "fkIdTpm")
    private List<Miembros> miembrosList;

    public TiposMiembro() {
    }

    public TiposMiembro(Integer pkIdTpm) {
        this.pkIdTpm = pkIdTpm;
    }

    public Integer getPkIdTpm() {
        return pkIdTpm;
    }

    public void setPkIdTpm(Integer pkIdTpm) {
        this.pkIdTpm = pkIdTpm;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @XmlTransient
    public List<Miembros> getMiembrosList() {
        return miembrosList;
    }

    public void setMiembrosList(List<Miembros> miembrosList) {
        this.miembrosList = miembrosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdTpm != null ? pkIdTpm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposMiembro)) {
            return false;
        }
        TiposMiembro other = (TiposMiembro) object;
        if ((this.pkIdTpm == null && other.pkIdTpm != null) || (this.pkIdTpm != null && !this.pkIdTpm.equals(other.pkIdTpm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TiposMiembro[ pkIdTpm=" + pkIdTpm + " ]";
    }
    
}
