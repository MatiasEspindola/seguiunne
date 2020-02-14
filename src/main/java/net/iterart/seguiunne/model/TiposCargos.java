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
@Table(name = "TiposCargos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposCargos.findAll", query = "SELECT t FROM TiposCargos t")
    , @NamedQuery(name = "TiposCargos.findByPkIdTpc", query = "SELECT t FROM TiposCargos t WHERE t.pkIdTpc = :pkIdTpc")
    , @NamedQuery(name = "TiposCargos.findByDescri", query = "SELECT t FROM TiposCargos t WHERE t.descri = :descri")})
public class TiposCargos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_tpc")
    private Integer pkIdTpc;
    @Size(max = 255)
    @Column(name = "descri")
    private String descri;
    @OneToMany(mappedBy = "fkidtpCar")
    private List<ResponsablesProyecto> responsablesProyectoList;

    public TiposCargos() {
    }

    public TiposCargos(Integer pkIdTpc) {
        this.pkIdTpc = pkIdTpc;
    }

    public Integer getPkIdTpc() {
        return pkIdTpc;
    }

    public void setPkIdTpc(Integer pkIdTpc) {
        this.pkIdTpc = pkIdTpc;
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
        hash += (pkIdTpc != null ? pkIdTpc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposCargos)) {
            return false;
        }
        TiposCargos other = (TiposCargos) object;
        if ((this.pkIdTpc == null && other.pkIdTpc != null) || (this.pkIdTpc != null && !this.pkIdTpc.equals(other.pkIdTpc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TiposCargos[ pkIdTpc=" + pkIdTpc + " ]";
    }
    
}
