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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estela
 */
@Entity
@Table(name = "Financiacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Financiacion.findAll", query = "SELECT f FROM Financiacion f")
    , @NamedQuery(name = "Financiacion.findByPkIdOrg", query = "SELECT f FROM Financiacion f WHERE f.pkIdOrg = :pkIdOrg")
    , @NamedQuery(name = "Financiacion.findByRubro", query = "SELECT f FROM Financiacion f WHERE f.rubro = :rubro")
    , @NamedQuery(name = "Financiacion.findByDescri", query = "SELECT f FROM Financiacion f WHERE f.descri = :descri")
    , @NamedQuery(name = "Financiacion.findByAporteUnne", query = "SELECT f FROM Financiacion f WHERE f.aporteUnne = :aporteUnne")
    , @NamedQuery(name = "Financiacion.findByAporteOrganizavion", query = "SELECT f FROM Financiacion f WHERE f.aporteOrganizavion = :aporteOrganizavion")
    , @NamedQuery(name = "Financiacion.findByCostoTotal", query = "SELECT f FROM Financiacion f WHERE f.costoTotal = :costoTotal")})
public class Financiacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_org")
    private Integer pkIdOrg;
    @Size(max = 200)
    @Column(name = "rubro")
    private String rubro;
    @Size(max = 125)
    @Column(name = "descri")
    private String descri;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aporteUnne")
    private Float aporteUnne;
    @Column(name = "aporteOrganizavion")
    private Float aporteOrganizavion;
    @Column(name = "costoTotal")
    private Float costoTotal;
    @JoinColumn(name = "fk_id_pro", referencedColumnName = "pk_id_pro")
    @ManyToOne
    private Proyectos fkIdPro;

    public Financiacion() {
    }

    public Financiacion(Integer pkIdOrg) {
        this.pkIdOrg = pkIdOrg;
    }

    public Integer getPkIdOrg() {
        return pkIdOrg;
    }

    public void setPkIdOrg(Integer pkIdOrg) {
        this.pkIdOrg = pkIdOrg;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Float getAporteUnne() {
        return aporteUnne;
    }

    public void setAporteUnne(Float aporteUnne) {
        this.aporteUnne = aporteUnne;
    }

    public Float getAporteOrganizavion() {
        return aporteOrganizavion;
    }

    public void setAporteOrganizavion(Float aporteOrganizavion) {
        this.aporteOrganizavion = aporteOrganizavion;
    }

    public Float getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Float costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Proyectos getFkIdPro() {
        return fkIdPro;
    }

    public void setFkIdPro(Proyectos fkIdPro) {
        this.fkIdPro = fkIdPro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdOrg != null ? pkIdOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Financiacion)) {
            return false;
        }
        Financiacion other = (Financiacion) object;
        if ((this.pkIdOrg == null && other.pkIdOrg != null) || (this.pkIdOrg != null && !this.pkIdOrg.equals(other.pkIdOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Financiacion[ pkIdOrg=" + pkIdOrg + " ]";
    }
    
}
