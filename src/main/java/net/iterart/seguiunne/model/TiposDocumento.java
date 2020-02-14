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
@Table(name = "TiposDocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposDocumento.findAll", query = "SELECT t FROM TiposDocumento t")
    , @NamedQuery(name = "TiposDocumento.findByPkIdTpd", query = "SELECT t FROM TiposDocumento t WHERE t.pkIdTpd = :pkIdTpd")
    , @NamedQuery(name = "TiposDocumento.findByTipo", query = "SELECT t FROM TiposDocumento t WHERE t.tipo = :tipo")})
public class TiposDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_tpd")
    private Integer pkIdTpd;
    @Size(max = 20)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "fkidtpDoc")
    private List<ResponsablesProyecto> responsablesProyectoList;

    public TiposDocumento() {
    }

    public TiposDocumento(Integer pkIdTpd) {
        this.pkIdTpd = pkIdTpd;
    }

    public Integer getPkIdTpd() {
        return pkIdTpd;
    }

    public void setPkIdTpd(Integer pkIdTpd) {
        this.pkIdTpd = pkIdTpd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (pkIdTpd != null ? pkIdTpd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposDocumento)) {
            return false;
        }
        TiposDocumento other = (TiposDocumento) object;
        if ((this.pkIdTpd == null && other.pkIdTpd != null) || (this.pkIdTpd != null && !this.pkIdTpd.equals(other.pkIdTpd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TiposDocumento[ pkIdTpd=" + pkIdTpd + " ]";
    }
    
}
